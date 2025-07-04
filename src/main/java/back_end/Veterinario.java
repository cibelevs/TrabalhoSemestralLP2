/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

//veterinários que devem ser cadastrados
// indicando nome, cpf, email, telefone, especialidade 
// e o número do CFMV (conselho federal de medicina veterinária 

public class Veterinario extends Pessoa
{
    private String especialidade, numeroCfmv;
    private double precoConsulta;
    
    public Veterinario(String nome, String cpf, String email, String telefone, 
            String especialidade, String numeroCfmv, double precoConsulta)
    {
        super(nome, cpf, email, telefone);
        this.especialidade = especialidade;
        this.numeroCfmv = numeroCfmv;
        this.precoConsulta = precoConsulta;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNumeroCfmv() {
        return numeroCfmv;
    }

    public void setNumeroCfmv(String numeroCfmv) {
        this.numeroCfmv = numeroCfmv;
    }
    
    public double getPrecoConsulta() {
        return precoConsulta;
    }
    
    public void setPrecoConsulta(double precoConsulta) {
        this.precoConsulta = precoConsulta;
    }
    
    @Override
    public String getDados(){
        return "Especialidade: " + this.getEspecialidade() + "\n" +
                "Numero CFMV: " + this.getNumeroCfmv() + "\n";      
    }
  
}
