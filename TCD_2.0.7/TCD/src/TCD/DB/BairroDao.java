/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Acompanhamento;
import TCD.Classes.Bairro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Bairro
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class BairroDao extends AbstractDao <Bairro, Long> {

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO BAIRROS (NOME, TAXA) VALUES (?, ?)";
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM BAIRROS WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        
        return "SELECT * FROM BAIRROS"; //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
       return "UPDATE BAIRROS SET NOME = ?, TAXA = ? WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
       return "DELETE FROM BAIRROS WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
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
            pstmt.setLong(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(BairroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o Bairro para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Bairro o) {
        try {
            pstmt.setString(1, o.getNome());
            pstmt.setFloat(2, o.getTaxa());
            if(o.getId() != null)
                pstmt.setLong(3, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Bairro extrairObjeto(ResultSet resultSet) {
        Bairro objeto = new Bairro();
         try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setNome(resultSet.getString("NOME"));
            objeto.setTaxa(resultSet.getFloat("TAXA"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return objeto;
    }
    /**
     * Busca os bairros no banco de dados pelo nome
     * @param str nome  do bairro
     * @return objetos constituidos
     */
    public List<Bairro> buscarPorNome(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Bairro> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM BAIRROS WHERE NOME LIKE ? " )) {
            
            pstmt.setString(1, "%"+str+"%");

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
     * Verifica se já existe o bairro cadastrado no banco de dados
     * @param objeto bairro
     * @return id caso exista e null caso não exista
     */
    public Long buscarExistente(Bairro objeto) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        Bairro other = new Bairro();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM BAIRROS WHERE NOME = ? LIMIT 1" )) {
            
            
            pstmt.setString(1, objeto.getNome());

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();
            
            // Extrai objeto(s) do(s) respectivo(s) registro(s) do banco de dados
            if (resultSet.next()) {
                other = extrairObjeto(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve uma lista vazia (nenhum registro encontrado) 
        // ou a relação de objeto(s) recuperado(s)
        if (objeto != null)
            return other.getId();
        else 
            return null;
    }
    /**
     * Exclui o bairro do banco de dados
     * @param o bairro a ser excluido
     * @return true se for bem sucedida e false caso não
     */
    public Boolean excluirBairro(Bairro o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "UPDATE ENDERECOS SET BAIRROSID = NULL WHERE BAIRROSID = ?")) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, o.getId());
                
                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            

        } else {
            return false;
        }
        excluir(o);
        return true;
    }
    
    
}
