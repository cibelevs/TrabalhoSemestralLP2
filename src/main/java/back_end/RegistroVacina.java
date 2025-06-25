/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.time.LocalDate;

/**
 *
 * @author T-GAMER
 */
public class RegistroVacina {

    private Vacina vacina;
    private LocalDate dataAplicacao;
    private LocalDate dataValidade;

    public RegistroVacina(Vacina vacina, LocalDate dataAplicacao, LocalDate dataValidade) {
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

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    
}
