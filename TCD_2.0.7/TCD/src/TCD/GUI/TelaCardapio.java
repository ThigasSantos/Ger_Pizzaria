/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.GUI;

import TCD.Classes.Acompanhamento;
import TCD.Classes.Ingrediente;
import TCD.DB.AcompanhamentoDao;
import TCD.DB.IngredienteDao;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Declaração da classe da Tela de Cardapio
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class TelaCardapio extends javax.swing.JFrame {

    /** Lista de Ingrediente */
    private List<Ingrediente> ingredientes;
    /** Lista de Acompanhamentos */
    private List<Acompanhamento> acompanhamentos;
    /** Variavel que armazena um novo acompanhamento */
    private Acompanhamento novoAcompanhamento;
    
    /**
     * Creates new form TelaProduto
     */
    public TelaCardapio() {
        initComponents();
        buscarTodos();
        novoAcompanhamento = new Acompanhamento();   
        buscarIngredientes();
         
        }
    
    /**
     * Limpa as text boxes
     */
    private void limparCampos(){
        nomeTF.setText("");
        precoTF.setText("");
        descricaoTA.setText("");
        
    }
    
    /**
     * Busca todos os ingredientes
     */
    public void buscarIngredientes(){
        DefaultListModel model = new DefaultListModel();
        ingredientes = new IngredienteDao().localizarTodos();
        ingredientes.forEach(i->{
        model.addElement(i.toString());
        });
        todosIngredientes.setModel(model);
        atualizarIngredientesdoProduto();
    }
    
    /**
     * Busca todos os acompanhamentos
     */
    private void buscarTodos(){
        acompanhamentos = new AcompanhamentoDao().localizarTodos();
        atualizarLista();
    }
    
    /**
     * Busca o acompanhamento com o filtro de nome
     * @param nome para filtro
     */
     private void buscarTodos(String nome){
        acompanhamentos = new AcompanhamentoDao().buscarPorNome(nome);
        atualizarLista();
    }
    
     /**
      * Atualiza a jlist com as buscas
      */
    private void atualizarLista(){
        DefaultListModel model = new DefaultListModel();
        acompanhamentos.forEach(acomp->{
            model.addElement(acomp.getNome() + " " + acomp.getDescricao()+ " [" + acomp.getCategoria() +"]");
        });
        acompanhamentoList.setModel(model);
    }
    
    /**
     * Atualiza os ingredientes presentes no acompanhamento
     */
    private void atualizarIngredientesdoProduto(){
        DefaultListModel model = new DefaultListModel();
        
        novoAcompanhamento.getIngredientes().forEach(i->{
        model.addElement(i.toString());
        });
        
        ingredientesdoProduto.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        acompanhamentoList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        nomeTF = new javax.swing.JTextField();
        categoriaCB = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricaoTA = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        precoTF = new javax.swing.JTextField();
        salvarBTN = new javax.swing.JButton();
        excluirBTN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        todosIngredientes = new javax.swing.JList<>();
        adicionarIngrediente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        ingredientesdoProduto = new javax.swing.JList<>();
        removerIngrediente = new javax.swing.JButton();
        limparBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        acompanhamentoList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acompanhamentoListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(acompanhamentoList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 63, 290, 137));

        jLabel1.setText("Nome");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 88, -1, -1));

        nomeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeTFActionPerformed(evt);
            }
        });
        nomeTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nomeTFKeyTyped(evt);
            }
        });
        getContentPane().add(nomeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 85, 170, -1));

        categoriaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bebidas", "outros" }));
        categoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaCBActionPerformed(evt);
            }
        });
        getContentPane().add(categoriaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(347, 87, -1, -1));

        jLabel2.setText("Categoria");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        descricaoTA.setColumns(10);
        descricaoTA.setRows(2);
        jScrollPane2.setViewportView(descricaoTA);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 229, 42));

        jLabel3.setText("Descricao");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel4.setText("Preco");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 183, -1, -1));

        precoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precoTFActionPerformed(evt);
            }
        });
        precoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precoTFKeyTyped(evt);
            }
        });
        getContentPane().add(precoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 61, -1));

        salvarBTN.setText("Salvar");
        salvarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBTNActionPerformed(evt);
            }
        });
        getContentPane().add(salvarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        excluirBTN.setText("Excluir");
        excluirBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirBTNActionPerformed(evt);
            }
        });
        getContentPane().add(excluirBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Buscar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 13, -1, -1));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 11, 155, -1));

        jLabel6.setText("__________________________________________________________________________________________________________________________");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, -1, -1));

        jLabel7.setText("___________________________________________________________________________________________________________________________");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 235, -1, -1));

        todosIngredientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(todosIngredientes);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 267, 246, -1));

        adicionarIngrediente.setText("Adicionar Ingrediente ");
        adicionarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarIngredienteActionPerformed(evt);
            }
        });
        getContentPane().add(adicionarIngrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        ingredientesdoProduto.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingredientes do Produto"));
        ingredientesdoProduto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(ingredientesdoProduto);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 267, -1, -1));

        removerIngrediente.setText("Remover Ingrediente");
        removerIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerIngredienteActionPerformed(evt);
            }
        });
        getContentPane().add(removerIngrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 403, -1, -1));

        limparBTN.setText("Limpar Seleção");
        limparBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limparBTNActionPerformed(evt);
            }
        });
        getContentPane().add(limparBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(619, 206, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    // <editor-fold defaultstate="collapsed" desc="Events">
    private void nomeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeTFActionPerformed

    private void categoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaCBActionPerformed
  

    }//GEN-LAST:event_categoriaCBActionPerformed

    private void precoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_precoTFActionPerformed

    private void acompanhamentoListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acompanhamentoListMouseClicked
        if(acompanhamentoList.getSelectedValue() == null )
            return;
        novoAcompanhamento = acompanhamentos.get(acompanhamentoList.getSelectedIndex());
        
        nomeTF.setText(novoAcompanhamento.getNome());
        categoriaCB.setSelectedItem(novoAcompanhamento.getCategoria());
        descricaoTA.setText(novoAcompanhamento.getDescricao());
        precoTF.setText(Float.toString(novoAcompanhamento.getPreco()));
        
        
        atualizarIngredientesdoProduto();
    }//GEN-LAST:event_acompanhamentoListMouseClicked

    private void nomeTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomeTFKeyTyped
       
    }//GEN-LAST:event_nomeTFKeyTyped

    private void salvarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBTNActionPerformed

        if(acompanhamentoList.getSelectedValue() != null)
            novoAcompanhamento = acompanhamentos.get(acompanhamentoList.getSelectedIndex());
      
        
        novoAcompanhamento.setNome(nomeTF.getText());
        novoAcompanhamento.setPreco(Float.parseFloat(precoTF.getText()));
        novoAcompanhamento.setDescricao(descricaoTA.getText());
        novoAcompanhamento.setCategoria((String)categoriaCB.getSelectedItem());
       
        novoAcompanhamento.setId(new AcompanhamentoDao().salvar(novoAcompanhamento));
        
        Acompanhamento acompanhamentoTemp = new Acompanhamento();
        acompanhamentoTemp = new AcompanhamentoDao().localizarPorId(novoAcompanhamento.getId());
        
        for(Ingrediente ing :  acompanhamentoTemp.getIngredientes()){
            if(!novoAcompanhamento.getIngredientes().contains(ing)){
                new AcompanhamentoDao().excluirIngrediente(novoAcompanhamento, ing);
            }
        }
        for(Ingrediente ing :  novoAcompanhamento.getIngredientes()){
            if(!acompanhamentoTemp.getIngredientes().contains(ing)){
                new AcompanhamentoDao().salvarIngredienteEmProduto(novoAcompanhamento, ing);
            }
        }
        
        limparCampos();
        buscarTodos();
        novoAcompanhamento = new Acompanhamento();
    }//GEN-LAST:event_salvarBTNActionPerformed

    private void excluirBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBTNActionPerformed
       if(acompanhamentoList.getSelectedValue() == null)
           return;
       novoAcompanhamento = acompanhamentos.get(acompanhamentoList.getSelectedIndex());
       new AcompanhamentoDao().excluirAcompanhamento(novoAcompanhamento);
       buscarTodos();
       limparCampos();
    }//GEN-LAST:event_excluirBTNActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        String text = jTextField1.getText();
        char evtText = evt.getKeyChar();
        if(evtText != '\b' && evtText != '\n'){
            text = text + evtText;
        }
        buscarTodos(text);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void limparBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limparBTNActionPerformed
        acompanhamentoList.clearSelection();
       
    }//GEN-LAST:event_limparBTNActionPerformed

    private void adicionarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarIngredienteActionPerformed
        if(todosIngredientes.isSelectionEmpty())
            return;
        if( !novoAcompanhamento.getIngredientes().contains(ingredientes.get(todosIngredientes.getSelectedIndex())))
            novoAcompanhamento.getIngredientes().add(ingredientes.get(todosIngredientes.getSelectedIndex()));
        atualizarIngredientesdoProduto();
        
    }//GEN-LAST:event_adicionarIngredienteActionPerformed

    private void removerIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerIngredienteActionPerformed
        if(ingredientesdoProduto.isSelectionEmpty())
            return;
        novoAcompanhamento.getIngredientes().remove(ingredientesdoProduto.getSelectedIndex());
        atualizarIngredientesdoProduto();
        
    }//GEN-LAST:event_removerIngredienteActionPerformed

    private void precoTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precoTFKeyTyped
        String caracteresDisponiveis = "0123456789.";

        if (!caracteresDisponiveis.contains(evt.getKeyChar() + "") || precoTF.getText().length() >= 8) 
            evt.consume();
    }//GEN-LAST:event_precoTFKeyTyped
// </editor-fold>


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> acompanhamentoList;
    private javax.swing.JButton adicionarIngrediente;
    private javax.swing.JComboBox<String> categoriaCB;
    private javax.swing.JTextArea descricaoTA;
    private javax.swing.JButton excluirBTN;
    private javax.swing.JList<String> ingredientesdoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton limparBTN;
    private javax.swing.JTextField nomeTF;
    private javax.swing.JTextField precoTF;
    private javax.swing.JButton removerIngrediente;
    private javax.swing.JButton salvarBTN;
    private javax.swing.JList<String> todosIngredientes;
    // End of variables declaration//GEN-END:variables
}
