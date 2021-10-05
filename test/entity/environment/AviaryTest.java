package entity.environment;

import entity.birds.Owl;
import entity.birds.PreyBird;
import entity.birds.Waterfowl;
import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/3
 */
public class AviaryTest {

    private Aviary aviary;

    @Before
    public void setUp() throws Exception {
        // duck
        Set<Food> duckFavoriteFood = new HashSet<>();
        duckFavoriteFood.add(Food.LARVAE);
        Set<WaterSource> waterSources = new HashSet<>();
        waterSources.add(WaterSource.FRESHWATER_SHORELANDS);
        waterSources.add(WaterSource.SALTWATER_SHORELANDS);
        Bird duck = new Waterfowl(BirdType.WaterfowlTypes.DUCK.getType(),
                "Waterfowl are another classification that live near water sources (fresh or salt)",
                false,
                2,
                duckFavoriteFood,
                waterSources);
        aviary = new Aviary(100.2, 80.213);
        aviary.addBird(duck);
    }

    @Test
    public void getAllBirds() {
        List<List<Bird>> birdsList = new ArrayList<>(aviary.getAllBirds().values());
        assertEquals(BirdType.WaterfowlTypes.DUCK.getType(), birdsList.get(0).get(0).getType());
    }

    @Test
    public void getAllBirdsAsList() {
        List<Bird> birds = aviary.getAllBirdsAsList();
        assertEquals(BirdType.WaterfowlTypes.DUCK.getType(), birds.get(0).getType());
    }

    @Test
    public void addBird() {
        // Owl
        Set<Food> owlFavoriteFood = new HashSet<>();
        owlFavoriteFood.add(Food.SMALL_MAMMALS);
        Bird owl = new Owl(BirdType.OwlTypes.OWL.getType(),
                "Owls are distinguished by the facial disks that frame the eyes and bill.",
                false,
                2,
                owlFavoriteFood);
        aviary.addBird(owl);
    }

    @Test
    public void getLocation() {
        assertEquals(100.2, aviary.getLocation().getX(), 0);
        assertEquals(80.213, aviary.getLocation().getY(), 0);
    }

    @Test
    public void setLocation() {
        aviary.setLocation(20.111, 50.222);
        assertEquals(20.111, aviary.getLocation().getX(), 0);
        assertEquals(50.222, aviary.getLocation().getY(), 0);
    }

    @Test
    public void testSetLocation() {
        aviary.setLocation(new Location(20.111, 50.222));
        assertEquals(20.111, aviary.getLocation().getX(), 0);
        assertEquals(50.222, aviary.getLocation().getY(), 0);
    }

    @Test
    public void isConflict() {
        // Owl
        Set<Food> eagleFavoriteFood = new HashSet<>();
        eagleFavoriteFood.add(Food.SMALL_MAMMALS);
        Bird eagle = new PreyBird(BirdType.PreyBirdTypes.EAGLES.getType(),
                "Birds of prey all have sharp, hooked beaks with visible nostrils.",
                false,
                2,
                eagleFavoriteFood);
        assertTrue(aviary.isConflict(eagle));
    }

    @Test
    public void getNumOfBirds() {
        assertEquals(Integer.valueOf(1), aviary.getNumOfBirds());
    }

    @Test
    public void isEmpty() {
        assertFalse(aviary.isEmpty());
    }
}