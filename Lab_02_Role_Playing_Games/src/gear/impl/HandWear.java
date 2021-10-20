package gear.impl;

import gear.AbstractGear;
import gear.enums.GearType;

/**
 * Class of hand wear, inheriting from {@link AbstractGear}
 *
 * @author novo
 * @since 2021/10/18
 */
public class HandWear extends AbstractGear {

    private static final int DEFAULT_DEFENSE_STRENGTH = 0;

    /**
     * Constructor for hand wear, type is fixed to {@link GearType}.HAND.
     * Since head wear could only be used for attack, there's no
     * defense parameter in the constructor.
     *
     * @param adj    adjective
     * @param noun   noun
     * @param attack attack power
     */
    public HandWear(String adj, String noun, int attack) {
        super(GearType.HAND, adj, noun, attack, DEFAULT_DEFENSE_STRENGTH);
    }
}
