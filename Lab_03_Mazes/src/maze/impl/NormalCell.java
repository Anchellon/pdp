package maze.impl;

import maze.AbstractCell;
import maze.Location;
import maze.enums.CellTypeEnum;

/**
 * @author novo
 * @since 2021/10/25
 */
public class NormalCell extends AbstractCell {

    public NormalCell(Location location) {
        super(CellTypeEnum.NORMAL, location);
    }

    public NormalCell(int x, int y) {
        super(CellTypeEnum.NORMAL, x, y);
    }
}
