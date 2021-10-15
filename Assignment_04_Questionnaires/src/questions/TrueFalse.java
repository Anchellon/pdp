package questions;

import questions.Question;

/**
 * @author novo
 * @since 2021/10/14
 */
public class TrueFalse implements Question {

    private static final int type = 0;
    private String text;
    private String correctAnswer;

    public TrueFalse(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
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
        if (!answer.equals("True") && !answer.equals("False")) {
            return "Incorrect";
        }
        return answer.equals(correctAnswer) ? "Correct" : "Incorrect";
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
