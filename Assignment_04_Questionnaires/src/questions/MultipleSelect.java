package questions;

import questions.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author novo
 * @since 2021/10/14
 */
public class MultipleSelect implements Question {
    private static final int type = 2;

    private String text;
    private String correctAnswer;

    private Set<String> options = new HashSet<>();

    public MultipleSelect(String text, String correctAnswer, String option1, String option2, String option3) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        options.add(option1);
        options.add(option2);
        options.add(option3);
    }

    public MultipleSelect(String text,
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

    public MultipleSelect(String text,
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

    public MultipleSelect(String text,
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

    public MultipleSelect(String text,
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

    public MultipleSelect(String text,
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
        String[] ansArr = answer.split(" ");
        for (String ans : ansArr) {
            int ansInt = Integer.parseInt(ans);
            if (ansInt < 1 || ansInt > 5) {
                return "Incorrect";
            }
        }
        return checkAnswer(answer) ? "Correct" : "Incorrect";
    }

    private boolean checkAnswer(String answer) {
        char[] ansChar = answer.toCharArray();
        Arrays.sort(ansChar);
        char[] corrAns = correctAnswer.toCharArray();
        Arrays.sort(corrAns);
        return new String(ansChar).equals(new String(corrAns));
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
