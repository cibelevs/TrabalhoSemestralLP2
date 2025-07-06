/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

import java.time.LocalDate;
import java.util.ArrayList;



/**
 *
 * @author asmita
 */
public class Popular {
    
    public Popular(){
        Tutor tutor = new Tutor("ana", "06485556633", "email", "telefone", "rua 1");
        this.popularTutores(tutor);
        this.popularFuncionario();
        this.popularVeterinario();
        this.popularAnimal(tutor);
        this.popularVacina();
    }
   
    
    
    private void popularTutores(Tutor tutor){
        
        
        DadosApp.clinica.getTutores().add(tutor);
    }
    private void popularFuncionario(){
        Funcionario funcionario = new Funcionario("Manhã", "Atendente", "carlos", "000000", "carlos0000", "721243");
        DadosApp.clinica.getFuncionarios().add(funcionario);
    }
    
    private void popularVeterinario(){
        Veterinario veterinario = new Veterinario("Cirurgião" , "719933431", 450, true, "alita", "555555", "124123124" ,"aluta454545");
        DadosApp.clinica.adicionarVeterinario(veterinario);
        
    }
    private void popularAnimal(Tutor tutor){
        Animal animal = new Animal("paco","pitbul","2020-05-04",tutor,new ArrayList<>() );
        tutor.getAnimais().add(animal);
    }
    
    private void popularVacina(){
        
        Vacina vacina = new Vacina("cura",250,LocalDate.parse("2025-06-11"));
        DadosApp.clinica.setVacinas(vacina);
    }
    
    
}
