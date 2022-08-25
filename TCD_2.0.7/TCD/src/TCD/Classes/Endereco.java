package TCD.Classes;

import java.util.Objects;
/**
 * Declaração da classe de Endereço, herdada de Entidade, salva o endereço da Pessoa
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Endereco extends Entidade{

        /** Rua em que a Pessoa mora */
	private String rua;
        /** Numero da moradia */
	private String numero;
        /** Complemento do Endereço*/
	private String complemento;
        /** Variavel do tipo Bairro responsavel por pegar nome do Bairro e taxa determinada ao Bairro */
        private Bairro bairro;

    //<editor-fold defaultstate="collapsed" desc="Equals/HashCode">
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        return true;
    }
    //</editor-fold> 
    
    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Endereco(){
            
    }
    public Endereco (String rua, String numero,String complemento, Bairro bairro){
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;                               
    }
    //</editor-fold>
      
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
     public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    //</editor-fold>
    

}
