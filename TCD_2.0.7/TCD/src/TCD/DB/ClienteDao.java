/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Cliente;
import TCD.Classes.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Declaração da classe DAO de Cliente
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class ClienteDao extends AbstractDao<Cliente, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO PESSOAS (NOME, DDD, TELEFONE, CARGO, ENDERECOSID) VALUES (?, ?, ?, 'cliente', ?)";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM PESSOAS WHERE ID = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM PESSOAS WHERE CARGO = 'cliente'";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE PESSOAS SET NOME = ?, DDD = ?, TELEFONE = ?, CARGO = 'cliente', ENDERECOSID = ? WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM PESSOAS WHERE ID = ?";
    }
    /**
     * Insere o valor da chave primária na senteça SQL específica para seu uso.
     *
     * @param pstmt Declaração previamente preparada.
     * @param id Chave primária a ser inserida na sentença SQL.
     */
    @Override
    public void ajustarIdDeclaracao(PreparedStatement pstmt, Long id) {
        try {
            // TODO Rever procedimento para suportar tipo genérico "K"
            pstmt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o cliente para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Cliente o) {
        try {
            
                pstmt.setString(1, o.getNome());            
                pstmt.setString(2, o.getDDD());
                pstmt.setString(3, o.getTelefone());
                pstmt.setLong(4, o.getEndereco().getId());
                if (o.getId() != null){
                    pstmt.setLong(5, o.getId());
                }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Ajusta a declaração de busca
     * @param pstmt declaração preciamente preparada
     * @param str string que representa nome ou telefone
     */
    public void ajustarBuscar(PreparedStatement pstmt, String str) {
        try {
            pstmt.setString(1, "%"+str+"%");
            pstmt.setString(2, "%"+str+"%");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Busca por nome ou telefone no banco de dados
     * @param str nome ou telefone para busca
     * @return objetos constituidos
     */
    public List<Cliente> buscarPorNomeTelefone(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Cliente> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PESSOAS WHERE (NOME LIKE ? OR TELEFONE LIKE ?) AND CARGO = 'cliente'" )) {
            
            ajustarBuscar(pstmt, str);

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();
            
            // Extrai objeto(s) do(s) respectivo(s) registro(s) do banco de dados
            objetos = extrairObjetos(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve uma lista vazia (nenhum registro encontrado) 
        // ou a relação de objeto(s) recuperado(s)
        return objetos;
    }
    /**
     * Confere a existencia de um cliente dado um determinado telefone
     * @param dddStr DDD do telefone
     * @param telefoneStr numero do telefone
     * @return objeto constituido caso ache, e nulo caso não exista
     */
    public Cliente localizarPorTelefone(String dddStr , String telefoneStr) {
        // Declara referência para reter o objeto a ser recuperado
        Cliente objeto = null;

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para busca por chave primária
                        getDeclaracaoSelectPorTelefone())) {

            // Prepara a declaração com os dados do objeto passado
            ajustarTelefoneDeclaracao(pstmt,dddStr, telefoneStr);
            
            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();

            // Se há resultado retornado...
            if (resultSet.next()) {
                // ... extrai objeto do respectivo registro do banco de dados
                objeto = extrairObjeto(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve nulo (objeto não encontrado) ou o objeto recuperado
        return objeto;
    }
    public List<Cliente> localizarPorEndereco(Endereco end) {
        // Declara referência para reter o objeto a ser recuperado
        List <Cliente> objeto = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para busca por chave primária
                        "SELECT * FROM PESSOAS WHERE ENDERECOSID = ?")) {

            // Prepara a declaração com os dados do objeto passado
            pstmt.setLong(1, end.getId());
            
            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();

            // Se há resultado retornado...
       
                // ... extrai objeto do respectivo registro do banco de dados
                objeto = extrairObjetos(resultSet);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve nulo (objeto não encontrado) ou o objeto recuperado
        return objeto;
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Cliente extrairObjeto(ResultSet resultSet) {
       Cliente objeto = new Cliente();
           
        try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setNome(resultSet.getString("NOME"));
            objeto.setDDD(resultSet.getString("DDD"));
            objeto.setTelefone(resultSet.getString("TELEFONE"));        
            objeto.setCargo(resultSet.getString("CARGO"));
            Long endId = resultSet.getLong("ENDERECOSID");
            
            Endereco end = new EnderecoDao().localizarPorId(endId);
            objeto.setEndereco(end);
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
       return objeto;
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    private String getDeclaracaoSelectPorTelefone() {
        return "SELECT * FROM PESSOAS WHERE TELEFONE = ? AND DDD = ?";
    }
   /**
    * Ajusta a declaração de um determinado numero de telefone
    * @param pstmt declaração previamente preparada
    * @param strDdd DDD do telefone
    * @param strTel numero de telefone
    */
    private void ajustarTelefoneDeclaracao(PreparedStatement pstmt,String strDdd, String strTel) {
        try {
            pstmt.setString(1, strTel);
            pstmt.setString(2, strDdd);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Exclui o cliente do banco de dados
     * @param o cliente a ser excluido
     * @return true para exclusão bem sucedida e false caso contrario
     */
     public Boolean excluirCliente(Cliente o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();
        
        if(new EnderecoDao().verificarUso(o.getEndereco()) == false)
                new EnderecoDao().excluir(o.getEndereco());
        
        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "UPDATE PEDIDOS SET PESSOASID = NULL WHERE PESSOASID = ?;")) {

                // Prepara a declaração com os dados do objeto passado
                 pstmt.setLong(1, o.getId());
                 
                
                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            excluir(o);
            if(o.getEndereco() != null)
                if(new EnderecoDao().verificarUso(o.getEndereco()) == false)
                    new EnderecoDao().excluir(o.getEndereco());
        } else {
            return false;
        }

        return true;
    }

    
}
