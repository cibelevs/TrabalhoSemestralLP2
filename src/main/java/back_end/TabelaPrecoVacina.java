/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package back_end;

/**
 *
 * @author Home
 */
public class TabelaPrecoVacina {
    
    
     // Vacinas disponíveis
     public static final String ANTIRRABICA = "Vacina Antirrábica";
    public static final String V10 = "Vacina V10";
    public static final String V8 = "Vacina V8";
    public static final String GRIPE_CANINA = "Vacina Gripe Canina";
    
    private final Vacina[] vacinas;

    public TabelaPrecoVacina() {
        vacinas = new Vacina[] {
            new Vacina(ANTIRRABICA, 50.0,null),
            new Vacina(V10, 120.0,null),
            new Vacina(V8, 100.0,null),
            new Vacina(GRIPE_CANINA, 80.0,null)
        };
    }

    /**
     * Obtém o preço de uma vacina pelo nome e retorna o preço
     * @param nomeVacina     
     * @return      
     */
    public double getPreco(String nomeVacina) {
        for (Vacina v : vacinas) {
            if (v.getNome().equalsIgnoreCase(nomeVacina)) {
                return v.getPreco();
            }
        }
      throw new IllegalArgumentException("Vacina não encontrada: " + nomeVacina);
    }
     public Vacina[] getTodasVacinas() {
        return vacinas.clone();
    }
}
