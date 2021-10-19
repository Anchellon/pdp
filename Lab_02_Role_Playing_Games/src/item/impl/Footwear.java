package item.impl;

import item.AbstractGear;
import item.enums.GearType;

/**
 * Class of footwear, inheriting from {@link AbstractGear}
 *
 * @author novo
 * @since 2021/10/18
 */
public class Footwear extends AbstractGear {

    /**
     * Constructor for footwear, type is fixed to {@link GearType}.FOOT.
     *
     * @param adj     adjective
     * @param noun    noun
     * @param attack  attack power
     * @param defense defense strength
     */
    public Footwear(String adj, String noun, int attack, int defense) {
        super(GearType.FOOT, adj, noun, attack, defense);
    }
}


