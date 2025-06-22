/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

//import java.util.ArrayList;

//O tutor deve ter um cadastro com nome,
//cpf, email, telefone de contato e endereço. 
//Desta forma, um tutor pode ter vários animais sob
//sua responsabilidade

public class Tutor extends Pessoa
{
    private String endereco;
    //private ArrayList<Animal> animais;
    
    public Tutor(String nome, String cpf, String email, String telefone, String endereco){
        super(nome, cpf, email, telefone);
        this.endereco = endereco;
        //animais = new ArrayList<Animal>();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}