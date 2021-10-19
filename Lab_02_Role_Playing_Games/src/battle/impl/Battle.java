package battle.impl;

import battle.BattleInterface;
import item.Gear;
import item.enums.GearType;
import player.Player;
import util.CountUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author novo
 * @since 2021/10/19
 */
public class Battle implements BattleInterface {
    private final long battleId;

    // two players in a battle
    private Player player1;
    private Player player2;

    // gears that players could pick up from
    private List<Gear> gears;

    /**
     * constructor of battle
     *
     * @param player1 player1
     * @param player2 player2
     * @param gears   gears that players could pick
     */
    public Battle(Player player1, Player player2, List<Gear> gears) {
        // check arguments
        if (player1 == null || player2 == null || gears == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (player1 == player2) {
            throw new IllegalArgumentException("Players cannot be the same");
        }
        this.player1 = player1;
        this.player2 = player2;
        this.gears = gears;
        this.battleId = CountUtil.generateBattleId();
    }

    /**
     * constructor of battle without gears
     *
     * @param player1 player1
     * @param player2 player2
     */
    public Battle(Player player1, Player player2) {
        this(player1, player2, new ArrayList<>());
    }

    /**
     * add gear to gears
     *
     * @param gear new gear
     */
    public void addGear(Gear gear) {
        // check argument
        if (gear == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        // check if gear already exists
        if (gears.contains(gear)) {
            throw new IllegalArgumentException("Gear already exists");
        }
        gears.add(gear);
    }

    /**
     * all gears to gears
     *
     * @param gears new gears
     */
    public void addGear(List<Gear> gears) {
        // check argument
        if (gears == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        // check if gear already exists
        for (Gear gear : gears) {
            if (gears.contains(gear)) {
                throw new IllegalArgumentException("Gear already exists");
            }
        }
        this.gears.addAll(gears);
    }

    @Override
    public void armPlayers() throws Exception {
        // check if we could arm players
        if (player1 == null || player2 == null) {
            throw new IllegalStateException("Not enough players");
        }
        // sort gears
        sortGears();
        // Use random to decide which player could pick first
        Random random = new Random();
        // we still have gears and one of the players still could pick gears
        while (!gears.isEmpty() || (player1.canPick() || player2.canPick())) {
            boolean picked;
            if (!player1.canPick()) {
                // if player1 can't pick, then just let player2 pick
                picked = pickGear(player2);
            } else if (!player2.canPick()) {
                // if player2 can't pick, then just let player1 pick
                picked = pickGear(player1);
            } else {
                // both of them could pick
                // randomly choose which player could pick
                if (random.nextInt(10) % 2 == 0) {
                    // if it's an even number, then player1 could pick
                    picked = pickGear(player1);
                    if (!picked) {
                        // if p1 failed to pick, let's try p2
                        // this is because, p1 may don't have gears to pick.
                        // But p2 still could pick gears. We don't want it to stop.
                        picked = pickGear(player2);
                    }
                } else {
                    // otherwise, player2 could pick
                    picked = pickGear(player2);
                    if (!picked) {
                        // if p1 failed to pick, let's try p2
                        picked = pickGear(player1);
                    }
                }
            }
            if (!picked) {
                // nobody pick last turn, that means we should stop picking
                break;
            }
            Thread.sleep(500);
        }
        System.out.println("-------------------------- STOP PICKING --------------------------\n\n\n\n");
    }

    /**
     * Let one player pick up one gear form gears
     *
     * @param player the player who is gonna pick up gear
     * @return did the player successfully pick up a gear
     */
    private boolean pickGear(Player player) throws Exception {
        // check argument
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        // check if player still has slots for gears
        if (!player.canPickHeadGear() && !player.canPickFootwear() && !player.canPickHandGear()) {
            // no slots for all types of gears
            return false;
        }
        // picked gear
        Gear pickedGear = null;
        // iterate over all gears
        for (Gear gear : gears) {
            if (gear.getType() == GearType.HEAD && player.canPickHeadGear()) {
                // if this gear is a head gear and player still has slot for this gear
                pickedGear = gear;
                break;
            } else if (gear.getType() == GearType.FOOT && player.canPickFootwear()) {
                // if this gear is a foot wear and player still has slot for this gear
                pickedGear = gear;
                break;
            } else if (gear.getType() == GearType.HAND && player.canPickHandGear()) {
                // if this gear is a hand gear and player still has slot for this gear
                pickedGear = gear;
                break;
            }
        }
        // if we successfully find a gear to pick
        if (pickedGear != null) {
            player.pickup(pickedGear);
            gears.remove(pickedGear);
            // print info
            System.out.println("[" + player.getPlayerId() + "] " + player.getName()
                    + " picked 「" + pickedGear.getFullName() + " 」\n"
                    + "        > attack power = " + pickedGear.getAttack() + "\n"
                    + "        > defense strength = " + pickedGear.getDefense() + "\n");
            return true;
        }
        // find nothing
        return false;
    }

    /**
     * From the description:
     * <p>
     * Rule 1: Prefer the type of item that the character has available slot for.
     * For example, if the character already has 2 footwear, 1 hand gear and 1 helmet,
     * the character should try to choose 1 more hand gear.
     * <p>
     * Rule 2: if rule 1 leads to multiple choices, pick which item has the highest strength
     * <p>
     * We would know, attack has higher priority over defense. So we just sort gears by their attack.
     */
    private void sortGears() {
        gears.sort((g1, g2) -> {
            if (g1.getAttack() != g2.getAttack()) {
                return Integer.compare(g2.getAttack(), g1.getAttack());
            } else {
                return Integer.compare(g2.getDefense(), g1.getDefense());
            }
        });
    }

    @Override
    public String status() {
        return player1.toString() + "\n" + player2.toString();
    }

    @Override
    public String winner() {
        if (player1 == null || player2 == null) {
            throw new IllegalStateException("Players cannot be null before starting");
        }
        // get attack power and defense strength of p1
        int attackOfP1 = player1.getTotalAttack();
        int defenseOfP1 = player1.getTotalDefense();

        // get attack power and defense strength of p1
        int attackOfP2 = player2.getTotalAttack();
        int defenseOfP2 = player2.getTotalDefense();
        StringBuilder res = new StringBuilder();
        res.append("====================================").append("\n")
                .append("======== RESULT OF BATTLE ").append(battleId).append(" ========").append("\n");
        if (attackOfP1 - defenseOfP2 > attackOfP2 - defenseOfP1) {
            // p1 win
            res.append("========    Player 1 WON    ========");
        } else if (attackOfP1 - defenseOfP2 < attackOfP2 - defenseOfP1) {
            res.append("========    Player 2 WON    ========");
        } else {
            res.append("========         TIE        ========");
        }
        res.append("\n====================================").append("\n");
        return res.toString();
    }
}
