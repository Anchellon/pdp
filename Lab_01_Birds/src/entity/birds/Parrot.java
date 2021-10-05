package entity.birds;

import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/3
 */
public class Parrot extends Bird {
    private final static String DEFAULT_TYPE = "Parrot";

    private Set<String> words = new HashSet<>();
    private String favoriteSaying;

    public Parrot(String type, String characteristic, Boolean distinct, Integer numOfWings, Set<Food> favoriteFoods) {
        super(type, characteristic, distinct, numOfWings, favoriteFoods);
        // We will give a default type to Parrot.class if user doesn't assign
        if (type == null || type.equals("")) {
            this.setType(DEFAULT_TYPE);
        }
    }

    public Parrot(String type) {
        super(type);
        if (type == null || type.equals("")) {
            setType(DEFAULT_TYPE);
        }
    }

    public Parrot(BirdType.ParrotTypes type) {
        super(type.getType());
    }

    public void setType(BirdType.ParrotTypes type) {
        setType(type.getType());
    }

    /**
     * get number of words that the parrot could say
     */
    public Integer getNumOfWords() {
        return words.size();
    }

    /**
     * get words that the parrot could say
     */
    public Set<String> getWords() {
        return words;
    }

    /**
     * set words the parrot could say
     */
    public void setWords(Set<String> words) {
        this.words = words;
    }

    /**
     * add word to words
     *
     * @param word the word we are going to add
     * @throws IllegalStateException the maximum size of words is 100
     */
    public void addWord(String word) throws IllegalStateException {
        if (words.size() >= 100) {
            throw new IllegalStateException("More than 100 words");
        }
        words.add(word);
    }

    /**
     * remove word from words
     *
     * @param word the word we are going to remove
     */
    public void removeWord(String word) {
        words.remove(word);
    }

    /**
     * get favorite saying
     */
    public String getFavoriteSaying() {
        return favoriteSaying;
    }

    /**
     * set favorite saying
     */
    public void setFavoriteSaying(String favoriteSaying) {
        this.favoriteSaying = favoriteSaying;
    }

    @Override
    public String toString() {
        return "Parrot {" +
                "id=" + getId() +
                ", type=" + getType() +
                ", characteristic=" + getCharacteristic() +
                ", isDistinct=" + isDistinct() +
                ", numOfWings=" + getNumOfWings() +
                ", favoriteFoods=" + getFavoriteFoods() +
                "}";
    }

    public static void main(String[] args) {
        Parrot parrot = new Parrot(BirdType.ParrotTypes.ROSE_RING_PARAKEET);
        System.out.println(parrot);
    }
}
