/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Endereco;
import TCD.Classes.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Funcionario
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class FuncionarioDao extends AbstractDao<Funcionario, Long>{

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO PESSOAS (NOME, DDD, TELEFONE, CPF, CARGO, ENDERECOSID) values (?, ?, ?, ?, ?, ?)";
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
        return "SELECT * FROM PESSOAS WHERE CARGO not in ('cliente')";
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
       return "UPDATE PESSOAS SET NOME = ?, DDD = ?, TELEFONE = ?, CPF = ?, CARGO = ?, ENDERECOSID = ? WHERE ID = ?";
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
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o funcionario para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Funcionario o) {
        
        try {
            pstmt.setString(1, o.getNome());
            pstmt.setInt(2, Integer.parseInt(o.getDDD()));
            pstmt.setString(3, o.getTelefone());
            pstmt.setString(4, o.getCpf());
            pstmt.setString(5, o.getCargo());
            if(o.getEndereco() != null)
                pstmt.setLong(6, o.getEndereco().getId());
            else
                pstmt.setNull(6, 0);
            if (o.getId() != null){
                pstmt.setLong(7 , o.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Ajusta a busca no banco de dados
     * @param pstmt declaração previamente preparada
     * @param str string de ajuste
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
    public List<Funcionario> buscarPorNomeTelefone(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Funcionario> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PESSOAS WHERE (NOME LIKE ? OR TELEFONE LIKE ?) AND CARGO <> 'cliente'" )) {
            
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
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Funcionario extrairObjeto(ResultSet resultSet) {
        Funcionario objeto = new Funcionario();
         try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setNome(resultSet.getString("NOME"));
            objeto.setDDD(resultSet.getString("DDD"));
            objeto.setTelefone(resultSet.getString("TELEFONE"));        
            objeto.setCpf(resultSet.getString("CPF"));
            objeto.setCargo(resultSet.getString("CARGO"));
            Long endId = resultSet.getLong("ENDERECOSID");
           
            
            Endereco end = new EnderecoDao().localizarPorId(endId);
            objeto.setEndereco(end);
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return objeto;
    }

    
}
