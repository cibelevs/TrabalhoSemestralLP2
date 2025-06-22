/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
    
    // para marcar consulta.....
    public boolean horarioDisponivel(LocalDateTime dataHora) {
    // Valida se o horário está dentro do intervalo permitido
    LocalTime hora = dataHora.toLocalTime();
    boolean horarioValido = (
        (!hora.isBefore(LocalTime.of(8, 0)) && hora.isBefore(LocalTime.of(12, 0))) ||
        (!hora.isBefore(LocalTime.of(14, 0)) && hora.isBefore(LocalTime.of(18, 0)))
    );
    if (!horarioValido) return false;

    // Verifica se já existe uma consulta marcada nesse horário
    for (Consulta c : consultas) {
        if (c.isConsultaMarcada() && c.getDataHora().equals(dataHora)) {
            return false; // horário ocupado
        }
    }
    return true;
   }

    public LocalDateTime proximoHorarioDisponivel(LocalDateTime inicio) {
    LocalDateTime horario = arredondarProximoHorario(inicio);

    while (!horarioDisponivel(horario)) {
        horario = horario.plusMinutes(20);
        if (!estaDentroDoHorarioAtendimento(horario.toLocalTime())) {
            horario = pularParaProximaFaixa(horario);
        }
    }
    return horario;
}

    // Arredonda para o próximo horário de 20 em 20 minutos
    private LocalDateTime arredondarProximoHorario(LocalDateTime dt) {
        int minuto = dt.getMinute();
        int sobra = minuto % 20;
        if (sobra == 0) return dt;
        return dt.plusMinutes(20 - sobra).withSecond(0).withNano(0);
    }

    // Verifica se está no horário permitido
    private boolean estaDentroDoHorarioAtendimento(LocalTime hora) {
        return (!hora.isBefore(LocalTime.of(8,0)) && hora.isBefore(LocalTime.of(12,0))) ||
               (!hora.isBefore(LocalTime.of(14,0)) && hora.isBefore(LocalTime.of(18,0)));
    }

    // Pula para o próximo período de atendimento (8:00 ou 14:00 do próximo dia)
    private LocalDateTime pularParaProximaFaixa(LocalDateTime dt) {
        LocalTime hora = dt.toLocalTime();
        if (hora.isBefore(LocalTime.of(8,0))) {
            return dt.withHour(8).withMinute(0);
        } else if (hora.isBefore(LocalTime.of(14,0))) {
            return dt.withHour(14).withMinute(0);
        } else {
            return dt.plusDays(1).withHour(8).withMinute(0);
        }
    }
    
    
    public void atendimentoImediato(Animal animal) {
        LocalDateTime agora = LocalDateTime.now();
        if (horarioDisponivel(agora)) {
            //Consulta c = new Consulta(animal, ...etc);
            //consultas.add(c);
            System.out.println("Consulta avulsa realizada agora!");
        } else {
            LocalDateTime proximo = proximoHorarioDisponivel(agora);
            System.out.println("Horário atual ocupado. Próximo disponível: " + proximo);
        }
    }

    
}
