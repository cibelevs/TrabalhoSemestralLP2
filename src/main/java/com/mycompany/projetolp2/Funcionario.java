/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

public class Funcionario extends Pessoa
{
    private String turnoTrabalho;
    
    public Funcionario(String nome, String cpf, String email, String telefone, String turnoTrabalho)
    {
        super(nome, cpf, email, telefone);
        this.turnoTrabalho = turnoTrabalho;
    }

    public String getTurnoTrabalho() {
        return turnoTrabalho;
    }

    public void setTurnoTrabalho(String turnoTrabalho) {
        this.turnoTrabalho = turnoTrabalho;
    }
    
}