/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import back_end.Agenda;
import back_end.Animal;
import back_end.Consulta;
import back_end.Funcionario;
import back_end.Pessoa;
import back_end.Tutor;
import back_end.Vacina;
import back_end.Veterinario;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author T-GAMER
 */
public class Clinica {
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Tutor> tutores;
    private ArrayList<Agenda> agendamentos;
    private ArrayList<Vacina> vacinas;
    private ArrayList<Consulta> consultas;

    public Clinica(ArrayList<Pessoa> pessoas, ArrayList<Veterinario> veterinarios, ArrayList<Funcionario> funcionarios, ArrayList<Tutor> tutores, ArrayList<Agenda> agendamentos, ArrayList<Vacina> vacinas, ArrayList<Consulta> consultas) {
        this.pessoas = pessoas;
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
    
    public boolean adicionarVeterinario(Veterinario vet) { //encapsulando lógica adição e verificando se já existe por Cpf ou Cfmv 
    for (Veterinario v : veterinarios) {                   //DadosApp.clinica.adicionarVeterinario(vet);
        if (v.getCpf().equals(vet.getCpf()) || v.getNumeroCfmv().equals(vet.getNumeroCfmv())) {
            return false; // já existe
        }
    }
    veterinarios.add(vet);
    return true;
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
    
    public Tutor getTutores(String cpf) {
        for(Tutor t: this.tutores){
            if(t.getCpf().equalsIgnoreCase(cpf)){
                return t;
            }
        }
        return null;
    }
    
    public Animal getAnimais(Tutor tutor, String nomeAnimal){
         for(Animal an : tutor.getAnimais()){
                if(an.getNome().equals(nomeAnimal)){
                    return an;
                }
            }
            return null;
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

    public void setVacinas(Vacina vacina) {
        this.vacinas.add(vacina);
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    

    public void atualizarListaDePessoas() {
        pessoas.clear();
        pessoas.addAll(tutores);
        pessoas.addAll(funcionarios);
        pessoas.addAll(veterinarios);
    }

    public  ArrayList<Pessoa> listaAtual(){
        atualizarListaDePessoas();
        return pessoas;
    }
    // Método para obter a lista de pessoas
    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
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
    
    
    public boolean atendimentoImediato() {
        LocalDateTime agora = LocalDateTime.now();
        if(horarioDisponivel(agora)) {
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
    
     public Veterinario buscarVeterinarioDisponivel(LocalDateTime dataHora) {
        // Verifica se o horário está dentro do período permitido
        LocalTime hora = dataHora.toLocalTime();
        boolean horarioValido = (
            (!hora.isBefore(LocalTime.of(8, 0)) && hora.isBefore(LocalTime.of(12, 0))) ||
            (!hora.isBefore(LocalTime.of(14, 0)) && hora.isBefore(LocalTime.of(18, 0)))
        );

        if (!horarioValido) {
            return null; // horário inválido
        }

        // Para cada veterinário
        for (Veterinario vet : veterinarios) {
            if (vet.isDisponivel()) {
                boolean ocupado = false;

                // Para cada consulta
                for (Consulta c : consultas) {
                    if (c.isConsultaMarcada() &&
                        c.getVeterinario().equals(vet) &&
                        c.getDataHora().equals(dataHora)) {
                        ocupado = true;
                        break;
                    }
                }

                if (!ocupado) {
                    return vet; // achou um disponível
                }
            }
    }

    return null; // nenhum disponível
}



    public boolean vetDisponivel(Veterinario v){
          if(v.isDisponivel()) return true;
        return false;
    }
    
     public Veterinario getVeterinario(String nomeVet){
        for(Veterinario vet: veterinarios){
            if(vet.getNome().equals(nomeVet)) 
                return vet;
        }
        return null;
     }
   
    public Vacina getVacina(String vacina){
        for(Vacina vac: this.vacinas){
            if(vac.getNome().equalsIgnoreCase(vacina)){
                return vac;
            }
        }
        return null;
        
    }
    
    public boolean vacinaExiste(String new_vacina){
        for(Vacina vac:vacinas){
            if(vac.getNome().equalsIgnoreCase(new_vacina)){
                return true;
            }
            
        }
        return false;
    }


}
