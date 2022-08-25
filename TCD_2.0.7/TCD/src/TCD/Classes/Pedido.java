package TCD.Classes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
/**
 * Declaração da classe de Pedido, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Pedido extends Entidade {
    /** preco total do Pedido */
    private Float precoTotal;
    /** alguma observação feita sobre o Pedido*/
    private String observacao;
    /** o horario em que o Pedido foi feito*/
    private String horario;
    /** varivel do tipo Cliente que salva o cliente que fez o pedido*/
    private Cliente cliente;
    /** variavel que armazena o Endereço em que o pedido vai ser entregue*/
    private Endereco endereco;
    /** lista que armazena as pizzas do Pedido */
    private List<Pizza> pizzas;
    /** lista que armazena os acompanhamentos do Pedido */
    private List<Acompanhamento> acompanhamentos;
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Pedido(){
        this.pizzas = new ArrayList<>();
        this.acompanhamentos = new ArrayList<>();
        this.precoTotal = 0F;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Acompanhamento> getAcompanhamento() {
        return acompanhamentos;
    }

    public void setAcompanhamento(List<Acompanhamento> acompanhamento) {
        this.acompanhamentos = acompanhamento;
    }
    //</editor-fold>
    
    /**
     * Adiciona um novo Acompanhamento ao Pedido
     * @param novo acompanhamento selecionado
     */
    public void add(Acompanhamento novo){
        
        if(!this.acompanhamentos.contains(novo))
            this.acompanhamentos.add(novo);     
    }
    
    /**
     * Adiciona uma nova Pizza ao Pedido
     * @param novo pizza selecionada
     */
    public void add(Pizza novo){
        
        if(!this.pizzas.contains(novo))
            this.pizzas.add(novo);     
        
        else
            this.pizzas.forEach(pizza ->{
            if(pizza.equals(novo))
                pizza.setQuantidade(pizza.getQuantidade()+ novo.getQuantidade());
            });             
    }
    
    /**
     * Calcula e atualiza o preço total do Pedido
     */
    public void calcularPrecoTotal(){
        this.precoTotal = 0F;
        this.acompanhamentos.forEach(acomp ->{
            this.precoTotal = this.precoTotal + ( acomp.getQuantidade() * acomp.getPreco() );
        });
        this.pizzas.forEach(pizza->{
            this.precoTotal = this.precoTotal + ( pizza.getQuantidade() * pizza.getPreco() );
        });
        
        if(this.endereco != null)
            this.precoTotal = this.precoTotal + this.endereco.getBairro().getTaxa();
    }
    
    
    /**
     * Gera representação textual do objeto atual.
     * 
     * @return Texto representativo do objeto atual
     */

    @Override
    public String toString() {
        if(cliente == null)
            return  "Cliente " + horario;
        else
            return cliente.toString() + " - " + horario;
    }
    
    
    
}
