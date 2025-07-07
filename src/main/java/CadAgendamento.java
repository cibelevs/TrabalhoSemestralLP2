import back_end.*; 
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author T-GAMER
 */
public class CadAgendamento extends javax.swing.JFrame {
    private Agenda agendaSelecionada = null;
    private boolean editar;
    /**
     * Creates new form CadAgendamento
     */
    public CadAgendamento() {
        initComponents();
        setLocationRelativeTo(null);
        btmExcluir.setVisible(false);
        this.editar = false;
        botao.setText("AGENDAR CONSULTA");
        
    }
    
    public CadAgendamento(boolean editar) {
        initComponents();
        setLocationRelativeTo(null);
        btmExcluir.setVisible(true);
        TITULO.setText("EDITAR AGENDAMENTO");
        botao.setText("EDITAR CONSULTA");
        this.editar = true;
        
    }
    
    private void agendarConsulta() {
        String nomeAnimal = txtNomeAnimal.getText().trim();
        String cpfTutor = txtCpfTutor.getText().trim();
        String especialidade = (String) comboEspecialidade.getSelectedItem();
        String dataHoraTexto = txtDiaHorario.getText().trim();

        if (nomeAnimal.isEmpty() || cpfTutor.isEmpty() || dataHoraTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
            return;
        }

        Tutor tutor = DadosApp.clinica.getTutores(cpfTutor);
        if (tutor == null) {
            JOptionPane.showMessageDialog(null, "Tutor não encontrado!");
            return;
        }

        Animal animalEncontrado = tutor.getAnimais().stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nomeAnimal))
                .findFirst()
                .orElse(null);

        if (animalEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Animal não encontrado para este tutor!");
            return;
        }

        LocalDateTime dataHora;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dataHora = LocalDateTime.parse(dataHoraTexto, formatter);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Formato de data e hora inválido! Use dd/MM/yyyy HH:mm");
            return;
        }
        
        if (!DadosApp.clinica.horarioDisponivel(dataHora)) {
            JOptionPane.showMessageDialog(null, "Horário inválido ou ocupado.");
            return;
        }
        
        Veterinario vetDisponivel = DadosApp.clinica.getVeterinarios().stream()
                .filter(v -> v.getEspecialidade().equalsIgnoreCase(especialidade))
                .filter(v -> DadosApp.clinica.vetDisponivel(v))
                .findFirst()
                .orElse(null);

        if (vetDisponivel == null) {
            JOptionPane.showMessageDialog(null, "Nenhum veterinário disponível para esta especialidade neste horário.");
            return;
        }

        dataHora = DadosApp.clinica.proximoHorarioDisponivel(dataHora);
        
        Consulta novaConsulta = new Consulta(
                dataHora,
                animalEncontrado,
                true,
                "Consulta agendada via tela",
                "Aguardando atendimento",
                "A definir",
                vetDisponivel
        );
        Agenda agendamento = new Agenda(dataHora,animalEncontrado,especialidade);
        DadosApp.clinica.getConsultas().add(novaConsulta);
        DadosApp.clinica.getAgendamentos().add(agendamento);

        JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso!");
        this.dispose();
    }
    
    public void salvarEdicaoAgendamento(Agenda agendamento) {
        try {
            String novoDataHoraStr = txtDiaHorario.getText();
            txtNomeAnimal.setEditable(false);
            txtCpfTutor.setEditable(false);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime novoDataHora = LocalDateTime.parse(novoDataHoraStr, formatter);

            if (!agendamento.getDiaHorario().equals(novoDataHora) &&
                    !DadosApp.clinica.horarioDisponivel(novoDataHora)) {
                JOptionPane.showMessageDialog(this, "Horário indisponível para agendamento!");
                return;
            }

            agendamento.setDiaHorario(novoDataHora);
            agendamento.setEspecialidade((String) comboEspecialidade.getSelectedItem());

            JOptionPane.showMessageDialog(this, "Agendamento atualizado com sucesso!");
            this.dispose();

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data/hora inválido! Use dd/MM/yyyy HH:mm");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
    }
    
    public void excluirAgendamento(Agenda ag) {
        this.agendaSelecionada = ag;
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir este agendamento?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (resposta == JOptionPane.YES_OPTION) {
            DadosApp.clinica.getAgendamentos().remove(ag);
            JOptionPane.showMessageDialog(this, "Agendamento excluído com sucesso!");
            this.dispose();
        }
    }
    
    public void inserirDados(Agenda ag) {
    this.agendaSelecionada = ag;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    txtDiaHorario.setText(ag.getDiaHorario().format(formatter));
    txtNomeAnimal.setText(ag.getAnimal().getNome());
    txtCpfTutor.setText(ag.getAnimal().getTutor().getCpf());

    txtNomeAnimal.setEditable(false);
    txtCpfTutor.setEditable(false);

    for (int i = 0; i < comboEspecialidade.getItemCount(); i++) {
        if (comboEspecialidade.getItemAt(i).equals(ag.getEspecialidade())) {
            comboEspecialidade.setSelectedIndex(i);
            break;
        }
    }

    botao.setText("EDITAR AGENDAMENTO");
    btmExcluir.setVisible(true);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCpfTutor = new javax.swing.JTextField();
        TITULO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeAnimal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ESPECIALIDADE = new javax.swing.JLabel();
        HORARIO = new javax.swing.JLabel();
        txtDiaHorario = new javax.swing.JTextField();
        botao = new javax.swing.JButton();
        comboEspecialidade = new javax.swing.JComboBox<>();
        btmExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TITULO.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        TITULO.setText("Agendamento de Consultas");

        jLabel2.setText("Nome do Animal:");

        jLabel4.setText("CPF do Tutor:");

        ESPECIALIDADE.setText("Especialidade:");

        HORARIO.setText("Dia e Horario da Consulta:");

        txtDiaHorario.setText("formato: dd/MM/yyyy HH:mm");
        txtDiaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaHorarioActionPerformed(evt);
            }
        });

        botao.setText("AGENDAR CONSULTA");
        botao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoActionPerformed(evt);
            }
        });

        comboEspecialidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cirurgião", "Cardiologista", "Endocrinologista", "Enfermeiro", "Patologista" }));
        comboEspecialidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEspecialidadeActionPerformed(evt);
            }
        });

        btmExcluir.setText("EXCLUIR AGENDAMENTO");
        btmExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TITULO)
                        .addContainerGap(233, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(HORARIO)
                                .addGap(18, 18, 18)
                                .addComponent(txtDiaHorario))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(ESPECIALIDADE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpfTutor, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomeAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btmExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botao, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(TITULO)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCpfTutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ESPECIALIDADE)
                    .addComponent(comboEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HORARIO)
                    .addComponent(txtDiaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botao)
                    .addComponent(btmExcluir))
                .addContainerGap(213, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEspecialidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEspecialidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEspecialidadeActionPerformed

    private void btmExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmExcluirActionPerformed
        // TODO add your handling code here:
        if (agendaSelecionada != null) {
        excluirAgendamento(agendaSelecionada);
        } else {
        JOptionPane.showMessageDialog(this, "Nenhum agendamento selecionado para exclusão.");
        }
    }//GEN-LAST:event_btmExcluirActionPerformed

   
    private void botaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoActionPerformed
        // TODO add your handling code here:
        if(this.editar == false){
            this.agendarConsulta();
        }else{
            salvarEdicaoAgendamento(this.agendaSelecionada);
        }
    }//GEN-LAST:event_botaoActionPerformed

    private void txtDiaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaHorarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CadAgendamento().setVisible(true);
        });
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ESPECIALIDADE;
    private javax.swing.JLabel HORARIO;
    private javax.swing.JLabel TITULO;
    private javax.swing.JButton botao;
    private javax.swing.JButton btmExcluir;
    private javax.swing.JComboBox<String> comboEspecialidade;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCpfTutor;
    private javax.swing.JTextField txtDiaHorario;
    private javax.swing.JTextField txtNomeAnimal;
    // End of variables declaration//GEN-END:variables
}
