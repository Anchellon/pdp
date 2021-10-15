package questions;

import questions.Question;

import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/14
 */
public class MultipleChoice implements Question{

    private static final int type = 1;

    private String text;
    private String correctAnswer;

    private Set<String> options = new HashSet<>();

    public MultipleChoice(String text, String correctAnswer, String option1, String option2, String option3) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
    }

    public MultipleChoice(String text,
                          String correctAnswer,
                          String option1,
                          String option2,
                          String option3,
                          String option4) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
    }

    public MultipleChoice(String text,
                          String correctAnswer,
                          String option1,
                          String option2,
                          String option3,
                          String option4,
                          String option5) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
    }

    public MultipleChoice(String text,
                          String correctAnswer,
                          String option1,
                          String option2,
                          String option3,
                          String option4,
                          String option5,
                          String option6) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
    }

    public MultipleChoice(String text,
                          String correctAnswer,
                          String option1,
                          String option2,
                          String option3,
                          String option4,
                          String option5,
                          String option6,
                          String option7) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        options.add(option7);
    }

    public MultipleChoice(String text,
                          String correctAnswer,
                          String option1,
                          String option2,
                          String option3,
                          String option4,
                          String option5,
                          String option6,
                          String option7,
                          String option8) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        options.add(option7);
        options.add(option8);
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
        if (ansInt < 1 || ansInt > options.size()) {
            return "Incorrect";
        }
        return correctAnswer.equals(answer) ? "Correct" : "Incorrect";
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
