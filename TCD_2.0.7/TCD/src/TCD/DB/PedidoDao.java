package TCD.DB;

import TCD.Classes.Endereco;
import TCD.Classes.Pedido;
import TCD.Classes.Pizza;
import TCD.Classes.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Declaração da classe DAO de Pedido
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class PedidoDao extends AbstractDao<Pedido, Long>{
    /**
     * Recupera a sentença SQL específica para a inserção da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para inserção.
     */
    @Override
    public String getDeclaracaoInsert() {
        return "INSERT INTO PEDIDOS (OBSERVACAO, PRECOTOTAL, HORARIO, PESSOASID, ENDERECOSID) VALUES (?, ?, current_timestamp(), ?, ?)";
    }
    /**
     * Recupera a sentença SQL específica para a busca da entidade no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidade.
     */
    @Override
    public String getDeclaracaoSelectPorId() {
       return "SELECT * FROM PEDIDOS WHERE ID = ?";
    }
    /**
     * Recupera a sentença SQL específica para a busca das entidades no banco de
     * dados.
     *
     * @return Sentença SQl para busca por entidades.
     */
    @Override
    public String getDeclaracaoSelectTodos() {
        return "SELECT * FROM PEDIDOS";
    }
    /**
     * Recupera a sentença SQL específica para a atualização da entidade no
     * banco de dados.
     *
     * @return Sentença SQl para atualização.
     */
    @Override
    public String getDeclaracaoUpdate() {
        return "UPDATE PEDIDOS SET OBSERVACAO = ?, PRECOTOTAL = ?, PESSOASID = ?, ENDERECOSID = ? WHERE ID = ?"; 
    }
    /**
     * Recupera a sentença SQL específica para a exclusão da entidade no banco
     * de dados.
     *
     * @return Sentença SQl para exclusão.
     */
    @Override
    public String getDeclaracaoDelete() {
       return " DELETE FROM PEDIDOS WHERE ID = ?;";
    }
    /**
     * Insere os valores do objeto na senteça SQL específica para inserção ou
     * atualização de registros no banco de dados.
     *
     * @param pstmt Declaração previamente preparada.
     * @param o pedido para inserção e atualização no banco de dados.
     */
    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Pedido o) {
        try {
            pstmt.setString(1, o.getObservacao());
            pstmt.setFloat(2, o.getPrecoTotal());
            pstmt.setLong(3, o.getCliente().getId());
            pstmt.setLong(4, o.getEndereco().getId());
            if (o.getId() != null){
                pstmt.setLong(5 , o.getId());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Cria objeto a partir do registro fornecido pelo banco de dados.
     *
     * @param resultSet Resultado proveniente do banco de dados relacional.
     * @return Objeto constituído.
     */
    @Override
    public Pedido extrairObjeto(ResultSet resultSet) {
        Pedido objeto = new Pedido();
        try {
            objeto.setId(resultSet.getLong("ID"));
            objeto.setObservacao(resultSet.getString("OBSERVACAO"));
            objeto.setEndereco((new EnderecoDao().localizarPorId(resultSet.getLong("ENDERECOSID"))));
            objeto.setCliente((new ClienteDao().localizarPorId(resultSet.getLong("PESSOASID"))));
            objeto.setHorario(resultSet.getString("HORARIO"));
            objeto.setPrecoTotal(resultSet.getFloat("PRECOTOTAL"));
            
            objeto.setAcompanhamento(new AcompanhamentoDao().SelectTodosdoPedido(objeto));
            objeto.setPizzas(new PizzaDao().SelectTodosdoPedido(objeto));
            
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objeto;
        
    }
    /**
     * Salva um acompanhemento em pedido na relação do banco de dados
     * @param pedido pedido para salvar
     * @param produto acompanhamento a ser salvo
     */
    public void salvarProdutoEmPedido(Pedido pedido,Produto produto){
            String declaracao;
                declaracao = "INSERT INTO PEDIDOS_PRODUTOS (PEDIDOSID, PRODUTOSID, QUANTIDADE) VALUES (?, ?, ?)";
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
                pstmt.setLong(1, pedido.getId());   
                pstmt.setLong(2, produto.getId());
                pstmt.setFloat(3, produto.getQuantidade());

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
     * Salva uma pizza em pedido na relação do banco de dados
     * @param pedido para salvar
     * @param produto pizza a ser salva
     */
    public void salvarProdutoEmPedido(Pedido pedido,Pizza produto){
            String declaracao;
                declaracao = "INSERT INTO PEDIDOS_PRODUTOS (PEDIDOSID, PIZZASID, QUANTIDADE) VALUES (?, ?, ?)";
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
                pstmt.setLong(1, pedido.getId());   
                pstmt.setLong(2, produto.getId());
                pstmt.setFloat(3, produto.getQuantidade());

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
     * Localiza o pedido com base no endereço
     * @param end endereço para filtro
     * @return objeto constituido
     */
    public List<Pedido> localizarPorEndereco(Endereco end) {
        // Declara referência para reter o objeto a ser recuperado
        List <Pedido> objeto = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para busca por chave primária
                        "SELECT * FROM PEDIDOS WHERE ENDERECOSID = ?")) {

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
     * Busca um pedido em determinado intervalo de datas
     * @param inicio data de inicio da busca
     * @param termino data final da busca
     * @return objetos constituidos
     */
    public List<Pedido> buscarEntreDatas(String inicio,String termino) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Pedido> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PEDIDOS WHERE HORARIO > (?) AND HORARIO < (?)" )) {
            
            pstmt.setString(1, inicio);
            pstmt.setString(2, termino);

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
     * Busca os pedidos de um cliente
     * @param clienteID chave de indetificação do cliente
     * @return objetos constituidos
     */
    public List<Pedido> buscarPorCliente(Long clienteID) {
        
        // Declara referência para reter o(s) objeto(s) a ser(em) recuperado(s)
        List<Pedido> objetos = new ArrayList<>();

        // Tenta preparar uma sentença SQL para a conexão já estabelecida
        try (PreparedStatement pstmt
                = ConexaoBd.getConexao().prepareStatement(
                        // Sentença SQL para recuperação de todos os registros
                        "SELECT * FROM PEDIDOS WHERE PESSOASID = ?" )) {
            
            pstmt.setLong(1, clienteID);
            

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
     * Exclui o pedido no banco de dados
     * @param o pedido para ser excluido
     * @return true em caso de sucesso e false caso não
     */
    public Boolean excluirPedido(Pedido o) {
        // Recupera a identidade (chave primária) do objeto a ser excluído
        Long id = o.getId();
        
        // Se há uma identidade válida...
        if (id != null && id != 0) {
            // ... tenta preparar uma sentença SQL para a conexão já estabelecida
            try (PreparedStatement pstmt
                    = ConexaoBd.getConexao().prepareStatement(
                            // Sentença SQL para exclusão de registros
                            "DELETE FROM PEDIDOS_PRODUTOS WHERE PEDIDOSID = ?;")) {

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
