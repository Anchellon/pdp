package Q2.util;

/**
 * @author novo
 * @since 2021/11/8
 */
public class CountUtil {
    private static int runId = 0;
    public static int createRun() {
        return ++runId;
    }
}
