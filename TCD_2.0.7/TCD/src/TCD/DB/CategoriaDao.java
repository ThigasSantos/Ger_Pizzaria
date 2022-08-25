/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Categoria
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class CategoriaDao extends AbstractDao<Categoria, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO CATEGORIA (NOME,PRECO0 ,PRECO1 ,PRECO2 ,PRECO3 , PRECO4 ) VALUES (?,?,?,?,?,?)"; //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM CATEGORIA WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
    return "SELECT * FROM CATEGORIA";
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE CATEGORIA SET NOME = ?,PRECO0 = ? ,PRECO1 = ?,PRECO2 = ?,PRECO3= ?, PRECO4 = ? WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM CATEGORIA WHERE ID = ?";
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o categoria para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Categoria o) {
        try {
            pstmt.setString(1, o.getNome());
            for (int i = 0 ; i < 5; i++)
                pstmt.setFloat(i+2, o.getPreco()[i]);
            if(o.getId() != null)
                pstmt.setLong(7, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Categoria extrairObjeto(ResultSet resultSet) {
        Categoria objeto = new Categoria();
        try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setNome(resultSet.getString("NOME"));
            for(int i = 0; i < 5 ; i++)
                objeto.getPreco()[i] = (resultSet.getFloat("PRECO"+Integer.toString(i)));
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       return objeto; 
    }
    /**
     * Busca uma categoria por nome no banco de dados
     * @param str nome da categoria
     * @return objetos constituidos
     */
    public List<Categoria> buscarPorNome(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Categoria> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM CATEGORIA WHERE NOME LIKE ?" )) {
            
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
     * Exclui os sabores de uma categoria no banco de dados
     * @param o categoria para ter os sabores excluidos
     */
    public void excluirSabores(Categoria o){
        try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM SABORES WHERE CATEGORIAID = ?")) {

                // Prepara a declaração com os dados do objeto passado
                 pstmt.setLong(1, o.getId());
                 
                
                // Executa o comando SQL
                pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            }
            
    }
    /**
     * Eclui a categoria do banco de dados
     * @param o categoria a ser exlcuida
     */
    public void excluirCategoria(Categoria o){
        
        excluirSabores(o);
        excluir(o);
        
    }
    
    
}
