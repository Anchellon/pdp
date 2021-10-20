package gear.impl;

import gear.AbstractGear;
import gear.enums.GearType;

/**
 * Class of head wear, inheriting from {@link AbstractGear}
 *
 * @author novo
 * @since 2021/10/18
 */
public class HeadWear extends AbstractGear {

    private static final int DEFAULT_ATTACK_POWER = 0;

    /**
     * Constructor for head wear, type is fixed to {@link GearType}.HEAD.
     * Since head wear could only be used for defense, there's no
     * attack parameter in the constructor.
     *
     * @param adj     adjective
     * @param noun    noun
     * @param defense defense strength
     */
    public HeadWear(String adj, String noun, int defense) {
        super(GearType.HEAD, adj, noun, DEFAULT_ATTACK_POWER, defense);
    }
}
