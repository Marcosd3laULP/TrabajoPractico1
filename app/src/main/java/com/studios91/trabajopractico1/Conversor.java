package com.studios91.trabajopractico1;

public class Conversor {
    private double tasaDeCambio = 0.92; // Ejemplo: 1 USD = 0.92 EUR

    public double getTasaDeCambio() {
        return tasaDeCambio;
    }

    public void setTasaDeCambio(double nuevaTasa) {
        this.tasaDeCambio = nuevaTasa;
    }

    // Lógica pura de negocio: solo cálculos matemáticos
    public double convertirAEuros(double dolares) {
        return dolares * tasaDeCambio;
    }

    public double convertirADolares(double euros) {
        return euros / tasaDeCambio;
    }
}
