package TCD.Classes;
/**
 * Declaração da classe de Funcionario, herdada de Pessoa
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Funcionario extends Pessoa {

    /** Variavel que armazena o cpf do Funcionario */
    private String cpf;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Funcionario(){
        
    }
    public Funcionario(String nome, String DDD, String telefone, String cargo, String cpf, Endereco endereco ){
        super(nome, DDD, telefone, cargo, endereco);
        this.cpf = cpf;
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    //</editor-fold>       
    
    /**
     * Gera representação textual do objeto atual.
     * 
     * @return Texto representativo do objeto atual
     */
    @Override
    public String toString() {
        return getNome() + " ("+getDDD()+") " + getTelefone() + " [" + getCargo()+ "]";
    }

    
}
