/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.Classes;

/**
 * Declaração da classe de Cliente, herdada de Pessoa
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Cliente extends Pessoa{
    
    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Cliente(){
        
    }
    public Cliente(String nome, String DDD, String telefone, Endereco endereco) {
        super(nome, DDD, telefone, "cliente", endereco);
       
    }
    //</editor-fold>
    
    /**
     * Gera representação textual do objeto atual.
     * 
     * @return Texto representativo do objeto atual
     */
    @Override
    public String toString() {
        return getNome() + " (" + getDDD() + ") " + getTelefone();
    }
    
   
    
 

}
