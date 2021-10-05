package entity.birds;

import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/4
 */
public class WaterfowlTest {

    private Waterfowl duck;

    @Before
    public void setUp() throws Exception {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.FISH);
        // water source
        Set<WaterSource> waterSources = new HashSet<>();
        waterSources.add(WaterSource.FRESHWATER_SHORELANDS);
        duck = new Waterfowl("duck",
                "Waterfowl are another classification that live near water sources (fresh or salt)",
                false,
                2,
                favoriteFoods,
                waterSources);

    }

    /**
     * test if a bird is waterfowl or not
     */
    @Test
    public void isWaterfowl() {
        Set<Food> favoriteFoods = new HashSet<>();
        favoriteFoods.add(Food.FISH);
        // water source
        Set<WaterSource> waterSources = new HashSet<>();
        waterSources.add(WaterSource.FRESHWATER_SHORELANDS);
        Waterfowl swan = new Waterfowl("swan",
                "",
                false,
                2,
                favoriteFoods,
                waterSources);

        assertTrue(Waterfowl.isWaterfowl(swan));

        // hawk is not a waterfowl
        PreyBird hawk = new PreyBird("hawk",
                "Has sharp, hooked beaks with visible nostrils.",
                false,
                2,
                favoriteFoods);

        assertFalse(Waterfowl.isWaterfowl(hawk));
    }

}