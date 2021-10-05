package entity.environment.enums;

/**
 * @author novo
 * @since 2021/9/28
 */
public enum WaterSource {
    // They live near water sources including wetlands, freshwater and saltwater shorelands, even the ocean.
    WETLANDS(1, "wetlands"),
    FRESHWATER_SHORELANDS(2, "freshwater shorelands"),
    SALTWATER_SHORELANDS(3, "saltwater shorelands"),
    OCEAN(4, "OCEAN");

    private final Integer id;
    private String name;

    WaterSource(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
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
        return "entity.environment.enums.WaterSource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
