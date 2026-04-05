package com.studios91.trabajopractico1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private Conversor conversor = new Conversor();

    private MutableLiveData<String> resultado = new MutableLiveData();
    private MutableLiveData<String> error = new MutableLiveData();
    private MutableLiveData<Double> tasaActual = new MutableLiveData();

    public MainActivityViewModel(@NonNull Application application){
        super(application);
        tasaActual.setValue(conversor.getTasaDeCambio());
    }

    public LiveData<String> getResultado() { return resultado; }
    public LiveData<String> getError() { return error; }
    public LiveData<Double> getTasaActual() { return tasaActual; }

    public void realizarConversion (String entrada, boolean aEuros ){
        if(entrada == null || entrada.isEmpty()){
            error.setValue("Ingrese un valor para poder hacer la conversion");
            return;
        }

        try{
            double valor = Double.parseDouble(entrada);
            double res;

            if(aEuros){
                res = conversor.convertirAEuros(valor);
            } else{
                res = conversor.convertirADolares(valor);
            }
            resultado.setValue(String.format("%.2f", res));
            error.setValue(null);
        } catch (NumberFormatException e) {
            error.setValue("Solo puede ingresar valores numericos validos");
        }
    }

    public void actualizarTasa(String nuevaTasaStr){
        try{
            double nuevaTasa = Double.parseDouble(nuevaTasaStr);
            conversor.setTasaDeCambio(nuevaTasa);
        }catch(NumberFormatException e){
            error.setValue("Tasa no valida");
        }
    }
}
