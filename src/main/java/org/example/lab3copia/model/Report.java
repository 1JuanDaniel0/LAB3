package org.example.lab3copia.model;

public class Report {
    private Double salarioMax;
    private Double salarioMin;
    private Double salarioPromPorTrabajo;
    private String nombreMasPagado;

    // Constructor
    public Report() {

    }

    public Report(Double salarioMax, Double salarioMin, Double salarioPromPorTrabajo, String nombreMasPagado) {
        this.salarioMax = salarioMax;
        this.salarioMin = salarioMin;
        this.salarioPromPorTrabajo = salarioPromPorTrabajo;
        this.nombreMasPagado = nombreMasPagado;
    }

    // Getters y setters
    public Double getSalarioMax() {
        return salarioMax;
    }
    public void setSalarioMax(Double salarioMax) {
        this.salarioMax = salarioMax;
    }
    public Double getSalarioMin() {
        return salarioMin;
    }
    public void setSalarioMin(Double salarioMin) {
        this.salarioMin = salarioMin;
    }
    public Double getSalarioPromPorTrabajo() {
        return salarioPromPorTrabajo;
    }
    public void setSalarioPromPorTrabajo(Double salarioPromPorTrabajo) {
        this.salarioPromPorTrabajo = salarioPromPorTrabajo;
    }
    public String getNombreMasPagado() {
        return nombreMasPagado;
    }
    public void setNombreMasPagado(String nombreMasPagado) {
        this.nombreMasPagado = nombreMasPagado;
    }
}