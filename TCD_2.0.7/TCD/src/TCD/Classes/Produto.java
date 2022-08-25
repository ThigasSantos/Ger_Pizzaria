package TCD.Classes;
/**
 * Declaração da classe abstrata de Produto, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public abstract class Produto extends Entidade {
   /** preco do Produto */
    private Float preco;
    /** quantidade selecionada do Produto */
    private Integer quantidade;
    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Produto() {
        this.quantidade = 0;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
    //</editor-fold>
}
