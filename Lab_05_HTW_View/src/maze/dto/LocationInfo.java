package maze.dto;

import maze.enums.CellTypeEnum;

import java.util.Set;

/**
 * @author novo
 * @since 2021/12/10
 */
public class LocationInfo {
    private String directions;
    private Set<CellTypeEnum> types;

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Set<CellTypeEnum> getTypes() {
        return types;
    }

    public void setTypes(Set<CellTypeEnum> types) {
        this.types = types;
    }
}
