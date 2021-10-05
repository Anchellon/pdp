package entity.birds;

import entity.birds.abstracts.WaterBird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class ShoreBird extends WaterBird {
    private final static String DEFAULT_TYPE = "ShoreBird Bird";

    public ShoreBird(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods, Set<WaterSource> waterSources) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods, waterSources);
        // We will give a default type to ShoreBird.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public ShoreBird(String type, Set<WaterSource> waterSources) {
        super(type, waterSources);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public ShoreBird(BirdType.ShoreBirdTypes type, Set<WaterSource> waterSources) {
        super(type.getType(), waterSources);
    }

    public void setType(BirdType.ShoreBirdTypes type) {
        setType(type.getType());
    }

    @Override
    public String toString() {
        return "ShoreBird {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                ", waterSources=" + getWaterSources() +
                "}";
    }
}
