/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.DB;

import TCD.Classes.Acompanhamento;
import TCD.Classes.Ingrediente;
import TCD.Classes.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Acompanhamento
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class AcompanhamentoDao extends AbstractDao<Acompanhamento, Long>{
     /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return sentença SQL para inserção
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO PRODUTOS (NOME, PRECO, CATEGORIA, DESCRICAO) VALUES (?, ?, ?, ?)";
    }
     /**
     * Recupera a sentença SQL específica para a busca da entidade no banco
     * de dados.
     *
     * @return sentença SQL para inserção
     */
    @Override
    public String getDeclaracaoSelectPorId() {
        return "SELECT * FROM PRODUTOS WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM PRODUTOS";  
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE PRODUTOS SET NOME = ?, PRECO = ?, CATEGORIA = ?, DESCRICAO = ? WHERE ID = ? ";
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
        return "DELETE FROM PRODUTOS WHERE ID = ?";
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o acompanhamento para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Acompanhamento o) {
        try {
            
            pstmt.setString(1, o.getNome());
            pstmt.setFloat(2, o.getPreco());
            pstmt.setString(3, o.getCategoria());
            pstmt.setString(4, o.getDescricao());
            if (o.getId() != null)
                pstmt.setLong(5 , o.getId());

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return objeto constituido.
     */
    @Override
    public Acompanhamento extrairObjeto(ResultSet resultSet) {
        Acompanhamento objeto = new Acompanhamento();
        try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setNome(resultSet.getString("NOME"));
            objeto.setPreco(resultSet.getFloat("PRECO"));
            objeto.setCategoria(resultSet.getString("CATEGORIA"));
            objeto.setDescricao(resultSet.getString("DESCRICAO"));
            objeto.setIngredientes(new IngredienteDao().buscarPorAcompanhamento(objeto));
            
        } catch (SQLException ex) {
            Logger.getLogger(AcompanhamentoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
      
    }
    
    
    // ---------Outras
    /**
     *  Busca o acompanhamento no banco de dados pelo nome dele
     * @param str nome buscado
     * @return objetos constituidos
     */
    public List<Acompanhamento> buscarPorNome(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Acompanhamento> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PRODUTOS WHERE NOME LIKE ?" )) {
            
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
     * Seleciona todos os acompanhamentos da categoria
     * @param str nome da categoria
     * @return objetos constituidos
     */
    public List<Acompanhamento> SelectTodosdaCategoria(String str) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Acompanhamento> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PRODUTOS WHERE CATEGORIA = ?" )) {
            
            pstmt.setString(1, str);

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
     * Seleciona todos os acompanhamentos de um pedido
     * @param pedido desejado
     * @return objetos constituidos
     */
    public List<Acompanhamento> SelectTodosdoPedido(Pedido pedido) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Acompanhamento> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PRODUTOS,PEDIDOS_PRODUTOS WHERE PEDIDOS_PRODUTOS.PEDIDOSID= ? AND PEDIDOS_PRODUTOS.PRODUTOSID = PRODUTOS.ID" )) {
            
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
     * Salca o ingrediente no produto selecionado
     * @param acompanhamento produto selecionado
     * @param ingrediente para ser adicionado
     */
    public void salvarIngredienteEmProduto(Acompanhamento acompanhamento,Ingrediente ingrediente){
            String declaracao;
                declaracao = "INSERT INTO INGREDIENTES_PRODUTOS (INGREDIENTESID, PRODUTOSID) VALUES (?, ?)";
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
                pstmt.setLong(2, acompanhamento.getId());

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
    * Exclui o acompanhamento do banco de dados
    * @param o acompanhamento para ser excluido
    * @return true para exclusão bem sucedida
    */     
    public Boolean excluirAcompanhamento(Acompanhamento o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        excluirAcompanhamento_Pedidos(o);
        excluirAcompanhamento_Ingredientes(o);
        excluir(o);
        return true;
    }
    /**
     * Exclui o ingrediente do acompanhamento
     * @param o acompanhamento selecionado
     * @param i ingrediente a ser excluido
     * @return true para exclusão bem sucedida
     */
    public Boolean excluirIngrediente(Acompanhamento o, Ingrediente i) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM INGREDIENTES_PRODUTOS WHERE PRODUTOSID = ? and INGREDIENTESID = ?")) {

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
    
    
    
    /**
     * Exclui o acompanhamento na relação acompanhamento pedido
     * @param o acompanhamento a ser excluido
     * @return true para exclusão bem sucedida
     */
    public Boolean excluirAcompanhamento_Pedidos(Acompanhamento o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM PEDIDOS_PRODUTOS WHERE PRODUTOSID = ?")) {

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
     * Exclui acompanhamento da relação acompanhamento_ingrediente
     * @param o acompanhamento a ser excluido
     * @return true para exclusão bem sucedida
     */
    public Boolean excluirAcompanhamento_Ingredientes(Acompanhamento o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();

        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM INGREDIENTES_PRODUTOS WHERE PRODUTOSID = ?")) {

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
