package Q2.impl;

import Q2.Run;
import Q2.enums.LevelEnum;
import Q2.util.CountUtil;

/**
 * @author novo
 * @since 2021/11/8
 */
public class RunImpl implements Run {
    // we have an id for each run
    private final int id;
    private final boolean win;
    private final int enemyKilled;
    private final LevelEnum level;

    /**
     * constructor
     *
     * @param win         win or not
     * @param enemyKilled number of enemy been killed
     * @param level       game level
     * @param bonus       bonus
     */
    public RunImpl(boolean win, int enemyKilled, LevelEnum level, int bonus) {
        if (enemyKilled < 0 || bonus < 0) {
            throw new IllegalArgumentException("negative variables");
        }
        if (level == null) {
            throw new IllegalArgumentException("level cannot be null");
        }
        this.win = win;
        this.enemyKilled = enemyKilled;
        this.level = level;
        // bonus will be checked in enum class
        this.level.setBonus(bonus);
        // get unique id
        this.id = CountUtil.createRun();
    }

    /**
     * overload constructor
     *
     * @param win         win or not
     * @param enemyKilled number of enemy been killed
     * @param level       game level
     */
    public RunImpl(boolean win, int enemyKilled, LevelEnum level) {
        this(win, enemyKilled, level, 0);
    }

    /**
     * Since we have a uniform format of all enums,
     * we could simply use one formulation to get score.
     * <p>
     * (win/lose + 2 * enemyKilled) * levelFactor + bonus(if Epic level)
     *
     * @return score
     */
    @Override
    public double getScore() {
        int winScore = win ? 10 : -10;
        return (winScore + 2 * enemyKilled) * level.getRate() + level.getBonus();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int compareTo(Run o) {
        // check null
        if (o == null) {
            throw new IllegalArgumentException("null argument");
        }
        // compare by score
        return Double.compare(this.getScore(), o.getScore());
    }
}
