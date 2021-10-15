package item.impl;

import item.Item;
import item.enums.HeadGearEnum;

/**
 * @author novo
 * @since 2021/10/12
 */
public class HeadGear extends Item {

    /**
     * Constructor of headGear
     *
     * @param headGear what head gear we are trying to create
     */
    public HeadGear(HeadGearEnum headGear) {
        super(headGear.getAdjective(),
                headGear.getType(),
                0.0,
                headGear.getDefenseStrength(),
                false,
                true);
    }
}
