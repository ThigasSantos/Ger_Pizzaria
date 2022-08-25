package TCD.Classes;

import java.util.Objects;
/**
 * Declaração da classe de Bairro, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Bairro extends Entidade {

        /** Nome do Bairro */
	private String nome;
        /** Taxa de entrega referente ao Bairro */
	private Float taxa;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getTaxa() {
        return taxa;
    }

    public void setTaxa(Float taxa) {
        this.taxa = taxa;
    }
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Equals/HashCode">
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        final Bairro other = (Bairro) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.taxa, other.taxa)) {
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
        return nome + " R$" + taxa;
    }
    
    

}
