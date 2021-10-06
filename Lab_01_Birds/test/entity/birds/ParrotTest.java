package entity.birds;

import entity.birds.enums.BirdType;
import entity.environment.enums.Food;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/4
 */
public class ParrotTest {
    private Parrot roseRingParakeet;
    private Parrot grayParrot;
    private Parrot sulfurCrestedCockatoo;

    /**
     * We need to init before each test.
     * So we use @Before instead of @BeforeClass
     */
    @Before
    public void init() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.NUTS);
        roseRingParakeet = new Parrot(BirdType.ParrotTypes.ROSE_RING_PARAKEET.getType(),
                "Parrots have a short, curved beak and are known for their intelligence and ability to mimic sounds. Many pet parrots can learn a vocabulary of up to 100 words and often adopt a single \"favorite\" saying.",
                false,
                2,
                favoriteFoods);

        // add some words
        Set<String> words = new HashSet<>();
        words.add("Hello");
        words.add("World");
        words.add("Hello world");
        roseRingParakeet.setWords(words);

        grayParrot = new Parrot(BirdType.ParrotTypes.GRAY_PARROT);

        sulfurCrestedCockatoo = new Parrot("sulfur crested cockatoo");
    }

    @Test
    public void testToString() {
        System.out.println(roseRingParakeet);
        System.out.println(grayParrot);
        System.out.println(sulfurCrestedCockatoo);
    }

    /**
     * Test getId
     */
    @Test
    public void getId() {
        assertNotNull(roseRingParakeet.getId());
    }

    /**
     * Test getType
     */
    @Test
    public void getType() {
        assertEquals(roseRingParakeet.getType(), BirdType.ParrotTypes.ROSE_RING_PARAKEET.getType());
    }

    @Test
    public void setType() {
        // change roseRingParakeet type to grayParrot
        roseRingParakeet.setType(BirdType.ParrotTypes.GRAY_PARROT);
        // check if we changed successfully
        assertEquals(roseRingParakeet.getType(), BirdType.ParrotTypes.GRAY_PARROT.getType());

        // change back
        roseRingParakeet.setType(BirdType.ParrotTypes.ROSE_RING_PARAKEET);
        // check again
        assertEquals(roseRingParakeet.getType(), BirdType.ParrotTypes.ROSE_RING_PARAKEET.getType());
    }

    @Test
    public void getCharacteristic() {
        assertEquals(roseRingParakeet.getCharacteristic(), "Parrots have a short, curved beak and are known for their intelligence and ability to mimic sounds. Many pet parrots can learn a vocabulary of up to 100 words and often adopt a single \"favorite\" saying.");
    }

    @Test
    public void setCharacteristic() {
        // check if we could change the characteristic
        roseRingParakeet.setCharacteristic("Test to set characteristic");
        assertEquals(roseRingParakeet.getCharacteristic(), "Test to set characteristic");
    }

    @Test
    public void isExtinct() {
        assertFalse(roseRingParakeet.isExtinct());
    }

    @Test
    public void setExtinct() {
        // check if we could change the extinct status
        roseRingParakeet.setExtinct(true);
        assertTrue(roseRingParakeet.isExtinct());
    }

    @Test
    public void getNumOfWings() {
        assertEquals(roseRingParakeet.getNumOfWings(), 2, 0);
    }

    @Test
    public void setNumOfWings() {
        // check if we could change the wings
        roseRingParakeet.setNumOfWings(2);
        System.out.println(roseRingParakeet);
    }

    @Test
    public void getFavoriteFoods() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.NUTS);
        assertArrayEquals(roseRingParakeet.getFavoriteFoods().toArray(), favoriteFoods.toArray());
        System.out.println(roseRingParakeet);

    }

    @Test
    public void setFavoriteFoods() {
        // check if we could change favorite foods
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.OTHER_BIRDS);
        roseRingParakeet.setFavoriteFoods(favoriteFoods);

        // convert favorite foods to array
        assertArrayEquals(roseRingParakeet.getFavoriteFoods().toArray(), favoriteFoods.toArray());
    }

    @Test
    public void addFavoriteFoods() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.FRUIT);
        favoriteFoods.add(Food.NUTS);

        roseRingParakeet.addFavoriteFoods(Food.FRUIT);

        assertArrayEquals(roseRingParakeet.getFavoriteFoods().toArray(), favoriteFoods.toArray());
    }

    @Test
    public void removeFavoriteFoods() {
        roseRingParakeet.removeFavoriteFoods(Food.FISH);
        System.out.println(roseRingParakeet);
    }

    // ------- parrot's unique methods --------

    @Test
    public void getNumOfWords() {
        assertEquals(Integer.valueOf(3), roseRingParakeet.getNumOfWords());
    }

    @Test
    public void getWords() {
        System.out.println(roseRingParakeet.getWords());
    }

    @Test
    public void setWords() {
        Set<String> words = new HashSet<>();
        words.add("Aloha world");
        roseRingParakeet.setWords(words);
        assertEquals(Integer.valueOf(1), roseRingParakeet.getNumOfWords());
    }

    @Test
    public void addWord() {
        roseRingParakeet.getWords().add("I am a parrot");
        assertEquals(Integer.valueOf(4), roseRingParakeet.getNumOfWords());
    }

    @Test
    public void removeWord() {
        roseRingParakeet.getWords().remove("Hello");
        assertEquals(Integer.valueOf(2), roseRingParakeet.getNumOfWords());
    }

    @Test
    public void favoriteSaying() {
        roseRingParakeet.setFavoriteSaying("Hello world");
        assertEquals("Hello world", roseRingParakeet.getFavoriteSaying());
    }
}