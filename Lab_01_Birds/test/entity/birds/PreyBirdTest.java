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
 * @since 2021/9/28
 */
public class PreyBirdTest {

    private PreyBird hawk;
    private PreyBird eagle;
    private PreyBird osprey;

    /**
     * We need to init before each test.
     * So we use @Before instead of @BeforeClass
     */
    @Before
    public void init() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.AQUATIC_INVERTEBRATES);
        favoriteFoods.add(Food.FISH);
        hawk = new PreyBird("hawk",
                "Has sharp, hooked beaks with visible nostrils.",
                false,
                2,
                favoriteFoods);

        eagle = new PreyBird(BirdType.PreyBirdTypes.EAGLES);

        osprey = new PreyBird("osprey");
    }

    @Test
    public void testToString() {
        System.out.println(hawk);
        System.out.println(eagle);
        System.out.println(osprey);
    }

    /**
     * Test getId
     */
    @Test
    public void getId() {
        assertNotNull(hawk.getId());
    }

    /**
     * Test getType
     */
    @Test
    public void getType() {
        assertEquals(hawk.getType(), BirdType.PreyBirdTypes.HAWKS.getType());
    }

    @Test
    public void setType() {
        // change hawk type to eagle
        hawk.setType(BirdType.PreyBirdTypes.EAGLES);
        // check if we changed successfully
        assertEquals(hawk.getType(), BirdType.PreyBirdTypes.EAGLES.getType());

        // change back
        hawk.setType(BirdType.PreyBirdTypes.HAWKS);
        // check again
        assertEquals(hawk.getType(), BirdType.PreyBirdTypes.HAWKS.getType());
    }

    @Test
    public void getCharacteristic() {
        assertEquals(hawk.getCharacteristic(), "Has sharp, hooked beaks with visible nostrils.");
    }

    @Test
    public void setCharacteristic() {
        // check if we could change the characteristic
        hawk.setCharacteristic("Test to set characteristic");
        assertEquals(hawk.getCharacteristic(), "Test to set characteristic");
    }

    @Test
    public void isDistinct() {
        assertFalse(hawk.isDistinct());
    }

    @Test
    public void setDistinct() {
        // check if we could change the distinct status
        hawk.setDistinct(true);
        assertTrue(hawk.isDistinct());
    }

    @Test
    public void getNumOfWings() {
        assertEquals(hawk.getNumOfWings(), 2, 0);
    }

    @Test
    public void setNumOfWings() {
        // check if we could change the wings
        hawk.setNumOfWings(2);
        System.out.println(hawk);
    }

    @Test
    public void getFavoriteFoods() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.AQUATIC_INVERTEBRATES);
        favoriteFoods.add(Food.FISH);
        assertArrayEquals(hawk.getFavoriteFoods().toArray(), favoriteFoods.toArray());
        System.out.println(hawk);

    }

    @Test
    public void setFavoriteFoods() {
        // check if we could change favorite foods
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.OTHER_BIRDS);
        hawk.setFavoriteFoods(favoriteFoods);

        // convert favorite foods to array
        assertArrayEquals(hawk.getFavoriteFoods().toArray(), favoriteFoods.toArray());
    }

    @Test
    public void addFavoriteFoods() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.AQUATIC_INVERTEBRATES);
        favoriteFoods.add(Food.FISH);
        favoriteFoods.add(Food.OTHER_BIRDS);

        hawk.addFavoriteFoods(Food.OTHER_BIRDS);

        assertArrayEquals(hawk.getFavoriteFoods().toArray(), favoriteFoods.toArray());
    }

    @Test
    public void removeFavoriteFoods() {
        hawk.removeFavoriteFoods(Food.FISH);
        System.out.println(hawk);
    }
}
