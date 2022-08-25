package TCD.Classes;
/**
 * Declaração da classe de Ingrediente, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Ingrediente extends Entidade{
    /** nome do Ingrediente */
    private String nome;
    /** descrição do Ingrediente */
    private String descricao;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    //</editor-fold>
    
    /**
     * Gera representação textual do objeto atual.
     * 
     * @return Texto representativo do objeto atual
     */
    @Override
    public String toString() {
        if(0 == descricao.length())
            return nome;
        else
        return nome + " [" + descricao + "]";
    }
    
    
        
}
