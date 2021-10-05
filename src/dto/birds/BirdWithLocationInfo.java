package dto.birds;

import entity.birds.abstracts.Bird;
import entity.environment.Location;

/**
 * @author novo
 * @since 2021/10/3
 */
public class BirdWithLocationInfo {
    private Bird bird;
    private Location location;

    public BirdWithLocationInfo(Bird bird, Double x, Double y) {
        this.bird = bird;
        this.location = new Location(x, y);
    }

    public BirdWithLocationInfo(Bird bird, Location location) {
        this.bird = bird;
        this.location = location;
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public Double getX() {
        return location.getX();
    }

    public void setX(Double x) {
        this.location.setX(x);
    }

    public Double getY() {
        return location.getY();
    }

    public void setY(Double y) {
        this.location.setY(y);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
