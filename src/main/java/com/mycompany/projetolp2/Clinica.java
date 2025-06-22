/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

import java.util.ArrayList;

/**
 *
 * @author T-GAMER
 */
public class Clinica {
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Tutor> tutores;
    private ArrayList<Agenda> agendamentos;
    private ArrayList<Vacina> vacinas;
    private ArrayList<Consulta> consultas;

    public Clinica(ArrayList<Veterinario> veterinarios, ArrayList<Funcionario> funcionarios, ArrayList<Tutor> tutores, ArrayList<Agenda> agendamentos, ArrayList<Vacina> vacinas, ArrayList<Consulta> consultas) {
        this.veterinarios = veterinarios;
        this.funcionarios = funcionarios;
        this.tutores = tutores;
        this.agendamentos = agendamentos;
        this.vacinas = vacinas;
        this.consultas = consultas;
    }

    public ArrayList<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(ArrayList<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Tutor> getTutores() {
        return tutores;
    }

    public void setTutores(ArrayList<Tutor> tutores) {
        this.tutores = tutores;
    }

    public ArrayList<Agenda> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(ArrayList<Agenda> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public ArrayList<Vacina> getVacinas() {
        return vacinas;
    }

    public void setVacinas(ArrayList<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    
    
}
