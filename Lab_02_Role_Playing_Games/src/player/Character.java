package player;

import item.Gear;
import item.enums.GearType;

import java.util.ArrayList;
import java.util.List;

public class Character {
    // Maximum number of gears a player could have
    public static final int FOOT_WEAR_NUM = 2;
    public static final int HAND_GEAR_NUM = 2;
    public static final int HEAD_GEAR_NUM = 1;

    // Gears the player currently has
    private List<Gear> handGear;
    private List<Gear> footWear;
    private Gear headGear;

    // Name of the player
    private final String name;
    // Original attack power of the player
    private final int attackBase;
    // Original defense strength of the player
    private final int defenseBase;

    /**
     * Constructor of player
     *
     * @param name name of the player
     * @param attackBase original attack power of the player, can't be changed in the future
     * @param defenseBase original defense strength of the player, can't be changed in the future
     */
    public Character(String name, int attackBase, int defenseBase) {
        this.name = name;
        this.attackBase = attackBase;
        this.defenseBase = defenseBase;
        handGear = new ArrayList<>();
        footWear = new ArrayList<>();
        headGear = null;
    }

    /**
     * Constructor of player without attackBase and defenseBase.
     * They will be set to 0 by default.
     *
     * @param name name of the player
     */
    public Character(String name) {
        this.name = name;
        this.attackBase = 0;
        this.defenseBase = 0;
        handGear = new ArrayList<>();
        footWear = new ArrayList<>();
        headGear = null;
    }



    void pickup(Gear gear) throws Exception{
        // get the type of new gear
        GearType newType = gear.getType();
        // use
        if (newType == GearType.HEAD) {
            pickHeadWear(gear);
        }
        // simply put `g` on if there is avail slot
        // otherwise, combine `g` with an existing one
        // with the same type, by calling g.Combine(...);
    }

    void pickHeadWear(Gear gear) throws Exception{
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


}
