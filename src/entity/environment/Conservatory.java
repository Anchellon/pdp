package entity.environment;

import dto.birds.BirdWithLocationInfo;
import entity.birds.abstracts.Bird;
import entity.environment.enums.Food;

import java.util.*;

/**
 * Conservatory that holds birds.
 * It has a temporary space and several aviaries.
 *
 * @author novo
 * @since 2021/9/28
 */
public class Conservatory {
    Map<String, List<Bird>> birds;
    List<Aviary> aviaries;

    public Conservatory() {
        birds = new HashMap<>();
        aviaries = new ArrayList<>();
    }

    /**
     * get all birds in conservatory(including those in aviaries)
     *
     * @return birds in conservatory(including those in aviaries)
     */
    public Map<String, List<Bird>> getAllBirds() {
        Map<String, List<Bird>> res = new HashMap<>(birds);
        // iterate aviaries
        for (Aviary aviary : aviaries) {
            res.putAll(aviary.getAllBirds());
        }
        return res;
    }

    /**
     * get all birds in conservatory(excluding those in aviaries)
     *
     * @return birds in conservatory(excluding those in aviaries)
     */
    public Map<String, List<Bird>> getAllBirdsNotInAviary() {
        return birds;
    }

    /**
     * get a list of all birds in conservatory(including those in aviaries)
     *
     * @return birds in conservatory(including those in aviaries)
     */
    public List<Bird> getAllBirdsAsList() {
        List<Bird> ans = new ArrayList<>();
        // add all birds to ans
        for (List<Bird> birdList : birds.values()) {
            ans.addAll(birdList);
        }
        // iterate all aviaries
        for (Aviary aviary : aviaries) {
            ans.addAll(aviary.getAllBirdsAsList());
        }
        return ans;
    }

    /**
     * Print an index that lists all birds in the conservatory in alphabetical order and their location
     *
     * @return List of birds info with location info
     */
    public List<BirdWithLocationInfo> getAllBirdsWithLocation() {
        List<BirdWithLocationInfo> res = new ArrayList<>();
        // iterate all these aviaries
        for (Aviary aviary : aviaries) {
            for (Bird bird : aviary.getAllBirdsAsList()) {
                res.add(new BirdWithLocationInfo(bird, aviary.getLocation()));
            }
        }
        // sort by bird's type
        res.sort(Comparator.comparing(o -> o.getBird().getType()));
        return res;
    }

    /**
     * Print a “map” that lists all the aviaries by location and the birds they house
     *
     * @return map contains aviaries and respective birds inside
     */
    public Map<Aviary, List<Bird>> getAviaryAndBirdsAsMap() {
        Map<Aviary, List<Bird>> res = new HashMap<>();
        for (Aviary aviary : aviaries) {
            res.put(aviary, aviary.getAllBirdsAsList());
        }
        return res;
    }

    /**
     * Calculate what food needs to be kept and in what quantities
     *
     * @return Map of foods and required quantities
     */
    public Map<Food, Integer> getFoodsRequirements() {
        Map<Food, Integer> ans = new HashMap<>();
        // iterate all birds
        List<Bird> allBirds = getAllBirdsAsList();
        for (Bird bird : allBirds) {
            for (Food food : bird.getFavoriteFoods()) {
                // food requirement + 1
                ans.put(food, ans.getOrDefault(food, 0) + 1);
            }
        }
        return ans;
    }

    /**
     * get all aviaries
     *
     * @return list of all aviaries
     */
    public List<Aviary> getAllAviaries() {
        return aviaries;
    }

    /**
     * add new aviary
     *
     * @param aviary new aviary
     */
    public void addAviary(Aviary aviary) throws IllegalStateException {
        if (aviaries.size() >= 20) {
            throw new IllegalStateException("Already has 20 aviaries!");
        }
        aviaries.add(aviary);
    }

    /**
     * remove an aviary
     *
     * @param aviary the one need to delete
     */
    public void removeAviary(Aviary aviary) {
        aviaries.remove(aviary);
    }

    /**
     * assign a bird to a aviary. Need to check if it is possible or not.
     *
     * @param bird   target bird
     * @param aviary target aviary
     * @return result
     */
    public boolean assignBirdToAviary(Bird bird, Aviary aviary) {
        // check if the number of birds will exceed maximum
        if (aviary.getNumOfBirds() >= Aviary.MAX_CAPACITY) {
            return false;
        }
        // check if this bird could be assigned to this aviary or not
        if (!aviary.isConflict(bird)) {
            // add this bird to aviary
            aviary.addBird(bird);
            // remove this bird from temporary conservatory
            birds.get(bird.getType()).remove(bird);
            return true;
        }
        return false;
    }

    /**
     * rescue a bird.
     *
     * @param bird target bird
     * @return if the bird is distinct, return false
     */
    public boolean rescueBird(Bird bird) {
        if (bird.isDistinct()) {
            return false;
        }
        birds.putIfAbsent(bird.getType(), new ArrayList<>());
        birds.get(bird.getType()).add(bird);
        return true;
    }


    /**
     * search bird by bird's id
     *
     * @param id target bird's id
     * @return aviary that the bird is in
     */
    public Aviary searchBird(Integer id) {
        for (Aviary aviary : aviaries) {
            for (Bird bird : aviary.getAllBirdsAsList()) {
                if (bird.getId() == id) {
                    return aviary;
                }
            }
        }
        return null;
    }

    /**
     * search bird by bird's object
     *
     * @param bird target
     * @return aviary that the bird is in
     */
    public Aviary searchBird(Bird bird) {
        for (Aviary aviary : aviaries) {
            if (aviary.getAllBirdsAsList().contains(bird)) {
                return aviary;
            }
        }
        return null;
    }

    /**
     * print all signs of all the aviaries
     */
    private void printAllSigns() {
        for (Aviary aviary : aviaries) {
            System.out.println(Sign.getDescription(aviary));
        }
    }


}
