/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Ingrediente;
import TCD.Classes.Sabor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Sabor
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class SaborDao extends AbstractDao<Sabor, Long>{

    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO SABORES (NOME, CATEGORIAID) VALUES (?, ?)";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM SABORES WHERE ID = ?";
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM SABORES";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE SABORES SET NOME = ?, CATEGORIAID = ? WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM SABORES WHERE ID = ?"; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o sabor para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Sabor o) {
        try {
            pstmt.setString(1, o.getNome());
            pstmt.setLong(2, o.getCategoria().getId());
            
            if(o.getId() != null)
                pstmt.setLong(3, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SaborDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Sabor extrairObjeto(ResultSet resultSet) {
        Sabor sabor = new Sabor();          
        try {
            sabor.setId(resultSet.getLong("ID"));
            sabor.setNome(resultSet.getString("NOME"));
            sabor.setIngredientes(new IngredienteDao().buscarPorSabor(sabor));
            sabor.setCategoria(new CategoriaDao().localizarPorId(resultSet.getLong("CATEGORIAID")));
        } catch (SQLException ex) {
            Logger.getLogger(SaborDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sabor;
    }
    
    /**
     * Busca o sabor pelo nome
     * @param str nome do sabor para busca
     * @return objetos constituidos
     */
    public List<Sabor> buscarPorNome(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Sabor> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM SABORES WHERE NOME LIKE ?" )) {
            
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
     * Salva os ingredientes no sabor
     * @param sabor 
     * @param ingrediente a ser incluido
     */
    public void salvarIngredienteEmSabor(Sabor sabor,Ingrediente ingrediente){
            String declaracao;
                declaracao = "INSERT INTO INGREDIENTES_SABORES (INGREDIENTESID, SABORESID) VALUES (?, ?)";
            //Verificar Categoria Produto
            

            // try-with-resources libera recurso ao final do bloco (PreparedStatement)
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para inserção de registros
                            declaracao,
                            // Especifica que a(s) chave(s) primária(s) devem ser
                            // retornadas como resposta
                            Statement.RETURN_GENERATED_KEYS)) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, ingrediente.getId());   
                pstmt.setLong(2, sabor.getId());

                // Executa o comando SQL
                pstmt.executeUpdate();

                // Recupera os resultados da execução: chaves primárias dos registros criados
                ResultSet rs = pstmt.getGeneratedKeys();

                // Se há alguma chave, move para o primeiro registro...

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    /**
     * Exclui o sabor do banco de dados
     * @param o sabor a ser excluido
     */
    public void excluirSabor(Sabor o){
        excluirSabores_Ingredientes(o);
        excluirSabores_Pizzas(o);
        excluir(o);
    }
    
    /**
     * Exclui as pizza sde um determinado sabor
     * @param o sabor das pizzas para exclusão
     */
    public void excluirSabores_Pizzas(Sabor o) {
        new PizzaDao().SelectPorSabor(o).forEach(pizza->{
            new PizzaDao().excluirPizza(pizza);
        });
    }
    
    /**
     * Exclui os ingredientes de um sabor
     * @param o sabor que contem os ingredientes
     * @return true em caso de sucesso e false caso não
     */
    public Boolean excluirSabores_Ingredientes(Sabor o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM INGREDIENTES_SABORES WHERE SABORESID = ?")) {

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
     * Exclui o ingrediente de um sabor
     * @param o sabor
     * @param i ingrediente para ser retirado do sabor
     * @return true em caso de sucesso e false em caso de falha
     */
    public Boolean excluirIngrediente(Sabor o, Ingrediente i) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM INGREDIENTES_SABORES WHERE SABORESID = ? and INGREDIENTESID = ?")) {

                // Prepara a declaração com os dados do objeto passado
                pstmt.setLong(1, o.getId());
                pstmt.setLong(2, i.getId());
                
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
    
    
    
}
