/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Acompanhamento;
import TCD.Classes.Ingrediente;
import TCD.Classes.Sabor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Ingrediente
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class IngredienteDao  extends AbstractDao<Ingrediente, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO INGREDIENTES (NOME, DESCRICAO) VALUES (?, ?)";
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM INGREDIENTES WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM INGREDIENTES"; 
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE INGREDIENTES SET NOME = ?, DESCRICAO = ?  WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM INGREDIENTES WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o ingrediente para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Ingrediente o) {
        
        try {
            pstmt.setString(1, o.getNome());
            pstmt.setString(2, o.getDescricao());
            if(o.getId() != null)
                pstmt.setLong(3, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Ingrediente extrairObjeto(ResultSet resultSet) {
        Ingrediente ingrediente = new Ingrediente();
        try {           
            ingrediente.setId(resultSet.getLong("ID"));
            ingrediente.setNome(resultSet.getString("NOME"));
            ingrediente.setDescricao(resultSet.getString("DESCRICAO"));
        } catch (SQLException ex) {
            Logger.getLogger(IngredienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingrediente;
    }
    
    // ---------Outras
    /**
     * Busca o ingrediente por nome no banco de dados
     * @param str nome a ser buscado
     * @return objetos constituidos
     */
    public List<Ingrediente> buscarPorNome(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Ingrediente> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM INGREDIENTES WHERE NOME LIKE ?" )) {
            
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
     * Busca os ingredientes presentes em um acompanhamento
     * @param acomp acompanhamento para busca
     * @return obejtos constituidos
     */
    public List<Ingrediente> buscarPorAcompanhamento(Acompanhamento acomp) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Ingrediente> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM INGREDIENTES,INGREDIENTES_PRODUTOS WHERE INGREDIENTES_PRODUTOS.PRODUTOSID = ? AND INGREDIENTES_PRODUTOS.INGREDIENTESID = INGREDIENTES.ID" )) {
            
            pstmt.setLong(1, acomp.getId());

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
     * Busca os ingredientes presentes em um sabor
     * @param sabor para busca
     * @return objetos constituidos
     */
    public List<Ingrediente> buscarPorSabor(Sabor sabor) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Ingrediente> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM INGREDIENTES,INGREDIENTES_SABORES WHERE INGREDIENTES_SABORES.SABORESID = ? AND INGREDIENTES_SABORES.INGREDIENTESID = INGREDIENTES.ID" )) {
            
            pstmt.setLong(1, sabor.getId());

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
     * Exclui o ingrediente da relação ingrediente_acompanhamento
     * @param o ingrediente a ser excluido
     * @return true em caso de sucesso e false caso não
     */
    public Boolean excluirIngredientes_Acompanhamento(Ingrediente o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM Ingredientes_Produtos WHERE INGREDIENTESID = ?")) {

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
        return true;
    }
    /**
     * Exclui o ingrediente da relação ingrediente_sabor
     * @param o ingrediente a ser excluido
     * @return true em caso de sucesso e false caso não
     */
    public Boolean excluirIngredientes_Sabores(Ingrediente o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM Ingredientes_Sabores WHERE INGREDIENTESID = ?")) {

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
        return true;
    }
    /**
     * Exclui o ingrediente do banco de dados
     * @param o ingrediente a ser excluido
     * @return true para sucesso e false caso não
     */
    public Boolean excluirIngredientes(Ingrediente o){
        excluirIngredientes_Acompanhamento(o);
        excluirIngredientes_Sabores(o);
        excluir(o);
        
        return true;
    }
    
}
