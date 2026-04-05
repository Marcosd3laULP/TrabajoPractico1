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

import com.studios91.trabajopractico1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(MainActivityViewModel.class);

        configurarObservadores();
        configurarRadioGroup();
        configurarBotones();
    }

    private void configurarRadioGroup(){
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            binding.etCampoDolar.setText("");
            binding.etCampoEuro.setText("");

            if(checkedId == R.id.rbDolar){
                binding.etCampoDolar.setEnabled(false);
                binding.etCampoEuro.setEnabled(true);
                binding.etCampoDolar.requestFocus();
            }else if(checkedId == R.id.rbEuro){
                binding.etCampoEuro.setEnabled(false);
                binding.etCampoDolar.setEnabled(true);
                binding.etCampoEuro.requestFocus();
            }

        });
    }

    private void configurarObservadores(){
        viewModel.getResultado().observe(this, resultado ->{

            if(resultado == null) return;

            if(binding.rbEuro.isChecked()){
                binding.etCampoEuro.setText(resultado);
            } else{
                binding.etCampoDolar.setText(resultado);
            }
        });

        viewModel.getTasaActual().observe((this), tasa ->{
            binding.etTasaCambio.setText(String.valueOf(tasa));
        });

        viewModel.getError().observe((this), mensajeError ->{
            if(mensajeError != null){
                Toast.makeText(this, mensajeError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarBotones(){
        binding.btConvertir.setOnClickListener(v ->{
            String valorEntrada;
            if(binding.rbEuro.isChecked()){
                valorEntrada = binding.etCampoDolar.getText().toString();
                viewModel.realizarConversion(valorEntrada, true);
            } else{
                valorEntrada = binding.etCampoEuro.getText().toString();
                viewModel.realizarConversion(valorEntrada, false);
            }
        });

        binding.btCambiarValor.setOnClickListener(v ->{
           String nuevaTasa = binding.etTasaCambio.getText().toString();
           viewModel.actualizarTasa(nuevaTasa);
           Toast.makeText(this, "tasa actualizada",Toast.LENGTH_SHORT).show();
        });
    }
}