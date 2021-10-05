package entity.environment;

import entity.birds.FlightlessBird;
import entity.birds.Owl;
import entity.birds.PreyBird;
import entity.birds.Waterfowl;
import entity.birds.abstracts.Bird;
import entity.birds.enums.BirdType;
import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/4
 */
public class ConservatoryTest {

    private Conservatory conservatory;

    // ======== basic methods ========

    /**
     * initialization
     */
    @Before
    public void setUp() throws Exception {
        conservatory = new Conservatory();
        // add duck to conservatory
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
        conservatory.rescueBird(duck);

        // new aviaries
        Aviary aviary1 = new Aviary(100.2, 80.213);
        Aviary aviary2 = new Aviary(277.112, 188.661);
        conservatory.addAviary(aviary1);
        conservatory.addAviary(aviary2);

        // add eagle to aviary one
        Set<Food> eagleFavoriteFood = new HashSet<>();
        eagleFavoriteFood.add(Food.SMALL_MAMMALS);
        Bird eagle = new PreyBird(BirdType.PreyBirdTypes.EAGLES.getType(),
                "Birds of prey all have sharp, hooked beaks with visible nostrils.",
                false,
                2,
                eagleFavoriteFood);
        conservatory.rescueBird(eagle);
        aviary1.addBird(eagle);
    }

    /**
     * get all birds in the conservatory(including aviaries)
     */
    @Test
    public void getAllBirds() {
        System.out.println(conservatory.getAllBirds());
    }

    /**
     * get all birds in the conservatory(excluding aviaries)
     */
    @Test
    public void getAllBirdsNotInAviary() {
        System.out.println(conservatory.getAllBirdsNotInAviary());
    }

    /**
     * get a List of all these birds in conservatory(including aviaries)
     */
    @Test
    public void getAllBirdsAsList() {
        System.out.println(conservatory.getAllBirdsAsList());
    }

    /**
     * get a List of all birds in the aviaries, with respective location(Aviary location).
     */
    @Test
    public void getAllBirdsWithLocation() {
        System.out.println(conservatory.getAllBirdsWithLocation());
    }

    /**
     * get a Map of all aviaries and respective birds
     */
    @Test
    public void getAviaryAndBirdsAsMap() {
        System.out.println(conservatory.getAviaryAndBirdsAsMap());
    }

    /**
     * get food requirement of foods
     */
    @Test
    public void getFoodsRequirements() {
        System.out.println(conservatory.getFoodsRequirements());

    }

    /**
     * get all aviaries
     */
    @Test
    public void getAllAviaries() {
        System.out.println(conservatory.getAllAviaries());
    }

    /**
     * add an aviary to conservatory
     */
    @Test
    public void addAviary() {
        Aviary aviary3 = new Aviary(300.2, 666.1);
        conservatory.addAviary(aviary3);
        assertEquals(Integer.valueOf(3), (Integer)conservatory.getAllAviaries().size());
    }

    /**
     * remove an aviary from conservatory
     */
    @Test
    public void removeAviary() {
        Aviary aviary3 = new Aviary(300.2, 666.1);
        conservatory.addAviary(aviary3);
        conservatory.removeAviary(aviary3);
    }

    /**
     * assign a bird to an aviary
     */
    @Test
    public void assignBirdToAviary() {
        // Owl
        Set<Food> owlFavoriteFood = new HashSet<>();
        owlFavoriteFood.add(Food.SMALL_MAMMALS);
        Bird owl = new Owl(BirdType.OwlTypes.OWL.getType(),
                "Owls are distinguished by the facial disks that frame the eyes and bill.",
                false,
                2,
                owlFavoriteFood);
        conservatory.rescueBird(owl);
        // get aviary2
        Aviary aviary2 = conservatory.getAllAviaries().get(1);

        conservatory.assignBirdToAviary(owl, aviary2);
    }

    /**
     * rescue a bird
     */
    @Test
    public void rescueBird() {
        // Owl
        Set<Food> owlFavoriteFood = new HashSet<>();
        owlFavoriteFood.add(Food.SMALL_MAMMALS);
        Bird owl = new Owl(BirdType.OwlTypes.OWL.getType(),
                "Owls are distinguished by the facial disks that frame the eyes and bill.",
                false,
                2,
                owlFavoriteFood);
        conservatory.rescueBird(owl);
    }

    /**
     * search for a bird in aviaries
     */
    @Test
    public void searchBird() {
        // search by object
        Bird bird = conservatory.getAllBirdsAsList().get(0);
        System.out.println(bird);
        // search result should not be null
        assertNotNull(bird);
    }

    @Test
    public void printAllSign() {
        // print signs of all these aviaries
        conservatory.printAllSigns();
    }

    // ======== other cases ========

    /**
     * null arguments
     */
    @Test(expected = IllegalArgumentException.class)
    public void addNullAviary() {
        conservatory.addAviary(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assignNullBirdOrNullAviary() {
        conservatory.assignBirdToAviary(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rescueNullBird() {
        conservatory.rescueBird(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchNullBird() {
        conservatory.searchBird(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchNullBirdInAviaries() {
        conservatory.searchBird(null);
    }

    // -------- restrictions --------

    /**
     * maximum of 20 aviaries in the conservatory
     */
    @Test(expected = IllegalStateException.class)
    public void testMaximumAviaries() {
        // new 20 aviaries and add them to conservatory
        for (int i = 0; i < 20; i++) {
            conservatory.addAviary(new Aviary(100.0 + i, 40.0 + i));
        }
    }

    /**
     * No aviary can house more than 5 birds
     */
    @Test(expected = IllegalStateException.class)
    public void testMaximumBirdsInAviary() {
        // get an empty aviary from conservatory
        Aviary aviary = conservatory.getAllAviaries().get(1);
        // new 6 ducks and add them to aviary
        for (int i = 0; i < 6; i++) {
            // add duck to conservatory
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
            // rescue duck
            conservatory.rescueBird(duck);
            // assign to aviary
            conservatory.assignBirdToAviary(duck, aviary);
        }
    }

    /**
     * No extinct birds can be added to an aviary
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddExtinctBird() {
        // we create a moa(which is distinct).
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.FRUIT);
        FlightlessBird moa = new FlightlessBird(BirdType.FlightlessBirdTypes.MOAS,
                "Flightless birds live on the ground and have no (or undeveloped) wings.",
                true,
                0,
                favoriteFoods);
        conservatory.rescueBird(moa);
    }

    /**
     * Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddConflictingBirds() {
        // we've got an eagle in aviary one during setup
        // so we create a duck to test if we could put it into aviary one or not.
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
        // rescue duck
        conservatory.rescueBird(duck);

        // get aviary one
        Aviary aviary = conservatory.getAllAviaries().get(0);
        // try to assign
        conservatory.assignBirdToAviary(duck, aviary);
    }

    /**
     * Get food quantities while no birds exist.
     */
    @Test
    public void testNoBirdsWhileGettingFoodQuantities() {
        // create a new conservatory
        conservatory = new Conservatory();
        Map<Food, Integer> foodsRequirements = conservatory.getFoodsRequirements();
        assertEquals(0, foodsRequirements.size());
    }

    /**
     * Print signs while no birds exist
     */
    @Test
    public void testNoBirdsWhilePrintingSigns() {
        // create a new conservatory
        conservatory = new Conservatory();
        conservatory.printAllSigns();
    }

    /**
     * Search a bird that doesn't exist.
     */
    @Test
    public void testSearchNonexistBird() {
        assertNull(conservatory.searchBird(-1));
    }
}