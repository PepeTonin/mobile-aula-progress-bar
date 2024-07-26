package com.example.progressbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private TextView tvGuessWord;
    private TextView tvFeedback;
    private EditText editTextTriedLetter;
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

        String[] words = {
                "casa", "bola", "livro", "mesa", "luz", "dedo", "roda", "chuva", "festa", "gato",
                "mochila", "banana", "piano", "fogao", "janela", "musica", "filme", "estrada", "escola", "ponte",
                "mar", "sol", "lua", "cama", "vento", "copo", "prato", "porta", "teatro", "arvore",
                "cadeira", "sapato", "relogio", "aviao", "bicicleta", "computador", "telefone", "sorvete", "mangueira", "piscina",
                "jardim", "jogador", "pintura", "cachorro", "girafa", "elefante", "tigre", "leao", "zebra", "crocodilo"
        };
        int randomIndex = (int) (Math.random() * words.length);
        guessWord = words[randomIndex].toLowerCase();

        wordPlaceholder = "";
        for (int i = 0; i<guessWord.length(); i++){
            wordPlaceholder += "_ ";
        }
        tvGuessWord.setText(wordPlaceholder);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String setPlaceholder(@NonNull String guessWord, String triedLetter, String oldPlaceholder){
        String updatedPlaceholder = "";
        int helperIndexForPlaceholder;
        String letterInWord;
        String valueInPlaceholder;
        for (int i=0; i<guessWord.length(); i++){
            helperIndexForPlaceholder = i*2 - 1;
            valueInPlaceholder = String.valueOf(oldPlaceholder.charAt(helperIndexForPlaceholder));
            letterInWord = String.valueOf(guessWord.charAt(i));
            if (letterInWord.equals(triedLetter)){
                updatedPlaceholder += triedLetter + " ";
            } else if (!valueInPlaceholder.equals("_")) {
                updatedPlaceholder += valueInPlaceholder + " ";
            } else {
                updatedPlaceholder += "_ ";
            }
        }
        return updatedPlaceholder;
    }

    public void tryLetter(View view){
        String triedLetter = editTextTriedLetter.getText().toString().toLowerCase();

        if (isNumeric(triedLetter)) {
            tvFeedback.setText("Insira uma letra válida!");
            return;
        }

        if (guessWord.contains(triedLetter)){
            wordPlaceholder = setPlaceholder(guessWord, triedLetter, wordPlaceholder);
            tvGuessWord.setText(wordPlaceholder);
            return;
        }

        tvFeedback.setText("Não há essa letra");
        progress ++;
        progressBarHorizontal.setProgress(progress);

    }
}