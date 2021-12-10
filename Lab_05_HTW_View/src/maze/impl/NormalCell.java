package maze.impl;

import maze.AbstractCell;
import maze.Location;

/**
 * @author novo
 * @since 2021/10/25
 */
public class NormalCell extends AbstractCell {

    public NormalCell(Location location) {
        super(location);
    }

    public NormalCell(int x, int y) {
        super(x, y);
    }
}
