/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.GUI;

import TCD.Classes.Cliente;
import TCD.Classes.Pedido;
import TCD.DB.ClienteDao;
import TCD.DB.PedidoDao;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * Declaração da classe da Tela de Buscar de Pedidos
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class TelaBuscarPedidos extends javax.swing.JFrame {
    
    /** Lista de Clientes */
    List<Cliente> clientesList;
    /** Lista de Pedidos */
    List<Pedido> pedidos;
    
    /**
     * Creates new form TelaBuscarPedidos
     */
    public TelaBuscarPedidos() {
        initComponents();
        buscarPedido();
    }
    
    /**
     * Atualiza a lista de pedidos
     */
    private void atualizarListaPedidos(){
        DefaultListModel model = new DefaultListModel();
                   
           pedidos.forEach(p->{
           model.addElement(p.toString());
           });
           pedidosList.setModel(model);
    }
    
    /**
     * busca todos os pedidos
     */
    private void buscarPedido(){
        
        pedidos = new PedidoDao().localizarTodos();
        atualizarListaPedidos();       
        
    }
    
    /**
     * Busca os pedidos com o filtro de cliente
     * @param c cliente pra filtro
     */
    private void buscarPedido(Cliente c){
        pedidos = new PedidoDao().buscarPorCliente(c.getId());
        atualizarListaPedidos();
    }
   
    /**
     * Busca pedidos com o filtro de um intervalo de datas
     * @param dataI data inicial da busca
     * @param dataT data final da busca
     */
    private void buscarPedido(String dataI, String dataT){
        if(dataI.length() != 10 || dataT.length() != 10)
            return;
        else{
           String diaI = dataI.substring(0, 2);
           String mesI = dataI.substring(3, 5);
           String anoI = dataI.substring(6, 10);
           
           String diaT = dataT.substring(0, 2);
           String mesT = dataT.substring(3, 5);
           String anoT = dataT.substring(6, 10);
           
           dataI = anoI + "-" + mesI + "-" + diaI + " 00:00:00";
           dataT =  anoT + "-" + mesT + "-" + diaT +" 23:59:59";
           pedidos = new PedidoDao().buscarEntreDatas(dataI, dataT);
           atualizarListaPedidos();
        }            
    }

    /**
     * Atribui valores para melhor visualização
     */
    private void atribuirValoresPedido(){
        Pedido pedidoSelecionado = new Pedido();
        Long index = new Long(pedidosList.getSelectedIndex());
        
        pedidoSelecionado = pedidos.get(pedidosList.getSelectedIndex());
        if(pedidoSelecionado.getCliente() != null){
        nomeLabel.setText("Nome: " + pedidoSelecionado.getCliente().getNome());
        telefoneLabel.setText("Telefone: ("+pedidoSelecionado.getCliente().getDDD() +") " + pedidoSelecionado.getCliente().getTelefone());
        horarioLabel.setText("Horario: " + pedidoSelecionado.getHorario());
        }
        else{
            nomeLabel.setText("Nome: N/A");
        }
        if(pedidoSelecionado.getEndereco() != null){
        
        ruaLabel.setText("rua: " + pedidoSelecionado.getEndereco().getRua());
        numeroLabel.setText("numero: " + pedidoSelecionado.getEndereco().getNumero());
        complementoLabel.setText("complemento: " + pedidoSelecionado.getEndereco().getComplemento());
        if(pedidoSelecionado.getEndereco().getBairro() != null){
        bairroLabel.setText("Bairro: " + pedidoSelecionado.getEndereco().getBairro().getNome());
        taxaLabel.setText("Taxa de Entrega: " + pedidoSelecionado.getEndereco().getBairro().getTaxa());
        precoTotalLabel.setText("Preço Total: " + Float.toString(pedidoSelecionado.getPrecoTotal() + pedidoSelecionado.getEndereco().getBairro().getTaxa()));
        }else
             precoTotalLabel.setText("Preço Total: " + Float.toString(pedidoSelecionado.getPrecoTotal()));
        }
        
        
        subtotalLabel.setText("Subtotal: " + pedidoSelecionado.getPrecoTotal());
        
        observacoesTP.setText("Observações: " + pedidoSelecionado.getObservacao());
        
        DefaultListModel model = new DefaultListModel();
        pedidoSelecionado.getPizzas().forEach(ps->{
            model.addElement(ps);
        });
        pedidoSelecionado.getAcompanhamento().forEach(ps->{
            model.addElement(ps);
        });
        itensPedidoList.setModel(model);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nomeTF = new javax.swing.JTextField();
        dddTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        telefoneTF = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pedidosList = new javax.swing.JList<>();
        pedidoPanel = new javax.swing.JPanel();
        nomeLabel = new javax.swing.JLabel();
        telefoneLabel = new javax.swing.JLabel();
        bairroLabel = new javax.swing.JLabel();
        ruaLabel = new javax.swing.JLabel();
        numeroLabel = new javax.swing.JLabel();
        complementoLabel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itensPedidoList = new javax.swing.JList<>();
        precoTotalLabel = new javax.swing.JLabel();
        taxaLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        observacoesTP = new javax.swing.JTextPane();
        subtotalLabel = new javax.swing.JLabel();
        horarioLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        excluirBTN = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dataInicioTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dataTerminoTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar por Cliente"));

        jLabel1.setText("Nome");

        nomeTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nomeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeTFActionPerformed(evt);
            }
        });

        dddTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        jLabel2.setText("Telefone");

        telefoneTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        telefoneTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefoneTFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(dddTF, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(telefoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(nomeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(dddTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(nomeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pedidosList.setBackground(new java.awt.Color(240, 240, 240));
        pedidosList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pedidosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pedidosList.setOpaque(false);
        pedidosList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pedidosListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pedidosList);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 380, 330));

        pedidoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Pedido"));
        pedidoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomeLabel.setText("Nome:");
        pedidoPanel.add(nomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 190, -1));

        telefoneLabel.setText("Telefone:");
        pedidoPanel.add(telefoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 190, -1));

        bairroLabel.setText("Bairro:");
        pedidoPanel.add(bairroLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 180, -1));

        ruaLabel.setText("Rua:");
        pedidoPanel.add(ruaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 180, -1));

        numeroLabel.setText("Numero:");
        pedidoPanel.add(numeroLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 130, -1));

        complementoLabel.setText("Complemento:");
        pedidoPanel.add(complementoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Endereço de Entrega");
        pedidoPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        itensPedidoList.setBackground(new java.awt.Color(240, 240, 240));
        itensPedidoList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        itensPedidoList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        itensPedidoList.setOpaque(false);
        itensPedidoList.setVisibleRowCount(5);
        jScrollPane2.setViewportView(itensPedidoList);

        pedidoPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 16, 400, 201));

        precoTotalLabel.setText("Preço Total:");
        pedidoPanel.add(precoTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 180, -1));

        taxaLabel.setText("Taxa de Entrega:");
        pedidoPanel.add(taxaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 180, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Preço");
        pedidoPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        observacoesTP.setText("Observaçoes");
        observacoesTP.setOpaque(false);
        observacoesTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                observacoesTPKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(observacoesTP);

        pedidoPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 400, 60));

        subtotalLabel.setText("Subtotal:");
        pedidoPanel.add(subtotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, -1));

        horarioLabel.setText("Horario");
        pedidoPanel.add(horarioLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 190, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Cliente");
        pedidoPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Horario/Data");
        pedidoPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jPanel2.add(pedidoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 620, 340));

        excluirBTN.setText("Excluir");
        excluirBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirBTNActionPerformed(evt);
            }
        });
        jPanel2.add(excluirBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 350, 80, -1));

        jList2.setBackground(new java.awt.Color(240, 240, 240));
        jList2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jList2.setOpaque(false);
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        jButton1.setText("Limpar Seleção");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtrar Por Data"));

        jLabel4.setText("Data Inicio:");

        dataInicioTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dataInicioTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataInicioTFActionPerformed(evt);
            }
        });
        dataInicioTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dataInicioTFKeyTyped(evt);
            }
        });

        jLabel5.setText("Data Termino:");

        dataTerminoTF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dataTerminoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataTerminoTFActionPerformed(evt);
            }
        });
        dataTerminoTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dataTerminoTFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dataInicioTF)
                    .addComponent(dataTerminoTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dataInicioTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dataTerminoTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(57, 57, 57)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

    // <editor-fold defaultstate="collapsed" desc="Event Code"> 
    private void nomeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeTFActionPerformed

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
            
            DefaultListModel model = new DefaultListModel();
            clientesList = new ClienteDao().buscarPorNomeTelefone(text);
            clientesList.forEach(c->{
                model.addElement(c.toString());
            });
            jList2.setModel(model);
            
            
            if (clientesList.size() == 1 && telefoneTF.getText().length() >= 8) {
                nomeTF.setText(clientesList.get(0).getNome());
                dddTF.setText(clientesList.get(0).getDDD());
                jList2.setSelectedIndex(0);
                buscarPedido(clientesList.get(0));
            }
            
        }
    }//GEN-LAST:event_telefoneTFKeyTyped

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        if (jList2.getSelectedValue() != null) {
                Cliente c = clientesList.get(jList2.getSelectedIndex());

                nomeTF.setText(c.getNome());
                telefoneTF.setText(c.getTelefone());
                dddTF.setText(c.getDDD());

                buscarPedido(c);
            
        }
    }//GEN-LAST:event_jList2MouseClicked

    private void dataInicioTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataInicioTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataInicioTFActionPerformed

    private void dataTerminoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataTerminoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataTerminoTFActionPerformed

    private void dataInicioTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataInicioTFKeyTyped
        String caracteresDisponiveis = "0123456789";
        
        if (!caracteresDisponiveis.contains(evt.getKeyChar() + "") || (dataInicioTF.getText()+ evt.getKeyChar()).length() >= 11) {
            evt.consume();
        }
        else {
            String text = dataInicioTF.getText() + evt.getKeyChar();
            if(text.length() == 2 || text.length() == 5){             
                dataInicioTF.setText(text + "/");
                evt.consume();
            }
            if((dataInicioTF.getText()+ evt.getKeyChar()).length() == 10 && evt.getKeyChar() != '\b')
                buscarPedido(dataInicioTF.getText(),dataTerminoTF.getText()+ evt.getKeyChar());
                
        }
        
    }//GEN-LAST:event_dataInicioTFKeyTyped

    private void dataTerminoTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataTerminoTFKeyTyped
        String caracteresDisponiveis = "0123456789";
        
        if (!caracteresDisponiveis.contains(evt.getKeyChar() + "") || (dataTerminoTF.getText()+ evt.getKeyChar()).length() >= 11) {
            evt.consume();
        }
        else {
            String text = dataTerminoTF.getText() + evt.getKeyChar();
            if(text.length() == 2 || text.length() == 5){             
                dataTerminoTF.setText(text + "/");
                evt.consume();
            }
                
        }
        if((dataTerminoTF.getText()+ evt.getKeyChar()).length() == 10 && evt.getKeyChar() != '\b')
            buscarPedido(dataInicioTF.getText(),dataTerminoTF.getText()+ evt.getKeyChar());
            
        
    }//GEN-LAST:event_dataTerminoTFKeyTyped

    private void pedidosListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pedidosListMouseClicked
        if(pedidosList.getSelectedValue() != null){
            atribuirValoresPedido();
            
        }
            
    }//GEN-LAST:event_pedidosListMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        pedidosList.clearSelection();
        jList2.clearSelection();
        buscarPedido();
        atualizarListaPedidos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void observacoesTPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observacoesTPKeyTyped
        evt.consume();
        
    }//GEN-LAST:event_observacoesTPKeyTyped

    private void excluirBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirBTNActionPerformed
        if(pedidosList.isSelectionEmpty())
            return;
        
        new PedidoDao().excluirPedido(pedidos.get(pedidosList.getSelectedIndex()));
        buscarPedido();
    }//GEN-LAST:event_excluirBTNActionPerformed
    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bairroLabel;
    private javax.swing.JLabel complementoLabel;
    private javax.swing.JTextField dataInicioTF;
    private javax.swing.JTextField dataTerminoTF;
    private javax.swing.JTextField dddTF;
    private javax.swing.JButton excluirBTN;
    private javax.swing.JLabel horarioLabel;
    private javax.swing.JList<String> itensPedidoList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField nomeTF;
    private javax.swing.JLabel numeroLabel;
    private javax.swing.JTextPane observacoesTP;
    private javax.swing.JPanel pedidoPanel;
    private javax.swing.JList<String> pedidosList;
    private javax.swing.JLabel precoTotalLabel;
    private javax.swing.JLabel ruaLabel;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel taxaLabel;
    private javax.swing.JLabel telefoneLabel;
    private javax.swing.JTextField telefoneTF;
    // End of variables declaration//GEN-END:variables
}
