package com.example.mobilna;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

class Notatka {
    String tytul;
    String tresc;

    public Notatka(String tytul, String tresc) {
        this.tytul = tytul;
        this.tresc = tresc;
    }

    @Override
    public String toString() {
        return tytul + " - " + tresc;
    }
}

public class MainActivity extends AppCompatActivity {

    private ArrayList<Notatka> notatki;
    private ArrayAdapter<Notatka> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText poleTytul = findViewById(R.id.editTytul);
        EditText poleTresc = findViewById(R.id.editTresc);
        Button przycisk = findViewById(R.id.btnDodaj);
        ListView lista = findViewById(R.id.listViewNotatki);

        notatki = new ArrayList<>();
        notatki.add(new Notatka("Zakupy", "Mleko i chleb"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notatki);
        lista.setAdapter(adapter);

        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tytul = poleTytul.getText().toString();
                String tresc = poleTresc.getText().toString();

                if (!tytul.isEmpty() && !tresc.isEmpty()) {
                    Notatka nowa = new Notatka(tytul, tresc);
                    notatki.add(nowa);
                    adapter.notifyDataSetChanged();
                    poleTytul.setText("");
                    poleTresc.setText("");
                }
            }
        });
    }
}