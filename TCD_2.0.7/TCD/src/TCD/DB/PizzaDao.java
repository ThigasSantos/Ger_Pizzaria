/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Pedido;
import TCD.Classes.Pizza;
import TCD.Classes.Sabor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Pizza
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class PizzaDao extends AbstractDao<Pizza, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO PIZZAS (SABOR1ID, SABOR2ID, BORDA, TAMANHO) VALUES (?, ?, ?, ?)";
    }

    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
       return "SELECT * FROM PIZZAS WHERE ID = ?";
       //SELECT TOP 1
    }

    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
       return "SELECT * FROM PIZZAS";
    }

    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE PIZZAS SET SABOR1ID = ?, SABOR2ID = ?, BORDA = ?, TAMANHO = ? WHERE ID = ?";
    }

    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM PIZZAS WHERE ID = ?";
    }

    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o pizza para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Pizza o) {
        try {
            pstmt.setLong(1, o.getSabores()[0].getId());
            if(o.getSabores().length > 1)
                pstmt.setLong(2, o.getSabores()[1].getId());
            else
                pstmt.setLong(2, o.getSabores()[0].getId());
            pstmt.setString(3, o.getBorda());
            pstmt.setInt(4, o.getTamanho());
            if(o.getId() != null)
                pstmt.setLong(5, o.getId());
        } catch (SQLException ex) {
            Logger.getLogger(PizzaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Pizza extrairObjeto(ResultSet resultSet) {
       Pizza objeto = new Pizza();
       Sabor sabor = new Sabor();
       
        try {  
     
  
            if(resultSet.getLong("SABOR1ID") == resultSet.getLong("SABOR2ID")){
                sabor = new SaborDao().localizarPorId(resultSet.getLong("SABOR1ID"));
                objeto = new Pizza(sabor,resultSet.getInt("TAMANHO"));
                
            }
            else{
                Sabor sabor2 = new SaborDao().localizarPorId(resultSet.getLong("SABOR2ID"));
                sabor = new SaborDao().localizarPorId(resultSet.getLong("SABOR1ID"));
                objeto = new Pizza(sabor, resultSet.getInt("TAMANHO"));
            }
                
            
            objeto.setId(resultSet.getLong("ID"));
            objeto.setBorda(resultSet.getString("BORDA"));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PizzaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
    }
    
    /**
     * Busca para ver se a pizza já existe no banco de dados
     * @param objeto pizza para busca
     * @return id da pizza caso ja exista e null caso não
     */
    public Long buscarExistente(Pizza objeto) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
       Pizza other = new Pizza();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PIZZAS WHERE ((SABOR1ID = ? AND SABOR2ID = ?) OR (SABOR1ID = ? AND SABOR2ID = ?)) AND (BORDA = ? AND TAMANHO = ?) LIMIT 1" )) {
            
            pstmt.setLong(1, objeto.getSabores()[0].getId());
            pstmt.setLong(4, objeto.getSabores()[0].getId());
            if(objeto.getSabores().length > 1){
                pstmt.setLong(2, objeto.getSabores()[1].getId());
                pstmt.setLong(3, objeto.getSabores()[1].getId());
            }
            else{
                pstmt.setLong(2, objeto.getSabores()[0].getId());
                pstmt.setLong(3, objeto.getSabores()[0].getId());
            }
            pstmt.setString(5, objeto.getBorda());
            pstmt.setInt(6, objeto.getTamanho()+1);

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
     *  Seleciona todas as pizzas de um pedido
     * @param pedido para filtro
     * @return  objetos constituidos
     */
     public List<Pizza> SelectTodosdoPedido(Pedido pedido) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Pizza> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PIZZAS,PEDIDOS_PRODUTOS WHERE PEDIDOS_PRODUTOS.PEDIDOSID= ? AND PEDIDOS_PRODUTOS.PIZZASID = PIZZAS.ID" )) {
            
            pstmt.setLong(1, pedido.getId());

            // Executa o comando SQL
            ResultSet resultSet = pstmt.executeQuery();
            
            // Extrai objeto(s) do(s) respectivo(s) registro(s) do banco de dados
            int i = 0;
            while(resultSet.next()){              
                objetos.add(extrairObjeto(resultSet));
                objetos.get(i).setQuantidade(resultSet.getInt("QUANTIDADE"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolve uma lista vazia (nenhum registro encontrado) 
        // ou a relação de objeto(s) recuperado(s)
        return objetos;
    }
     
     /**
      *  Seleciona as pizzas pelo sabor
      * @param sabor para filtro
      * @return objetos constituidos
      */
     public List<Pizza> SelectPorSabor(Sabor sabor) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Pizza> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PIZZAS WHERE SABOR1ID = ? OR SABOR2ID = ?" )) {
            
            pstmt.setLong(1, sabor.getId());
            pstmt.setLong(2, sabor.getId());

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
     * Exclui a pizza do banco de dados
     * @param o pizza para ser excluida
     */ 
     public void excluirPizza(Pizza o){
        excluirPizza_Pedidos(o);
        excluir(o); 
     }
     
     /**
      * Exclui a pizza da relação pedidos_produtos
      * @param o pizza a ser excluida
      * @return true em caso de sucesso e false caso não
      */
      public Boolean excluirPizza_Pedidos(Pizza o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM PEDIDOS_PRODUTOS WHERE PIZZASID = ?")) {

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
     
     
    
}
