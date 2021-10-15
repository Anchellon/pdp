package item.enums;

/**
 * @author novo
 * @since 2021/10/12
 */
public enum HeadGearEnum {
    HAPPY_HAT("Happy", "Hat", 10.0),
    SAD_HAT("Sad", "Hat", 1.0),
    GREEN_HAT("Green", "Hat", 0.1),
    BLUE_HELMET("Blue", "Helmet", 100.5);

    private String adjective;
    private String type;
    private Double defenseStrength;

    HeadGearEnum(String adjective, String type, Double defenseStrength) {
        this.adjective = adjective;
        this.type = type;
        this.defenseStrength = defenseStrength;
    }

    public String getAdjective() {
        return adjective;
    }

    public String getType() {
        return type;
    }

    public Double getDefenseStrength() {
        return defenseStrength;
    }
}
