/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.util.ArrayList;

//O tutor deve ter um cadastro com nome,
//cpf, email, telefone de contato e endereço. 
//Desta forma, um tutor pode ter vários animais sob
//sua responsabilidade

public class Tutor extends Pessoa
{
    private String endereco;
    private ArrayList<Animal> animais;
    
    public Tutor(String nome, String cpf, String email, String telefone, String endereco){
        super(nome, cpf, email, telefone);
        this.endereco = endereco;
        animais = new ArrayList<>();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(ArrayList<Animal> animais) {
        this.animais = animais;
    }

   public String imprimirAnimais() {
    StringBuilder sb = new StringBuilder();

    if (animais.isEmpty()) {
        sb.append("\nEste tutor não possui animais cadastrados.");
    } else {
        sb.append("\nAnimais do Tutor:");
        for (Animal an : animais) {
            sb.append("\n - Nome: ").append(an.getNome());
            // adicione outros dados do animal se quiser
        }
    }

    return sb.toString();
}

    
    @Override
    public String getDados(){
        return "\nEndereco: " + this.getEndereco() + "\n" +
               imprimirAnimais();
    }
   
    
}