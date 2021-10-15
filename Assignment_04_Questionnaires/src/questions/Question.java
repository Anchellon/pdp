package questions;

import java.util.Comparator;

/**
 * @author novo
 * @since 2021/10/14
 */
public interface Question extends Comparable<Question> {
    int getType();
    String getText();
    String answer(String answer);
}
