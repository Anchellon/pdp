package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;

import java.util.Set;

/**
 * @author novo
 * @since 2021/9/28
 */
public class PreyBird extends Bird {

    private final static String DEFAULT_TYPE = "Prey Bird";

    public PreyBird(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to PreyBird.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public PreyBird(String type) {
        super(type);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public PreyBird(BirdType.PreyBirdTypes type) {
        super(type.getType());
    }

    public void setType(BirdType.PreyBirdTypes type) {
        setType(type.getType());
    }

    /**
     * check if target bird is a prey bird or not
     * @param bird target bird
     * @return is a prey bird or not
     */
    public static boolean isPreyBird(Bird bird) {
        return bird.getType().equals(BirdType.PreyBirdTypes.EAGLES.getType())
                || bird.getType().equals(BirdType.PreyBirdTypes.HAWKS.getType())
                || bird.getType().equals(BirdType.PreyBirdTypes.OSPREY.getType());
    }

    @Override
    public String toString() {
        return "PreyBird {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                "}";
    }
}
