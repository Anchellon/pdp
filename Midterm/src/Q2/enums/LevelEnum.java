package Q2.enums;

/**
 * Three game levels.
 * Only epic could set bonus.
 * Rate is final.
 *
 * @author novo
 * @since 2021/11/8
 */
public enum LevelEnum {
    NORMAL(1.0, 0), HARD(1.5, 0), EPIC(1.5, 0);

    // rate of each level
    private final double rate;
    // bonus of the level -- only applied to epic
    private int bonus;

    LevelEnum(double rate, int bonus) {
        this.rate = rate;
        this.bonus = bonus;
    }

    public double getRate() {
        return rate;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        if (bonus != 0 && this != EPIC) {
            throw new IllegalStateException("Only Epic could have bonus");
        }
        this.bonus = bonus;
    }
}
