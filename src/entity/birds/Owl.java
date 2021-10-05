package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class Owl extends Bird {
    private final static String DEFAULT_TYPE = "Owl";

    public Owl(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to Owl.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public Owl(String type) {
        super(type);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public Owl(BirdType.OwlTypes type) {
        super(type.getType());
    }

    public void setType(BirdType.OwlTypes type) {
        setType(type.getType());
    }

    @Override
    public String toString() {
        return "Owl {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                "}";
    }
}
