/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

/**
 *
 * @author T-GAMER
 */
public class Consulta {
    private String dataHora;
    private Animal animal;
    private boolean consultaMarcada;
    private String diagnostico, problema,medicamento;
    private Veterinario veterinario;

    public Consulta(String dataHora, Animal animal, boolean consultaMarcada, String diagnostico, String problema, String medicamento, Veterinario veterinario) {
        this.dataHora = dataHora;
        this.animal = animal;
        this.consultaMarcada = consultaMarcada;
        this.diagnostico = diagnostico;
        this.problema = problema;
        this.medicamento = medicamento;
        this.veterinario = veterinario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public boolean isConsultaMarcada() {
        return consultaMarcada;
    }

    public void setConsultaMarcada(boolean consultaMarcada) {
        this.consultaMarcada = consultaMarcada;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    
    
}
