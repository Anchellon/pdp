package item;

import item.enums.GearType;
import util.CountUtil;

/**
 * Abstract class of gears, implementing {@link Gear}.
 * Define common methods of gears.
 *
 * @author novo
 * @since 2021/10/18
 */
public class AbstractGear implements Gear {
    private final long gearId;
    private GearType type;
    private String adj;
    private String noun;
    private int attack;
    private int defense;

    // this gear has already combined with other gears or not
    // false by default
    private boolean hasCombined = false;

    /**
     * Construct gear with parameters.
     *
     * @param type    type of the gear
     * @param adj     adjective of the gear
     * @param noun    noun of the gear
     * @param attack  attack power
     * @param defense defense strength
     */
    public AbstractGear(GearType type, String adj, String noun, int attack, int defense) {
        this.type = type;
        this.adj = adj;
        this.noun = noun;
        this.attack = attack;
        this.defense = defense;
        // get auto generated id
        this.gearId = CountUtil.generateGearId();
    }

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
    @Override
    public Gear combine(Gear gear) throws Exception {
        // check types
        if (gear.getType() != this.getType()) {
            throw new IllegalArgumentException("Failed to combine -- different types");
        }
        // check status of combination
        if (this.hasCombined() || gear.hasCombined()) {
            throw new IllegalStateException("Failed to combine -- already combined with other gears");
        }
        // generate new adjective in lexicographic order
        String newAdj = this.getAdj().compareTo(gear.getAdj()) <= 0 ?
                this.getAdj() + ", " + gear.getAdj() : gear.getAdj() + ", " + this.getAdj();
        // generate new noun in lexicographic order
        String newNoun = this.getNoun().compareTo(gear.getNoun()) <= 0 ?
                this.getNoun() : gear.getNoun();
        // get the sum of these two gears' attack power
        int newAttack = this.getAttack() + gear.getAttack();
        // get the sum of these two gears' defense strength
        int newDefense = this.getDefense() + gear.getDefense();
        // update gear
        this.setAdj(newAdj);
        this.setNoun(newNoun);
        this.setAttack(newAttack);
        this.setDefense(newDefense);
        // mark this as hasCombined
        this.setHasCombined(true);
        // return this as result
        return this;
    }

    /**
     * Get type of this gear
     *
     * @return type
     */
    @Override
    public GearType getType() {
        return type;
    }

    /**
     * Set type of this gear
     *
     * @param type type
     */
    @Override
    public void setType(GearType type) {
        this.type = type;
    }

    /**
     * Get adjective of this gear
     *
     * @return adjective
     */
    @Override
    public String getAdj() {
        return adj;
    }

    /**
     * Set adjective of this gear
     *
     * @param adj adjective
     */
    @Override
    public void setAdj(String adj) {
        this.adj = adj;
    }

    /**
     * Get noun of this gear
     *
     * @return noun
     */
    @Override
    public String getNoun() {
        return noun;
    }

    /**
     * Set noun of this gear
     *
     * @param noun noun
     */
    @Override
    public void setNoun(String noun) {
        this.noun = noun;
    }

    /**
     * Get attack power of this gear
     *
     * @return attack power
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Set attack power of this gear
     *
     * @param attack attack power
     */
    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Get defense strength of this gear
     *
     * @return defense strength
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /**
     * Set defense strength of this gear
     *
     * @param defense defense strength
     */
    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Get the combination status of a gear
     *
     * @return True if this gear has already combined with other gears.False if not
     */
    @Override
    public boolean hasCombined() {
        return hasCombined;
    }

    /**
     * Mark this gear as a combination
     *
     * @param hasCombined is a combination or not
     */
    @Override
    public void setHasCombined(boolean hasCombined) {
        this.hasCombined = hasCombined;
    }

    /**
     * Get full name of a gear
     *
     * @return combination of adj and noun
     */
    @Override
    public String getFullName() {
        return getAdj() + " " + getNoun();
    }


}
