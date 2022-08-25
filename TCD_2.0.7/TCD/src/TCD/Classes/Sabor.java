package TCD.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Declaração da classe de Sabor, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Sabor extends Entidade {
    /** nome do Sabor */
    private String nome;
    /** lista dos Ingrediente que Compõem o Sabor */
    private List<Ingrediente> ingredientes;
    /** variavel que armazena a Categoria que o Sabor pertence */
    private Categoria categoria;

    
    //<editor-fold defaultstate="collapsed" desc="Construtores">
    /**
     * Construtor padrão.<br>
     * 
     */
    public Sabor() {
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

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
     //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Equals/HashCode">
    /**
     * Verifica identidade entre objetos do tipo Sabor.<br> * @param obj Sabor a
     * ser comparada com o objeto atual
     * @return Estado de igualdade entre tarefas.
     */
    @Override
    public boolean equals(Object obj) {

        final Sabor other = (Sabor) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
    //</editor-fold> 
    
    /**
     * Gera representação textual do objeto atual.
     *
     * @return Texto representativo do objeto atual
     */
    @Override
    public String toString() {
        return nome;
    }

}
