package entity.environment.enums;

/**
 * @author novo
 * @since 2021/9/28
 */
public enum Food {
    // A description of what 2-4 items they prefer to eat from the following list:
    // berries, seeds, fruit, insects, other birds, eggs, small mammals,
    // fish, buds, larvae, aquatic invertebrates, nuts, and vegetation.
    BERRIES(1, "berries"),
    SEEDS(2, "seeds"),
    FRUIT(3, "fruit"),
    INSECTS(4, "insects"),
    OTHER_BIRDS(5, "other birds"),
    EGGS(6, "eggs"),
    SMALL_MAMMALS(7, "small mammals"),
    FISH(8, "fish"),
    BUDS(9, "buds"),
    LARVAE(10, "larvae"),
    AQUATIC_INVERTEBRATES(11, "aquatic invertebrates"),
    NUTS(12, "nuts"),
    VEGETATION(13, "vegetation");

    private final Integer id;
    private String name;

    Food(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "entity.environment.enums.Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
