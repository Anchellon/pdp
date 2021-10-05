package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;

import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class FlightlessBird extends Bird {
    private final static String DEFAULT_TYPE = "Flightless Bird";

    public FlightlessBird(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to FlightlessBird.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public FlightlessBird(BirdType.FlightlessBirdTypes type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type.getType(), characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to FlightlessBird.class if user doesn't assign
    }

    public FlightlessBird(String type) {
        super(type);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public FlightlessBird(BirdType.FlightlessBirdTypes type) {
        super(type.getType());
    }

    public void setType(BirdType.FlightlessBirdTypes type) {
        setType(type.getType());
    }

    /**
     * check if target bird is a flightless bird or not
     * @param bird target bird
     * @return is a flightless bird or not
     */
    public static boolean isFlightlessBird(Bird bird) {
        return bird.getType().equals(BirdType.FlightlessBirdTypes.EMU.getType())
                || bird.getType().equals(BirdType.FlightlessBirdTypes.KIWI.getType())
                || bird.getType().equals(BirdType.FlightlessBirdTypes.MOAS.getType());
    }

    @Override
    public String toString() {
        return "FlightlessBird {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                "}";
    }
}
