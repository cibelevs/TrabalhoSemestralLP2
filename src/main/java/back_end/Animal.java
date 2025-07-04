/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.util.ArrayList;

public class Animal 
{
    //Todo animal atendido na clínica deve
    //ser cadastrado como nome, raça, data de nascimento e tutor
    private String nome, raca;
    private String dataNascimento; //coloquei em string 
    private Tutor tutor;
    private ArrayList<RegistroVacina> vacinasTomadas;

    public Animal(String nome, String raca, String dataNascimento, Tutor tutor, ArrayList<RegistroVacina> vacinasTomadas) {
        this.nome = nome;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.tutor = tutor;
        this.vacinasTomadas = vacinasTomadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public ArrayList<RegistroVacina> getVacinasTomadas() {
        return vacinasTomadas;
    }

    public void setVacinasTomadas(ArrayList<RegistroVacina> vacinasTomadas) {
        this.vacinasTomadas = vacinasTomadas;
    }

    
    
}