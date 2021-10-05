package entity.environment;

import entity.birds.FlightlessBird;
import entity.birds.PreyBird;
import entity.birds.Waterfowl;
import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aviary is a place to store birds.
 *
 * @author novo
 * @since 2021/9/28
 */
public class Aviary {
    // maximum capacity of each aviary
    public static final int MAX_CAPACITY = 5;

    private Map<String, List<Bird>> birds;
    private Location location;

    /**
     * constructor of aviary
     * @param x coordinate x
     * @param y coordinate y
     */
    public Aviary(Double x, Double y) {
        this.birds = new HashMap<>();
        this.location = new Location(x, y);
    }

    /**
     * constructor of aviary
     * @param location location of aviary
     */
    public Aviary(Location location) {
        this.birds = new HashMap<>();
        this.location = location;
    }

    /**
     * get all birds in this aviary
     *
     * @return map of birds
     */
    public Map<String, List<Bird>> getAllBirds() {
        return birds;
    }

    /**
     * get a list of all birds in this array
     *
     * @return list of birds
     */
    public List<Bird> getAllBirdsAsList() {
        List<Bird> ans = new ArrayList<>();
        for (List<Bird> birdList : birds.values()) {
            ans.addAll(birdList);
        }
        return ans;
    }

    /**
     * add new bird to this aviary
     *
     * @param bird bird to be added
     */
    public void addBird(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }
        birds.putIfAbsent(bird.getType(), new ArrayList<>());
        birds.get(bird.getType()).add(bird);
    }

    /**
     * get location of this aviary
     *
     * @return location of this aviary
     */
    public Location getLocation() {
        return location;
    }

    /**
     * set location of this aviary
     *
     * @param location new location
     */
    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Arguement cannot be null");
        }
        this.location = location;
    }

    /**
     * set location of this aviary by x and y
     *
     * @param x x
     * @param y y
     */
    public void setLocation(Double x, Double y) {
        this.location = new Location(x, y);
    }

    /**
     * Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types
     *
     * @param bird new bird
     * @return is conflict or not
     */
    public boolean isConflict(Bird bird) {
        if (bird == null) {
            throw new IllegalArgumentException("Bird cannot be null");
        }
        // if this aviary is empty, then it will not have confliction
        if (this.isEmpty()) {
            return false;
        }
        // if this bird is a prey bird, or flightless bird, or waterfowl.
        // Then when this aviary only contains the same type of birds and no more other birds, then return true;
        if (PreyBird.isPreyBird(bird) || FlightlessBird.isFlightlessBird(bird) || Waterfowl.isWaterfowl(bird)) {
            // target bird belongs to one of these three kinds of birds
            if (birds.containsKey(bird.getType())) {
                // if there're birds has the same type here, that means there only has this kind of birds
                return false;
            } else {
                // this means, there're other kinds of birds here.
                return true;
            }
        } else {
            // target bird dosen't belong to any of these three kinds of birds.
            // we still need to check if there're conflicting birds here
            // 1: check prey birds
            for (BirdType.PreyBirdTypes birdType : BirdType.PreyBirdTypes.values()) {
                if (birds.containsKey(birdType.getType())) {
                    return true;
                }
            }
            // 2: check flightless birds
            for (BirdType.FlightlessBirdTypes birdType : BirdType.FlightlessBirdTypes.values()) {
                if (birds.containsKey(birdType.getType())) {
                    return true;
                }
            }
            // 3: check Waterfowl birds
            for (BirdType.WaterfowlTypes birdType : BirdType.WaterfowlTypes.values()) {
                if (birds.containsKey(birdType.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return number of types in this aviary
     *
     * @return number of types
     */
    public Integer getNumOfBirds() {
        int number = 0;
        for (List<Bird> birdList : birds.values()) {
            number += birdList.size();
        }
        return number;
    }

    /**
     * check if this aviary is empty
     *
     * @return return true if this aviary is empty
     */
    public boolean isEmpty() {
        return birds.isEmpty();
    }
}
