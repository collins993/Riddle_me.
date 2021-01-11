package io.github.collins993.riddleme;

public class QuestionModel {
    private String questionString;
    private String answer;

    public QuestionModel(String questionString, String answer) {
        this.questionString = questionString;
        this.answer = answer;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
