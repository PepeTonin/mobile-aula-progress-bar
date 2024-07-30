package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private TextView tvGuessWord;
    private TextView tvFeedback;
    private EditText editTextTriedLetter;
    private Button tryLetterBtn;
    private Button newGameBtn;
    private int progress;
    private String guessWord;
    private String wordPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        tvGuessWord = findViewById(R.id.guessWord);
        tvFeedback = findViewById(R.id.feedbackText);
        editTextTriedLetter = findViewById(R.id.userInput);
        tryLetterBtn = findViewById(R.id.tryLetterBtn);
        newGameBtn = findViewById(R.id.newGameBtn);
        progress = 0;

        initiateGame();
    }

    private void initiateGame() {
        String[] words = {
                "casa", "bola", "livro", "mesa", "luz", "dedo", "roda", "chuva", "festa", "gato",
                "mochila", "banana", "piano", "fogao", "janela", "musica", "filme", "estrada", "escola", "ponte",
                "mar", "sol", "lua", "cama", "vento", "copo", "prato", "porta", "teatro", "arvore",
                "cadeira", "sapato", "relogio", "aviao", "bicicleta", "computador", "telefone", "sorvete", "mangueira",
                "piscina",
                "jardim", "jogador", "pintura", "cachorro", "girafa", "elefante", "tigre", "leao", "zebra", "crocodilo"
        };

        int randomIndex = (int) (Math.random() * words.length);
        this.guessWord = words[randomIndex].toLowerCase();

        this.wordPlaceholder = "";
        for (int i = 0; i < guessWord.length(); i++) {
            this.wordPlaceholder += "_ ";
        }
        tvGuessWord.setText(wordPlaceholder);
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String updatePlaceholder(String letter, String word, String placeholder) {
        char[] wordArray = word.toCharArray();
        char[] placeholderArray = placeholder.toCharArray();
        char guessedChar = letter.charAt(0);

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i] == guessedChar) {
                placeholderArray[i * 2] = guessedChar;
            }
        }

        return new String(placeholderArray);
    }

    private void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    public void tryLetter(View view) {
        String triedLetter = editTextTriedLetter.getText().toString();
        editTextTriedLetter.setText("");

        if (triedLetter.matches("") || isNumeric(triedLetter)) {
            showToastMessage("Insira uma letra válida!");
            return;
        }

        triedLetter = triedLetter.toLowerCase();

        if (this.guessWord.contains(triedLetter)) {
            this.wordPlaceholder = updatePlaceholder(triedLetter, this.guessWord, this.wordPlaceholder);
            tvGuessWord.setText(this.wordPlaceholder);

            if (!this.wordPlaceholder.contains("_")){
                showToastMessage("Você acertou a palavra :)");
                tvFeedback.setText("Você ganhou!");
                tryLetterBtn.setEnabled(false);
                newGameBtn.setVisibility(View.VISIBLE);
                return;
            }

            showToastMessage("Você acertou uma letra!");
            return;
        }

        showToastMessage("Não há essa letra");
        this.progress++;
        progressBarHorizontal.setProgress(this.progress);

        if (progress == 6) {
            showToastMessage("Suas chances acabaram :(");
            tvFeedback.setText("Você perdeu!\nA palavra era: " + guessWord);
            tryLetterBtn.setEnabled(false);
            newGameBtn.setVisibility(View.VISIBLE);
        }
    }

    public void newGame(View view) {
        tryLetterBtn.setEnabled(true);
        newGameBtn.setVisibility(View.GONE);
        tvFeedback.setText("");
        this.progress = 0;
        progressBarHorizontal.setProgress(this.progress);
        initiateGame();
    }
}