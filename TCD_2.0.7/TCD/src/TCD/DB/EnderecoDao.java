/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Bairro;
import TCD.Classes.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Endereço
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class EnderecoDao extends AbstractDao<Endereco, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO ENDERECOS (RUA, NUMERO, COMPLEMENTO, BAIRROSID) VALUES (?, ?, ?, ?)"; 
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM ENDERECOS WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
       return "SELECT * FROM ENDERECOS";
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE ENDERECOS SET RUA = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRROSID = ? WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM ENDERECOS WHERE ID = ?";
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o endereço para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Endereco o) {
        try {
            pstmt.setString(1, o.getRua());
            pstmt.setString(2, o.getNumero());
            pstmt.setString(3, o.getComplemento());
            pstmt.setLong(4, o.getBairro().getId());
            if (o.getId() != null){
                    pstmt.setLong(5, o.getId());
                }
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
    public Endereco extrairObjeto(ResultSet resultSet) {
        Endereco objeto = new Endereco();
         try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setRua(resultSet.getString("RUA"));
            objeto.setNumero(resultSet.getString("NUMERO"));
            objeto.setComplemento(resultSet.getString("COMPLEMENTO"));              
            Long bairroId = resultSet.getLong("BAIRROSID");

                Bairro bairro = new BairroDao().localizarPorId(bairroId);
            objeto.setBairro(bairro);
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return objeto;
    }
    /**
     * Verifica se o endereço ja existe no banco de dados
     * @param o endereço para verificação
     * @return true se exister e false se não existir
     */
    public Boolean verificarUso(Endereco o) {
        if(new ClienteDao().localizarPorEndereco(o).size() != 0)
            return true;
        if(new PedidoDao().localizarPorEndereco(o).size() != 0)
            return true;
        return false;
    }
    /**
     * Exclui o endereço do banco de dados
     * @param o endereço para ser excluido
     * @return true se a exclusão for bem sucedida e false caso não
     */
    public Boolean excluirEndereco(Endereco o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "UPDATE PESSOAS SET ENDERECOSID = NULL WHERE ENDERECOSID = ?; UPDATE PEDIDOS SET ENDERECOSID = NULL WHERE ENDERECOSID = ?")) {

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
