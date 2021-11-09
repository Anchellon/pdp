import Q2.Run;
import Q2.TopScoreTracker;
import Q2.enums.LevelEnum;
import Q2.impl.RunImpl;
import Q2.impl.TopScoreTrackerImpl;
import org.junit.Test;

/**
 * @author novo
 * @since 2021/11/8
 */
public class TopScoreTest {

    @Test
    public void test() {
        TopScoreTracker score = new TopScoreTrackerImpl(1, 4);

        // create runs
        // (10 + 20) * 1 = 30
        Run run1 = new RunImpl(true, 10, LevelEnum.NORMAL);
        // (-10 + 0) * 1.5 = -15
        Run run2 = new RunImpl(false, 0, LevelEnum.HARD);
        // (10 + 200) * 1.5 = 315
        Run run3 = new RunImpl(true, 100, LevelEnum.HARD);
        // (-10 + 20) * 1.5 + 25 = 40
        Run run4 = new RunImpl(false, 10, LevelEnum.EPIC, 25);

//        score.addRun(0, run1);
//        score.addRun(0, run2);
//        score.addRun(2, run3);
//        score.addRun(3, run4);

        System.out.println(score.annualReport());
    }
}
