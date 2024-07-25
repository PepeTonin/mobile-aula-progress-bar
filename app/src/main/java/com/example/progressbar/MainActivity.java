package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBarHorizontal;
    private TextView textView;
    private int progress;
    private String chosedWord;
    private String wordPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarHorizontal = findViewById(R.id.progressBarHorizontal);
        textView = findViewById(R.id.textView);

        String[] words = {
                "casa", "bola", "livro", "mesa", "luz", "dedo", "roda", "chuva", "festa", "gato",
                "mochila", "banana", "piano", "fogao", "janela", "musica", "filme", "estrada", "escola", "ponte",
                "mar", "sol", "lua", "cama", "vento", "copo", "prato", "porta", "teatro", "arvore",
                "cadeira", "sapato", "relogio", "aviao", "bicicleta", "computador", "telefone", "sorvete", "mangueira", "piscina",
                "jardim", "jogador", "pintura", "cachorro", "girafa", "elefante", "tigre", "leao", "zebra", "crocodilo"
        };
        int randomIndex = (int) (Math.random() * words.length);
        chosedWord = words[randomIndex];

        wordPlaceholder = "";
        for (int i=0; i<chosedWord.length(); i++){
            if (i<chosedWord.length()-1){
                wordPlaceholder += "_ ";
            } else {
                wordPlaceholder += "_";
            }
        }

        textView.setText(wordPlaceholder);

    }

    public void tryLetter(View view){

    }
}