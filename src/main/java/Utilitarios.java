/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmita
 */
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class Utilitarios {
    public static boolean validaCampos(List<JTextField> campos) {
        for (JTextField campo : campos) {
            if (campo.getText() == null || campo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
                campo.requestFocus(); 
                return true;  
            }
        }
        return false; 
    }
    
     public static boolean validarCampos(List<JTextComponent> campos) {
        for (JTextComponent campo : campos) {
            if (campo.getText() == null || campo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
                campo.requestFocus(); 
                return true;  
            }
        }
        return false; 
    }
    
    public static void limparCampos(List<JTextField> campos) {
        for (JTextField campo : campos) {
               campo.setText("");
        }
    }
}

