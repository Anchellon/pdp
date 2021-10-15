package item;

/**
 * @author novo
 * @since 2021/10/12
 */
public abstract class Item {
    //- id: Long
    //- adjective: String
    //- type:String
    //- attackPower: Double = 0
    //- defenseStrength: Double = 0
    //- attackable: boolean = false
    //- defendable: boolean = false
    private Long id;
    private String adjective;
    private String type;
    private Double attackPower;
    private Double defenseStrength;
    private Boolean attackable;
    private Boolean defendable;

    /**
     * constructor of item, we won't pass id because id is auto-generated.
     *
     * @param adjective the adjective of this item
     * @param type the noun of this item
     * @param attackPower attack power of this item
     * @param defenseStrength defense strength of this item
     * @param attackable this item is attackable or not
     * @param defendable this item is defendable or not
     */
    public Item(String adjective, String type, Double attackPower, Double defenseStrength, Boolean attackable, Boolean defendable) {
        this.adjective = adjective;
        this.type = type;
        this.attackPower = attackPower;
        this.defenseStrength = defenseStrength;
        this.attackable = attackable;
        this.defendable = defendable;
    }




}
