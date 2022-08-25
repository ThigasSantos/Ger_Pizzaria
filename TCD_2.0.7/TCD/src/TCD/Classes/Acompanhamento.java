package TCD.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Declaração da classe de Acompanhamento, herdada de Produto
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Acompanhamento extends Produto {

    /** Nome do Acompanhamento */
    private String nome;
    /** Descrição do Acompanhmento */
    private String descricao;
    /** Categoria que o Acompanhamento pertence */
    private String categoria;
    /** Lista de Ingrediente*/
    private List <Ingrediente> ingredientes;

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Acompanhamento(){
    ingredientes = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    //</editor-fold> 

    //<editor-fold defaultstate="collapsed" desc="Equals/HashCode">
    @Override
    public boolean equals(Object obj) {
       
        final Acompanhamento other = (Acompanhamento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.categoria);
        return hash;
    }
    //</editor-fold> 
    
    /**
     * Gera representação textual do objeto atual.
     * 
     * @return Texto representativo do objeto atual
     */
    @Override
    public String toString() {
        return this.getQuantidade() + "x " + this.nome 
                + " [" + this.descricao 
                + "] R$ " + this.getPreco();
    }
}
