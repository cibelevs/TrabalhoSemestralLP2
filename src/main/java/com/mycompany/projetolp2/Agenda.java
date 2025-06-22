/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

import java.time.LocalDateTime;

/**
 *
 * @author T-GAMER
 */

public class Agenda  {
    
    private LocalDateTime diaHorario;
    private Animal animal;
    private String especialidade ;

    public Agenda(LocalDateTime diaHorario, Animal animal, String especialidade) {
        this.diaHorario = diaHorario;
        this.animal = animal;
        this.especialidade = especialidade;
    }

    public LocalDateTime getDiaHorario() {
        return diaHorario;
    }

    public void setDiaHorario(LocalDateTime diaHorario) {
        this.diaHorario = diaHorario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}