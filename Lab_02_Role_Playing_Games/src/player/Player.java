package player;

import item.Gear;
import item.enums.GearType;
import util.CountUtil;

import java.util.ArrayList;
import java.util.List;

public class Player {
    // Maximum number of gears a player could have
    public static final int FOOTWEAR_MAX = 2;
    public static final int HAND_GEAR_MAX = 2;
    public static final int HEAD_GEAR_MAX = 1;

    // id of player. won't change after creation
    private final long playerId;
    // Name of the player
    private final String name;
    // Original attack power of the player
    private final int attackBase;
    // Original defense strength of the player
    private final int defenseBase;

    // Gears the player currently has
    private List<Gear> handGear;
    private List<Gear> footwear;
    private Gear headGear;


    /**
     * Constructor of player
     *
     * @param name        name of the player
     * @param attackBase  original attack power of the player, can't be changed in the future
     * @param defenseBase original defense strength of the player, can't be changed in the future
     */
    public Player(String name, int attackBase, int defenseBase) {
        this.name = name;
        this.attackBase = attackBase;
        this.defenseBase = defenseBase;
        handGear = new ArrayList<>();
        footwear = new ArrayList<>();
        headGear = null;
        // get auto generated player id
        playerId = CountUtil.generatePlayerId();
    }

    /**
     * Constructor of player without attackBase and defenseBase.
     * They will be set to 0 by default.
     *
     * @param name name of the player
     */
    public Player(String name) {
        // use the other constructor
        this(name, 0, 0);
    }

    // getters
    public long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    /**
     * get total attack power of this player
     * @return total attack power
     */
    public int getTotalAttack() {
        int totalAttack = attackBase;
        for (Gear curr : handGear) {
            totalAttack += curr.getAttack();
        }
        for (Gear curr : footwear) {
            totalAttack += curr.getAttack();
        }
        return totalAttack;
    }

    /**
     * get total defense strength of this player
     * @return total defense strength
     */
    public int getTotalDefense() {
        int totalDefense = defenseBase;
        totalDefense += headGear.getDefense();
        for (Gear curr : footwear) {
            totalDefense += curr.getDefense();
        }
        return totalDefense;
    }

    /**
     * check this player could pick head gear or not
     */
    public boolean canPickHeadGear() {
        return headGear == null || !headGear.hasCombined();
    }

    /**
     * check this player could pick hand gear or not
     */
    public boolean canPickHandGear() {
        if (handGear.size() < HAND_GEAR_MAX) {
            return true;
        }
        for (Gear curr : handGear) {
            if (!curr.hasCombined()) {
                return true;
            }
        }
        return false;
    }

    /**
     * check this player could pick footwear or not
     */
    public boolean canPickFootwear() {
        if (footwear.size() < FOOTWEAR_MAX) {
            return true;
        }
        for (Gear curr : footwear) {
            if (!curr.hasCombined()) {
                return true;
            }
        }
        return false;
    }

    /**
     * this player still could pick gears
     */
    public boolean canPick() {
        return canPickHandGear() || canPickFootwear() || canPickHeadGear();
    }


    /**
     * Try to pick up a gear
     *
     * @param gear new gear
     * @throws Exception When the type doesn't exist or illegal, throw exception
     */
    public void pickup(Gear gear) throws Exception {
        // combine gear by its type
        switch (gear.getType()) {
            case HEAD:
                pickHeadWear(gear);
                break;
            case HAND:
                pickHandWear(gear);
                break;
            case FOOT:
                pickFootwear(gear);
                break;
            default:
                throw new IllegalArgumentException("Illegal type of gear");
        }
    }

    /**
     * cannot be used by other class
     * try to pick up a head wear
     *
     * @param gear new gear
     * @throws Exception When the type of gear is not {@link GearType}.HEAD, throw exception
     */
    private void pickHeadWear(Gear gear) throws Exception {
        if (gear.getType() != GearType.HEAD) {
            throw new IllegalArgumentException("Wrong type of gear");
        }
        // check status of head gear
        if (headGear == null) {
            // head gear is null, so the player doesn't have head gear yet.
            headGear = gear;
        } else {
            // player already has head gear, combine them.
            headGear = headGear.combine(gear);
        }
    }

