package com.studios91.trabajopractico1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private EditText etCampoDolar, etCampoEuro, etTasaCambio;
    private Button btConvertir, btCambiarValor;
    private RadioButton rbDolar, rbEuro;
    private TextView tvValor;
    private MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVistas();

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        configurarObservadores();

        configurarBotones();
    }

    private void inicializarVistas(){
        etCampoDolar = findViewById(R.id.etCampoDolar);
        etCampoEuro = findViewById(R.id.etCampoEuro);
        rbDolar = findViewById(R.id.rbDolar);
        rbEuro = findViewById(R.id.rbEuro);
        btConvertir = findViewById(R.id.btConvertir);
        btCambiarValor = findViewById(R.id.btCambiarValor);
        etTasaCambio = findViewById(R.id.etTasaCambio);
    }

    private void configurarObservadores(){
        viewModel.getResultado().observe(this, resultado ->{
            if(rbDolar.isChecked()){
                etCampoDolar.setText(resultado);
            } else{
                etCampoEuro.setText(resultado);
            }
        });

        viewModel.getTasaActual().observe((this), tasa ->{
            etTasaCambio.setText(String.valueOf(tasa));
        });

        viewModel.getError().observe((this), mensajeError ->{
            if(mensajeError != null){
                Toast.makeText(this, mensajeError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBotones(){
        btConvertir.setOnClickListener(v ->{
            if(rbEuro.isChecked()){
                viewModel.realizarConversion(etCampoDolar.getText().toString(), true);
            } else{
                viewModel.realizarConversion(etCampoEuro.getText().toString(), false);
            }
        });

        btCambiarValor.setOnClickListener(v ->{
           String nuevaTasa = etTasaCambio.getText().toString();
           viewModel.actualizarTasa(nuevaTasa);
           Toast.makeText(this, "tasa actualizada",Toast.LENGTH_SHORT).show();
        });
    }
}