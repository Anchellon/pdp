import maze.Maze;
import maze.impl.NonWrappingRoomMaze;
import maze.impl.PerfectMaze;
import maze.impl.WrappingRoomMaze;
import org.junit.Test;

/**
 * @author novo
 * @since 2021/11/29
 */
public class HTWTest {

    Maze maze;

    @Test
    public void testPerfectMaze() {
        maze = new PerfectMaze(5, 5, 2, 2);
    }

    @Test
    public void testWrappingMaze() {
        maze = new WrappingRoomMaze(5, 5, 3, 2, 2);
    }

    @Test
    public void testNonWrappingMaze() {
        maze = new NonWrappingRoomMaze(5, 5, 3, 2, 2);
    }

    /**
     * Test set illegal maze size
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMazeSize() {
        maze = new PerfectMaze(-1, -1, -1, -1);
    }

    /**
     * Test set illegal number of walls
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalNumberOfWalls() {
        maze = new WrappingRoomMaze(3, 3, 10, 1, 1);
    }

    /**
     * Test set illegal start room
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalStartPosition() {
        maze = new PerfectMaze(5, 5, 2, 2);
        maze.setStartLocationByRoomId(-1);
    }

    /**
     * Test set illegal number of arrows
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIllegalNumberOfArrows() {
        maze = new PerfectMaze(5, 5, 2, 2);
        maze.setNumOfArrows(-1);
    }

    /**
     * Move player without setting start location
     */
    @Test(expected = IllegalStateException.class)
    public void testMoveWithoutSettingStartLocation() {
        maze = new PerfectMaze(5, 5, 2, 2);
        maze.movePlayer('n');
    }

    /**
     * Move player with illegal code
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMoveWithIllegalCode() {
        maze = new PerfectMaze(5, 5, 2, 2);
        maze.movePlayer('l');
    }

}
