package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class Pigeon extends Bird {
    private final static String DEFAULT_TYPE = "Pigeon";

    public Pigeon(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to Pigeon.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public Pigeon(String type) {
        super(type);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public Pigeon(BirdType.PigeonTypes type) {
        super(type.getType());
    }

    public void setType(BirdType.PigeonTypes type) {
        setType(type.getType());
    }

    @Override
    public String toString() {
        return "Pigeon {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                "}";
    }
}
