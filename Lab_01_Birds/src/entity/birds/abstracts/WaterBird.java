package entity.birds.abstracts;

import entity.environment.enums.Food;
import entity.environment.enums.WaterSource;

import java.util.Set;

/**
 * The abstract class of water bird. Inherited from {@link entity.birds.abstracts.Bird}.
 * Besides from those fundamental attributes, water birds also have water sources.
 *
 * @author novo
 * @since 2021/10/3
 */
public abstract class WaterBird extends Bird {
    private Set<WaterSource> waterSources;

    public WaterBird(String type, String characteristic, Boolean extinct, Integer numOfWings, Set<Food> favoriteFoods, Set<WaterSource> waterSources) {
        super(type, characteristic, extinct, numOfWings, favoriteFoods);
        this.waterSources = waterSources;
    }

    public WaterBird(String type, Set<WaterSource> waterSources) {
        super(type);
        this.waterSources = waterSources;
    }

    /**
     * get water sources that this bird live near
     */
    public Set<WaterSource> getWaterSources() {
        return waterSources;
    }

    /**
     * set water sources
     */
    public void setWaterSources(Set<WaterSource> waterSources) {
        this.waterSources = waterSources;
    }

    /**
     * add one water source to water sources
     */
    public void addWaterSource(WaterSource waterSource) {
        this.getWaterSources().add(waterSource);
    }

    /**
     * remove one water source from water sources
     */
    public void removeWaterSource(WaterSource waterSource) {
        this.getWaterSources().remove(waterSource);
    }
}
