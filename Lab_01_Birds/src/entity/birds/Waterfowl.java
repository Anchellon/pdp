package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.abstracts.WaterBird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class Waterfowl extends WaterBird {

    private final static String DEFAULT_TYPE = "Waterfowl Bird";

    public Waterfowl(String type, String characteristic, Boolean extinct, Integer numOfWings, Set<Food> favoriteFoods, Set<WaterSource> waterSources) {
        super(type, characteristic, extinct, numOfWings, favoriteFoods, waterSources);
        // We will give a default type to Waterfowl.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public Waterfowl(String type, Set<WaterSource> waterSources) {
        super(type, waterSources);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public Waterfowl(BirdType.WaterfowlTypes type, Set<WaterSource> waterSources) {
        super(type.getType(), waterSources);
    }

    public void setType(BirdType.WaterfowlTypes type) {
        setType(type.getType());
    }

    /**
     * check if target bird is a waterfowl or not
     * @param bird target bird
     * @return is a waterfowl or not
     */
    public static boolean isWaterfowl(Bird bird) {
        return bird.getType().equals(BirdType.WaterfowlTypes.DUCK.getType())
                || bird.getType().equals(BirdType.WaterfowlTypes.GOOSE.getType())
                || bird.getType().equals(BirdType.WaterfowlTypes.SWAN.getType());
    }

    @Override
    public String toString() {
        return "Waterfowl {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isExtinct=" + isExtinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                ", waterSources=" + getWaterSources() +
                "}";
    }
}
