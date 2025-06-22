/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetolp2;

/**
 *
 * @author T-GAMER
 */
public class RegistroVacina {

    private Vacina vacina;
    private String dataAplicacao, dataValidade;

    
    public RegistroVacina(Vacina vacina, String dataAplicacao, String dataValidade)
    {
        this.vacina = vacina;
        this.dataAplicacao = dataAplicacao;
        this.dataValidade = dataValidade;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    public String getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(String dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }
        
    
}
