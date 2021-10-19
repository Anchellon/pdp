package util;

/**
 * Util to auto generate id
 *
 * @author novo
 * @since 2021/10/18
 */
public class CountUtil {
    private static long gearId = 1;
    private static long playerId = 1;
    private static long battleId = 1;

    /**
     * auto generate unique gear id
     *
     * @return new gear id
     */
    public static long generateGearId() {
        return gearId++;
    }

    /**
     * auto generate unique player id
     *
     * @return new player id
     */
    public static long generatePlayerId() {
        return playerId++;
    }

    /**
     * auto generate unique battle id
     *
     * @return new battle id
     */
    public static long generateBattleId() {
        return battleId++;
    }
}
