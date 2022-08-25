package TCD.Classes;
/**
 * Declaração da classe abstrata de Pessoa, herdada de entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public abstract class Pessoa extends Entidade{
    /** variavel do tipo Endereço que armazena o Endereço da Pessoa, seja Funcionario ou Cliente */
    private Endereco endereco;
    /** Nome da Pessoa */
    private String nome;
    /** DDD do numero da Pessoa */
    private String DDD;
    /** numero de telefone da Pessoa */
    private String telefone;
    /** cargo da Pessoa */
    private String cargo;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getDDD() {
        return DDD;
    }

    public void setDDD(String DDD) {
        this.DDD = DDD;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Pessoa(){
    }
    public Pessoa(String nome, String DDD, String telefone, String cargo, Endereco endereco){
        this.nome = nome;
        this.DDD = DDD;
        this.telefone = telefone;
        this.cargo = cargo;
        this.endereco = endereco;
    }
    //</editor-fold>
}
