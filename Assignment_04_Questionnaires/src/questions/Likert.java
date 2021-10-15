package questions;

import questions.Question;

/**
 * @author novo
 * @since 2021/10/14
 */
public class Likert implements Question {

    private static final int type = 3;
    private String text;

    public Likert(String text) {
        this.text = text;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String answer(String answer) {
        int ansInt = Integer.parseInt(answer);
        if (ansInt < 1 || ansInt > 5) {
            return "Incorrect";
        }
        return "Correct";
    }

    @Override
    public int compareTo(Question o) {
        if (getType() != o.getType()) {
            return Integer.compare(getType(), o.getType());
        } else {
            return getText().compareTo(o.getText());
        }
    }
}
