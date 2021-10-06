package entity.birds.abstracts;

import entity.environment.enums.Food;

import java.util.Set;

/**
 * The abstract class of birds, containing some fundamental attributes.
 * Each bird will have a unique id. This id is auto-generated and could not be changed after initialization.
 *
 * @author novo
 * @since 2021/9/28
 */
public abstract class Bird {
    public static int count = 1;
    // Id could not be changed after construction
    private final Integer id;
    // type could not be changed after construction
    private String type;
    private String characteristic;
    private Boolean extinct;
    private Integer numOfWings;
    private Set<Food> favoriteFoods;

    public Bird(String type, String characteristic, Boolean extinct, Integer numOfWings, Set<Food> favoriteFoods) {
        this.id = count++;
        this.type = type;
        this.characteristic = characteristic;
        this.extinct = extinct;
        this.numOfWings = numOfWings;
        this.favoriteFoods = favoriteFoods;
    }

    public Bird(String type) {
        this.id = count++;
        this.type = type;
    }

    /**
     * get Id of this bird
     *
     * @return unique id
     */
    public Integer getId() {
        return id;
    }

    /**
     * get type of this bird
     */
    public String getType() {
        return type;
    }

    /**
     * set type of bird
     */
    protected void setType(String type) {
        this.type = type;
    }

    /**
     * get bird's characteristic
     */
    public String getCharacteristic() {
        return characteristic;
    }

    /**
     * set bird's characteristic
     */
    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    /**
     * check if bird is extinct
     */
    public Boolean isExtinct() {
        return extinct;
    }

    /**
     * set bird as extinct or not
     */
    public void setExtinct(Boolean extinct) {
        this.extinct = extinct;
    }

    /**
     * get the number of wings of this bird
     */
    public Integer getNumOfWings() {
        return numOfWings;
    }

    /**
     * set the number of wings of this bird
     */
    public void setNumOfWings(Integer numOfWings) {
        this.numOfWings = numOfWings;
    }

    /**
     * get bird's favorite foods
     */
    public Set<Food> getFavoriteFoods() {
        return favoriteFoods;
    }

    /**
     * set bird's favorite foods
     */
    public void setFavoriteFoods(Set<Food> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    /**
     * add several foods as bird's favorite foods
     */
    public void addFavoriteFoods(Set<Food> foods) {
        favoriteFoods.addAll(foods);
    }

    /**
     * add one food as bird's favorite foods
     */
    public void addFavoriteFoods(Food food) {
        favoriteFoods.add(food);
    }

    /**
     * remove one food from bird's favorite foods
     */
    public void removeFavoriteFoods(Food food) {
        favoriteFoods.remove(food);
    }

    /**
     * remove several foods from bird's favorite foods
     */
    public void removeFavoriteFoods(Set<Food> foods) {
        favoriteFoods.removeAll(foods);
    }

    @Override
    public String toString() {
        return "Bird{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", characteristic='" + characteristic + '\'' +
                ", extinct=" + extinct +
                ", numOfWings=" + numOfWings +
                ", favoriteFoods=" + favoriteFoods +
                '}';
    }
}
