package io.github.collins993.riddleme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GameScreen extends AppCompatActivity {
    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitBtn;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArrayList;
    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        questionCountLabel = findViewById(R.id.no_question);
        questionLabel = findViewById(R.id.question);
        scoreLabel = findViewById(R.id.score);

        answerEdt = findViewById(R.id.answer);
        submitBtn = findViewById(R.id.submit_btn);
        progressBar = findViewById(R.id.progress);

        questionModelArrayList = new ArrayList<>();

        setQuestion();

        setData();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

    }

    public void checkAnswer() {
        String answerString = answerEdt.getText().toString().trim();
        if (answerString.equalsIgnoreCase(questionModelArrayList.get(currentPosition).getAnswer())) {
            numberOfCorrectAnswer++;

            new SweetAlertDialog(GameScreen.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good Job")
                    .setContentText("Right Answer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPosition++;
                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();

        }
        else{
            new SweetAlertDialog(GameScreen.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Wrong Answer")
                    .setContentText("The Right Answer is : " + questionModelArrayList.get(currentPosition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            currentPosition++;
                            setData();
                            answerEdt.setText("");

                        }
                    })
                    .show();
        }
        int x = ((currentPosition +1) * 100) / questionModelArrayList.size();
        progressBar.setProgress(x);
    }

    private void setQuestion() {
        questionModelArrayList.add(new QuestionModel("What gets wetter and wetter the more it dries?", "Towel"));
        questionModelArrayList.add(new QuestionModel("What kind of room has no doors or windows?", "Mushroom"));
        questionModelArrayList.add(new QuestionModel("What kind of tree can you carry in your hand?", "Palm Tree"));
        questionModelArrayList.add(new QuestionModel("What fruit is always sad?", "Blueberry"));
        questionModelArrayList.add(new QuestionModel("What is it that has a bottom at the top of them?", "Legs"));
        questionModelArrayList.add(new QuestionModel("What gets sharper the more you use it?", "Brain"));
        questionModelArrayList.add(new QuestionModel("What’s black and white and read all over?", "Newspaper"));
        questionModelArrayList.add(new QuestionModel("What has 13 hearts, but no other organs?", "Cards"));
        questionModelArrayList.add(new QuestionModel("What has a head and a tail, but no body?", "Coin"));
        questionModelArrayList.add(new QuestionModel("If you have me, you want to share me. If you share me, you don't have me. What am I?", "Secret"));
        questionModelArrayList.add(new QuestionModel("If I am holding a bee, what do I have in my eye?", "Beauty"));
        questionModelArrayList.add(new QuestionModel("What begins with T, ends with T, and has T in it?", "Teapot"));
        questionModelArrayList.add(new QuestionModel("What is greater than God, more evil than the devil, the poor have it, the rich need it, and if you eat it, you'll die?",  "Nothing"));
        questionModelArrayList.add(new QuestionModel("What has to be broken before you use it?", "Egg"));
        questionModelArrayList.add(new QuestionModel("I cannot talk but will always reply when spoken to. What am I?", "Echo"));
        questionModelArrayList.add(new QuestionModel("I have cities but no houses, forests but no trees, water but no fish. What am I?", "Map"));
        questionModelArrayList.add(new QuestionModel("Eva’s mother had three children. The first was called April, the second was called May. What was the name of the third?", "Eva"));
        questionModelArrayList.add(new QuestionModel("What runs all around a backyard, yet never moves?", "Fence"));
        questionModelArrayList.add(new QuestionModel("What is next in this sequence of numbers: 1, 11, 21, 1211, 111221, 312211, ______?", "13112221"));
        questionModelArrayList.add(new QuestionModel("What is so delicate that saying its name breaks it?", "Silence"));
    }

    public void setData() {
        if (questionModelArrayList.size() > currentPosition) {

            questionLabel.setText(questionModelArrayList.get(currentPosition).getQuestionString());
            questionCountLabel.setText("Question No : " + (currentPosition + 1));
            scoreLabel.setText("Score : " + numberOfCorrectAnswer + "/" + questionModelArrayList.size());
        }
        else{
            new SweetAlertDialog(GameScreen.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("You have successfully completed the game")
                    .setContentText("Your score is : " + numberOfCorrectAnswer + "/" + questionModelArrayList.size())
                    .setConfirmText("Restart")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberOfCorrectAnswer = 0;
                            progressBar.setProgress(0);
                            setData();
                        }
                    })
                    .setCancelText("Close")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            finish();
                        }
                    })
                    .show();
        }

    }

}