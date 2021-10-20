package gear;

import gear.enums.GearType;

/**
 * Interface of gears.
 * Declare common methods of gears.
 *
 * @author novo
 * @since 2021/10/18
 */
public interface Gear {

    // getters and setters
    GearType getType();

    long getId();

    /**
     * Get adjective of this gear
     *
     * @return adjective
     */
    String getAdj();

    /**
     * Get noun of this gear
     *
     * @return noun
     */
    String getNoun();

    /**
     * Get attack power of this gear
     *
     * @return attack power
     */
    int getAttack();

    /**
     * Get defense strength of this gear
     *
     * @return defense strength
     */
    int getDefense();

    /**
     * Set adjective of this gear
     *
     * @param adj adjective
     */
    void setAdj(String adj);

    /**
     * Set noun of this gear
     *
     * @param noun noun
     */
    void setNoun(String noun);

    /**
     * Set attack power of this gear
     *
     * @param attack attack power
     */
    void setAttack(int attack);

    /**
     * Set defense strength of this gear
     *
     * @param defense defense strength
     */
    void setDefense(int defense);

    /**
     * Mark this gear as a combination
     *
     * @param hasCombined is a combination or not
     */
    void setHasCombined(boolean hasCombined);

    /**
     * Get full name of a gear
     *
     * @return combination of adj and noun
     */
    String getFullName();

    /**
     * Get the combination status of a gear
     *
     * @return True if this gear has already combined with other gears.False if not
     */
    boolean hasCombined();

    /**
     * combine two gears
     * steps:
     * 1. first check if the gear is the same type
     * 2. check both of gears have already combined or not
     * 3. do combination
     *
     * @param gear gear gonna be combined
     * @return combination of these two items
     */
    Gear combine(Gear gear) throws Exception;
}
