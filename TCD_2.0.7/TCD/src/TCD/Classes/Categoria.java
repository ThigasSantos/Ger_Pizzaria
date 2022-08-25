/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.Classes;

/**
 * Declaração da classe de Categoria, herdada de Entidade
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Categoria extends Entidade{
    
    /** Nome da Categoria */
    private String nome;
    /** Vetor de Preços ligado a categoria da Pizza*/
    private Float preco[];

    //<editor-fold defaultstate="collapsed" desc="Construtores">
    public Categoria(){
        preco = new Float[5];
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float[] getPreco() {
        return preco;
    }

    public void setPreco(Float[] preco) {
        this.preco = preco;
    }
    //</editor-fold>
    
}
