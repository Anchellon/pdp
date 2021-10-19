package util;

/**
 * Util to auto generate id
 *
 * @author novo
 * @since 2021/10/18
 */
public class CountUtil {
    private static int gearId = 0;
    private static int playerId = 0;

    /**
     * auto generate unique gear id
     *
     * @return new gear id
     */
    public static int generateGearId() {
        return gearId++;
    }

    /**
     * auto generate unique player id
     *
     * @return new player id
     */
    public static int generatePlayerId() {
        return playerId++;
    }
}
