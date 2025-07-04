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
public class Vacina
{
   private String nome;
   private double preco;
   private LocalDate dataVencimento;

    public Vacina(String nome, double preco, LocalDate dataVencimento ) {
        this.nome = nome;
        this.preco = preco;
        this.dataVencimento = dataVencimento;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
   
    @Override
    public String toString() {
        return nome + " - R$" + preco + " - Venc: " + dataVencimento;
    }
}