    /**
     * cannot be used by other class
     * try to pick up a hand wear
     *
     * @param gear new gear
     * @throws Exception When the type of gear is not {@link GearType}.HAND, throw exception
     */
    private void pickHandWear(Gear gear) throws Exception {
        if (gear.getType() != GearType.HAND) {
            throw new IllegalArgumentException("Wrong type of gear");
        }
        // check status of head gear list
        if (handGear.size() <= HAND_GEAR_MAX - 1) {
            // there is still space left in the list, so we could add it directly.
            handGear.add(gear);
            return;
        }
        // player already has two hand gears, try to do combination.
        boolean success = false;
        for (Gear curr : handGear) {
            if (!curr.hasCombined()) {
                // directly modify the old gear
                curr.combine(gear);
                success = true;
                break;
            }
        }
        // if we didn't combine gears successfully, throw an exception
        if (!success) {
            throw new IllegalStateException("Failed to combine gears");
        }
    }

    /**
     * cannot be used by other class
     * try to pick up a foot wear
     *
     * @param gear new gear
     * @throws Exception When the type of gear is not {@link GearType}.FOOT, throw exception
     */
    private void pickFootwear(Gear gear) throws Exception {
        if (gear.getType() != GearType.FOOT) {
            throw new IllegalArgumentException("Wrong type of gear");
        }
        // check status of footwear list
        if (footwear.size() <= FOOTWEAR_MAX - 1) {
            // there is still space left in the list, so we could add it directly.
            footwear.add(gear);
            return;
        }
        // player already has two foot wears, try to do combination.
        boolean success = false;
        for (Gear curr : footwear) {
            if (!curr.hasCombined()) {
                // directly modify the old gear
                curr.combine(gear);
                success = true;
                break;
            }
        }
        // if we didn't combine gears successfully, throw an exception
        if (!success) {
            throw new IllegalStateException("Failed to combine gears");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== INFO =====").append("\n")
                .append("* playerId: ").append(playerId).append("\n")
                .append("* name: ").append(name).append("\n")
                .append("* original attack power: ").append(attackBase).append("\n")
                .append("* original defense strength: ").append(defenseBase).append("\n")
                .append("* gears: ").append("\n")
                .append("    - head gear: ").append("\n")
                .append("        ").append("「 ").append(headGear.getFullName()).append(" 」").append("\n")
                .append("          > defense strength = ").append(headGear.getDefense()).append("\n")
                .append("    - hand gears: ").append("\n");
        int totalAttack = this.attackBase;
        int totalDefense = this.defenseBase;
        totalDefense += headGear.getDefense();
        if (handGear.size() == 0) {
            sb.append("NONE").append("\n");
        } else {
            for (Gear curr : handGear) {
                sb.append("        「 ").append(curr.getFullName()).append(" 」").append("\n");
                sb.append("          > attack power = ").append(curr.getAttack()).append("\n");
                totalAttack += curr.getAttack();
                totalDefense += curr.getDefense();
            }
        }

        sb.append("    - foot wears: ").append("\n");
        if (footwear.size() == 0) {
            sb.append("NONE").append("\n");
        } else {
            for (Gear curr : footwear) {
                sb.append("        「 ").append(curr.getFullName()).append(" 」").append("\n");
                sb.append("          > attack power = ").append(curr.getAttack()).append("\n");
                sb.append("          > defense strength = ").append(curr.getDefense()).append("\n");
                totalAttack += curr.getAttack();
                totalDefense += curr.getDefense();
            }
        }
        sb.append("----------------------------").append("\n");
        sb.append("* total attack power: ").append(totalAttack).append("\n");
        sb.append("* total defense strength: ").append(totalDefense).append("\n");
        return sb.toString();
    }


}
