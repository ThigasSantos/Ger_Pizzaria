/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.GUI;

import TCD.Classes.Acompanhamento;
import TCD.Classes.Bairro;
import TCD.Classes.Cliente;
import TCD.Classes.Endereco;
import TCD.Classes.Pedido;
import TCD.Classes.Pizza;
import TCD.Classes.Sabor;
import TCD.DB.AcompanhamentoDao;
import TCD.DB.BairroDao;
import TCD.DB.ClienteDao;
import TCD.DB.EnderecoDao;
import TCD.DB.PedidoDao;
import TCD.DB.PizzaDao;
import TCD.DB.SaborDao;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Declaração da classe de Tela de Novo Pedido
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class TelaNovoPedido extends javax.swing.JFrame {
    
    /** Variavel para deixar os floats formatados */
    DecimalFormat df;
    
    /**
     * Creates new form TelaNovoPedido
     */
    public TelaNovoPedido() {
        initComponents();
        categoriaCB.setSelectedItem("pizza");
        pedido = new Pedido();
        pesquisaBairroList.setVisible(false);
        buscar();
         df = new DecimalFormat("0.00");
    }

    /** Lista de clientes */
    private List<Cliente> clientes;
    /** Lista de sabores disponiveis */
    private List<Sabor> saboresList;
    /** Lista de Acompanhamentos disponiveis na categoria selecionada */
    private List<Acompanhamento> acompanhamentosList;
    /** Lista de bairros disponiveis na pesquisa */
    List<Bairro> bairrosList;
    /** Variavel que armazena um pedido */
    private Pedido pedido;
    
    /**
     * Preenche os campos de cliente com base no cliente selecionado
     * @param c cliente selecionado
     */
    public void preencherCampos(Cliente c){
        nomeTF.setText(c.getNome());
        telefoneTF.setText(c.getTelefone());
        dddTF.setText(c.getDDD());
        if(c.getEndereco() == null){
            ruaTF.setText("");        
            numeroTF.setText("");
            complementoTF.setText("");
            bairroTF.setText("");
        }
        else{
            ruaTF.setText(c.getEndereco().getRua());        
            numeroTF.setText(c.getEndereco().getNumero());
            complementoTF.setText(c.getEndereco().getComplemento());
            if(c.getEndereco().getBairro() == null)
                bairroTF.setText("");
            else{
                bairroTF.setText(c.getEndereco().getBairro().getNome());
                taxaLabel.setText("Taxa: " + Float.toString(c.getEndereco().getBairro().getTaxa()));
                entregaTF.setText("Entrega: " + Float.toString(c.getEndereco().getBairro().getTaxa()));
            }
        }
    }
    
    /**
     * Limpa as text boxes
     */
    public void limparCampos(){
        nomeTF.setText("");
        telefoneTF.setText("");
        dddTF.setText("");
        ruaTF.setText("");
        bairroTF.setText("");
        numeroTF.setText("");
        complementoTF.setText("");
    }
    
    /**
     * Atualiza a jlist
     */
    public void atualizarLista(){
        DefaultListModel model = new DefaultListModel();
        clientes.forEach(c->{
        model.addElement(c.toString());});
        jList2.setModel(model);
        jList2.clearSelection();
    }
    
    /**
     * Busca todos os clientes
     */
    public void buscar(){
        clientes = new ClienteDao().localizarTodos();
        atualizarLista();
    }
    
    /**
     * Busca os clientes com base no filtro de nome ou telefone
     * @param text nome ou telefone
     */
    public void buscar(String text){
        clientes =  new ClienteDao().buscarPorNomeTelefone(text);
        atualizarLista();
        
    }
    
    /**
     * Atualiza o preço do pedido
     */
    public void atualizarPreco(){
        if(categoriaCB.getSelectedItem() == "pizza"){
            if(saboresList.get(produtosCB.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()] > saboresList.get(produtosCB2.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()])
                 precoTF.setText("Preço:" + Float.toString((quantidadeCB.getSelectedIndex()+1)*(saboresList.get(produtosCB.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()])));
            else
                precoTF.setText("Preço:" + Float.toString((quantidadeCB.getSelectedIndex()+1)*(saboresList.get(produtosCB2.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()])));
        }else{
            precoTF.setText("Preço:" + Float.toString((quantidadeCB.getSelectedIndex()+1)*(acompanhamentosList.get(produtosCB.getSelectedIndex()).getPreco())));
            
            String[] str2 = new String[1];
            str2[0] = acompanhamentosList.get(produtosCB.getSelectedIndex()).getDescricao();

            produtosCB2.setModel(new javax.swing.DefaultComboBoxModel<>(str2));
            
        }
        subtotallTF.setText("Subtotal: " + df.format(pedido.getPrecoTotal()));
        totalTF.setText("Total: " + df.format(pedido.getPrecoTotal()+ Float.parseFloat(entregaTF.getText().split(":")[1])) );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dddTF = new javax.swing.JTextField();
        telefoneTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        nomeTF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pesquisaBairroList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        bairroTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ruaTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        numeroTF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        complementoTF = new javax.swing.JTextField();
        taxaLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        quantidadeCB = new javax.swing.JComboBox<>();
        categoriaCB = new javax.swing.JComboBox<>();
        produtosCB = new javax.swing.JComboBox<>();
        produtosCB2 = new javax.swing.JComboBox<>();
        bordaCB = new javax.swing.JComboBox<>();
        tamanhoCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        observacoesTA = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        itensPedidoList = new javax.swing.JList<>();
        adicionarBTN = new javax.swing.JButton();
        excluirProdutoBTN = new javax.swing.JButton();
        precoTF = new javax.swing.JLabel();
        totalTF = new javax.swing.JLabel();
        entregaTF = new javax.swing.JLabel();
        subtotallTF = new javax.swing.JLabel();
        pedirBTN = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cliente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Telefone");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));

        dddTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dddTFActionPerformed(evt);
            }
        });
        dddTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dddTFKeyTyped(evt);
            }
        });
        jPanel1.add(dddTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 27, 28, -1));

        telefoneTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneTFKeyTyped(evt);
            }
        });
        jPanel1.add(telefoneTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 27, 132, -1));

        jLabel1.setText("Nome");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 61, -1, -1));

        nomeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeTFActionPerformed(evt);
            }
        });
        jPanel1.add(nomeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 58, 166, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Endereço", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pesquisaBairroList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pesquisaBairroList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pesquisaBairroListMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pesquisaBairroListMousePressed(evt);
            }
        });
        jPanel2.add(pesquisaBairroList, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 48, 200, 80));

        jLabel3.setText("Bairro");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 30, -1, -1));

        bairroTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairroTFActionPerformed(evt);
            }
        });
        bairroTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bairroTFKeyTyped(evt);
            }
        });
        jPanel2.add(bairroTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 27, 200, -1));

        jLabel4.setText("Rua");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 68, -1, -1));
        jPanel2.add(ruaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 65, 200, -1));

        jLabel5.setText("Numero");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 105, -1, -1));
        jPanel2.add(numeroTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 102, 60, -1));

        jLabel9.setText("Complemento");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 136, -1, -1));
        jPanel2.add(complementoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 133, 280, -1));

        taxaLabel.setText("Taxa: 0.0");
        jPanel2.add(taxaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        jList2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList2.setOpaque(false);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedido", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        jPanel3.setLayout(null);

        String[] str = new String[99];
        for (int i = 0; i<99; i ++)
        str[i] = Integer.toString(i+1);
        quantidadeCB.setModel(new javax.swing.DefaultComboBoxModel<>(str));
        quantidadeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeCBActionPerformed(evt);
            }
        });
        jPanel3.add(quantidadeCB);
        quantidadeCB.setBounds(20, 30, 40, 20);

        categoriaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pizza", "bebidas", "outros" }));
        categoriaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaCBActionPerformed(evt);
            }
        });
        jPanel3.add(categoriaCB);
        categoriaCB.setBounds(70, 30, 80, 22);

        produtosCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtosCBActionPerformed(evt);
            }
        });
        jPanel3.add(produtosCB);
        produtosCB.setBounds(16, 70, 250, 22);

        produtosCB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtosCB2ActionPerformed(evt);
            }
        });
        jPanel3.add(produtosCB2);
        produtosCB2.setBounds(16, 98, 250, 22);

        bordaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bordaCBActionPerformed(evt);
            }
        });
        jPanel3.add(bordaCB);
        bordaCB.setBounds(16, 126, 250, 22);

        tamanhoCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamanhoCBActionPerformed(evt);
            }
        });
        jPanel3.add(tamanhoCB);
        tamanhoCB.setBounds(16, 154, 250, 22);

        observacoesTA.setColumns(10);
        observacoesTA.setRows(2);
        observacoesTA.setText("Observações do Pedido\n");
        observacoesTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                observacoesTAMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(observacoesTA);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(280, 10, 440, 48);

        itensPedidoList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        itensPedidoList.setOpaque(false);
        itensPedidoList.setVisibleRowCount(5);
        jScrollPane1.setViewportView(itensPedidoList);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(280, 70, 380, 110);

        adicionarBTN.setText("Adicionar");
        adicionarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarBTNActionPerformed(evt);
            }
        });
        jPanel3.add(adicionarBTN);
        adicionarBTN.setBounds(190, 180, 81, 20);

        excluirProdutoBTN.setText("Excluir Item");
        excluirProdutoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirProdutoBTNActionPerformed(evt);
            }
        });
        jPanel3.add(excluirProdutoBTN);
        excluirProdutoBTN.setBounds(570, 180, 90, 22);

        precoTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        precoTF.setText("Preço: 0.0");
        jPanel3.add(precoTF);
        precoTF.setBounds(20, 180, 170, 15);

        totalTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalTF.setText("Total: 0.0");
        jPanel3.add(totalTF);
        totalTF.setBounds(660, 160, 110, 15);

        entregaTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        entregaTF.setText("Entrega: 0.0");
        jPanel3.add(entregaTF);
        entregaTF.setBounds(660, 140, 110, 15);

        subtotallTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        subtotallTF.setText("Subtotal : 0.0");
        jPanel3.add(subtotallTF);
        subtotallTF.setBounds(660, 120, 120, 15);

        pedirBTN.setText("Pedir");
        pedirBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedirBTNActionPerformed(evt);
            }
        });
        jPanel3.add(pedirBTN);
        pedirBTN.setBounds(710, 190, 60, 22);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Events">
    private void dddTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dddTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dddTFActionPerformed

    private void dddTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dddTFKeyTyped
        String caracteresDisponiveis = "0123456789";
        if (!caracteresDisponiveis.contains(evt.getKeyChar() + "") || dddTF.getText().length() >= 2) {
            evt.consume();
        }
    }//GEN-LAST:event_dddTFKeyTyped

    private void telefoneTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefoneTFKeyTyped
        String caracteresDisponiveis = "0123456789";

        if (!caracteresDisponiveis.contains(evt.getKeyChar() + "") || telefoneTF.getText().length() >= 9) {
            evt.consume();
        } else {
            String text = telefoneTF.getText();
            char evtText = evt.getKeyChar();
            if (evtText != '\b' && evtText != '\n') {
                text = text + evtText;
            }
            if(text == "")
                buscar();
            if (clientes.size() == 1 && telefoneTF.getText().length() >= 8) {
                    preencherCampos(clientes.get(0));
                    jList2.setSelectedIndex(0);
            }
            buscar(text);

        }
    }//GEN-LAST:event_telefoneTFKeyTyped

    private void nomeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeTFActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        if (jList2.getSelectedValue() != null) {
            Cliente c = clientes.get(jList2.getSelectedIndex());

            preencherCampos(c);
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void bairroTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bairroTFKeyTyped
        
        String text = bairroTF.getText();
        char evtText = evt.getKeyChar();
        if (evtText != '\b' && evtText != '\n') {
            text = text + evtText;
        }
        DefaultListModel model = new DefaultListModel();
        bairrosList = new BairroDao().buscarPorNome(text);
        bairrosList.forEach(b -> {
            model.addElement(b.getNome());
        });

        pesquisaBairroList.setVisible(true);
        pesquisaBairroList.setModel(model);
        if (text.length() == 0 || bairrosList.isEmpty()) {
            pesquisaBairroList.setVisible(false);
        }
        taxaLabel.setText("Taxa: " +"0.0");
        entregaTF.setText("Entrega: 0.0");
        
        if(bairrosList.size() == 1 && (bairrosList.get(0).getNome() == null ? text == null : bairrosList.get(0).getNome().equals(text))){
            pesquisaBairroList.setSelectedIndex(0);
            taxaLabel.setText("Taxa: " + Float.toString(new BairroDao().buscarPorNome(text).get(0).getTaxa()));
            entregaTF.setText("Entrega: " + Float.toString(new BairroDao().buscarPorNome(text).get(0).getTaxa()));
            totalTF.setText("Total: " + df.format(pedido.getPrecoTotal()+ Float.parseFloat(entregaTF.getText().split(":")[1])) );
        }
    
            


    }//GEN-LAST:event_bairroTFKeyTyped

    private void bairroTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairroTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bairroTFActionPerformed

    private void categoriaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaCBActionPerformed
        String str[];
        if ("pizza".equals((String) categoriaCB.getSelectedItem())) {

            saboresList = new SaborDao().localizarTodos();
            if(saboresList.size() > 0){
                str = new String[saboresList.size() + 1];

                for (int i = 0; i < saboresList.size(); i++) {
                    str[i] = saboresList.get(i).getNome();

                }

                produtosCB.setModel(new javax.swing.DefaultComboBoxModel<>(str));

                produtosCB2.setModel(new javax.swing.DefaultComboBoxModel<>(str));

                bordaCB.setEnabled(true);
                bordaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"sem Borda", "Catupiry", "Cheddar"}));
                tamanhoCB.setEnabled(true);
                tamanhoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pequena", "Media", "Grande", "Gigante", "Familia"}));

                if (saboresList.get(produtosCB.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()] >= saboresList.get(produtosCB2.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()]) {
                    precoTF.setText(Float.toString(saboresList.get(produtosCB.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()]));
                } else {
                    precoTF.setText(Float.toString(saboresList.get(produtosCB2.getSelectedIndex()).getCategoria().getPreco()[tamanhoCB.getSelectedIndex()]));
                }
            }
            else{
                produtosCB.setModel(new javax.swing.DefaultComboBoxModel<>());

                produtosCB2.setModel(new javax.swing.DefaultComboBoxModel<>());
                bordaCB.setEnabled(true);
                tamanhoCB.setEnabled(true);
            }
        } else{
            produtosCB.setModel(new javax.swing.DefaultComboBoxModel<>());
            produtosCB2.setModel(new javax.swing.DefaultComboBoxModel<>());
            acompanhamentosList = new AcompanhamentoDao().SelectTodosdaCategoria((String) categoriaCB.getSelectedItem());
            bordaCB.setEnabled(false);
            tamanhoCB.setEnabled(false);
            if(!acompanhamentosList.isEmpty())
            {
                str = new String[acompanhamentosList.size()];
            for (int i = 0; i < acompanhamentosList.size(); i++) 
                str[i] = acompanhamentosList.get(i).getNome();              
            produtosCB.setModel(new javax.swing.DefaultComboBoxModel<>(str));
            
            
            String[] str2 = new String[1];
            str2[0] = acompanhamentosList.get(produtosCB.getSelectedIndex()).getDescricao();

            
            produtosCB2.setModel(new javax.swing.DefaultComboBoxModel<>(str2));

            precoTF.setText(Float.toString(acompanhamentosList.get(produtosCB.getSelectedIndex()).getPreco()));
            }

            

        }
    }//GEN-LAST:event_categoriaCBActionPerformed

    private void observacoesTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_observacoesTAMouseClicked


    }//GEN-LAST:event_observacoesTAMouseClicked

    private void adicionarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarBTNActionPerformed
        DecimalFormat df = new DecimalFormat("0.00");
        if (categoriaCB.getSelectedItem() != "pizza") {
            Acompanhamento temp = acompanhamentosList.get(produtosCB.getSelectedIndex());
            temp.setQuantidade(temp.getQuantidade() + Integer.parseInt((String) quantidadeCB.getSelectedItem()));
            pedido.add(temp);

        } else {
            Pizza temp;
            if (produtosCB.getSelectedIndex() == produtosCB2.getSelectedIndex()) {
                temp = new Pizza(saboresList.get(produtosCB.getSelectedIndex()), tamanhoCB.getSelectedIndex());
            } else {
                temp = new Pizza(saboresList.get(produtosCB.getSelectedIndex()), saboresList.get(produtosCB2.getSelectedIndex()), tamanhoCB.getSelectedIndex());
            }

            temp.setBorda((String) bordaCB.getSelectedItem());


            temp.setQuantidade(Integer.parseInt((String) quantidadeCB.getSelectedItem()));
            temp.setId(new PizzaDao().buscarExistente(temp));
            if (temp.getId() == null) {
                temp.setId(new PizzaDao().salvar(temp));
            }

            pedido.add(temp);

        }

        DefaultListModel model = new DefaultListModel();

        pedido.getPizzas().forEach(pizza -> {
            model.addElement(pizza.toString());
        });
        pedido.getAcompanhamento().forEach(acomp -> {
            model.addElement(acomp.toString());
        });
        itensPedidoList.setModel(model);
        pedido.calcularPrecoTotal();
       // subtotallTF.setText("Subtotal: " + df.format(pedido.getPrecoTotal()));
        //totalTF.setText("Total: " + df.format(pedido.getPrecoTotal()+ Float.parseFloat(entregaTF.getText().split(":")[1])) );
        atualizarPreco();
    }//GEN-LAST:event_adicionarBTNActionPerformed

    private void excluirProdutoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirProdutoBTNActionPerformed
        
        if(itensPedidoList.isSelectionEmpty())
            return;
        List<Pizza> pizzastemp=  new ArrayList<>();
        for(Pizza pizza : pedido.getPizzas()){
            if(pizza.toString().equals(itensPedidoList.getSelectedValue())){
                pizzastemp.add(pizza);
            }      
        }
        pizzastemp.forEach(p->{
        pedido.getPizzas().remove(p);});
        
        List<Acompanhamento> acompTemp =  new ArrayList<>();
        for(Acompanhamento acomp : pedido.getAcompanhamento()){
            if(acomp.toString().equals(itensPedidoList.getSelectedValue())){
                acompTemp.add(acomp);
            }            
        }    
        acompTemp.forEach(acomp->{
        pedido.getAcompanhamento().remove(acomp);});
        
        
           //Atualizar Lista 
         DefaultListModel model = new DefaultListModel();

        pedido.getPizzas().forEach(pizza -> {
            model.addElement(pizza.toString());
        });
        pedido.getAcompanhamento().forEach(acomp -> {             
            model.addElement(acomp.toString());
        });
        itensPedidoList.setModel(model);
        pedido.calcularPrecoTotal();
        
         atualizarPreco();
       //subtotallTF.setText("Subtotal: " + df.format(pedido.getPrecoTotal()));
      // totalTF.setText("Total: " + df.format(pedido.getPrecoTotal()+ Float.parseFloat(entregaTF.getText().split(":")[1])) ); 
    }//GEN-LAST:event_excluirProdutoBTNActionPerformed

    private void pedirBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedirBTNActionPerformed
        Cliente cliente = new ClienteDao().localizarPorTelefone(dddTF.getText(), telefoneTF.getText());
        Bairro bairro = new Bairro();
        bairrosList = new BairroDao().buscarPorNome(bairroTF.getText());
       
        if( bairrosList.size() != 1){
             bairro.setTaxa(0F);
             bairro.setNome(bairroTF.getText());
        }
        else{
            bairro = bairrosList.get(0);
        }
        
        Endereco end = new Endereco();
        end.setBairro(bairro);
        end.setRua(ruaTF.getText());
        end.setNumero(numeroTF.getText());
        end.setComplemento(complementoTF.getText());

        if (cliente != null) {
            if (cliente.getEndereco().equals(end)) {
                end.setId(cliente.getEndereco().getId());
            } else {
                bairro.setId(new BairroDao().salvar(bairro));
                end.setBairro(bairro);
                end.setId(new EnderecoDao().salvar(end));
            }
        } else {
            cliente = new Cliente();
            bairro.setId(new BairroDao().salvar(bairro));

            end.setBairro(bairro);

            end.setId(new EnderecoDao().salvar(end));

            cliente.setEndereco(end);
            cliente.setNome(nomeTF.getText());
            cliente.setDDD(dddTF.getText());
            cliente.setTelefone(telefoneTF.getText());

            cliente.setId(new ClienteDao().salvar(cliente));
        }

        pedido.setObservacao(observacoesTA.getText());

        pedido.setCliente(cliente);
        pedido.setEndereco(end);

        PedidoDao pd = new PedidoDao();
        pedido.setId(pd.salvar(pedido));

        pedido.getAcompanhamento().forEach(acomp -> {
            pd.salvarProdutoEmPedido(pedido, acomp);
        });

        pedido.getPizzas().forEach(pizza -> {
            pd.salvarProdutoEmPedido(pedido, pizza);
        });
        
        pedido = new Pedido();

    }//GEN-LAST:event_pedirBTNActionPerformed

    private void pesquisaBairroListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisaBairroListMousePressed
        if(pesquisaBairroList.getSelectedValue() != null){
            bairroTF.setText(bairrosList.get(pesquisaBairroList.getSelectedIndex()).getNome());
            pesquisaBairroList.setVisible(false);
            taxaLabel.setText("Taxa: " + Float.toString(bairrosList.get(pesquisaBairroList.getSelectedIndex()).getTaxa()));
            entregaTF.setText("Entrega: " + Float.toString(bairrosList.get(pesquisaBairroList.getSelectedIndex()).getTaxa()));
            atualizarPreco();
        }
    }//GEN-LAST:event_pesquisaBairroListMousePressed

    private void pesquisaBairroListMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisaBairroListMouseExited
        pesquisaBairroList.setVisible(false);
    }//GEN-LAST:event_pesquisaBairroListMouseExited

    private void produtosCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtosCBActionPerformed
        atualizarPreco();
    }//GEN-LAST:event_produtosCBActionPerformed

    private void produtosCB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtosCB2ActionPerformed
         atualizarPreco();
    }//GEN-LAST:event_produtosCB2ActionPerformed

    private void bordaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bordaCBActionPerformed
         atualizarPreco();
    }//GEN-LAST:event_bordaCBActionPerformed

    private void tamanhoCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamanhoCBActionPerformed
         atualizarPreco();
    }//GEN-LAST:event_tamanhoCBActionPerformed

    private void quantidadeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeCBActionPerformed
        atualizarPreco();
    }//GEN-LAST:event_quantidadeCBActionPerformed
// </editor-fold>
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarBTN;
    private javax.swing.JTextField bairroTF;
    private javax.swing.JComboBox<String> bordaCB;
    private javax.swing.JComboBox<String> categoriaCB;
    private javax.swing.JTextField complementoTF;
    private javax.swing.JTextField dddTF;
    private javax.swing.JLabel entregaTF;
    private javax.swing.JButton excluirProdutoBTN;
    private javax.swing.JList<String> itensPedidoList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nomeTF;
    private javax.swing.JTextField numeroTF;
    private javax.swing.JTextArea observacoesTA;
    private javax.swing.JButton pedirBTN;
    private javax.swing.JList<String> pesquisaBairroList;
    private javax.swing.JLabel precoTF;
    private javax.swing.JComboBox<String> produtosCB;
    private javax.swing.JComboBox<String> produtosCB2;
    private javax.swing.JComboBox<String> quantidadeCB;
    private javax.swing.JTextField ruaTF;
    private javax.swing.JLabel subtotallTF;
    private javax.swing.JComboBox<String> tamanhoCB;
    private javax.swing.JLabel taxaLabel;
    private javax.swing.JTextField telefoneTF;
    private javax.swing.JLabel totalTF;
    // End of variables declaration//GEN-END:variables
}
