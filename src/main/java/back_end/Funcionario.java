/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

public class Funcionario extends Pessoa
{



    private String turnoTrabalho, Funcao;

    public Funcionario(String turnoTrabalho, String Funcao, String nome, String cpf, String email, String telefone) {
        super(nome, cpf, email, telefone);
        this.turnoTrabalho = turnoTrabalho;
        this.Funcao = Funcao;

    }
    



    public String getTurnoTrabalho() {
        return turnoTrabalho;
    }

    public void setTurnoTrabalho(String turnoTrabalho) {
        this.turnoTrabalho = turnoTrabalho;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String Funcao) {
        this.Funcao = Funcao;
    }

    @Override
  public String getDados(){
      return "Turno de Trabalho: " + this.getTurnoTrabalho() + "\n" +
              "Funcao: " + this.getFuncao();
  }
    
    
    

    
    
}