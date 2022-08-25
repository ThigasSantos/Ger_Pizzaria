/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCD.Classes;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * Declaração da classe de Pizza, herdada de Produto
 * 
 * @author Marcos Dias
 * @author Rafael Paranhos
 * @author Thiago Evangelista
 * @author Wellington Junio
 */
public class Pizza extends Produto{
    /** tamanho da Pizza, armazenada em forma de numero 0 pequena ... */
    private Integer tamanho;
    /** tipo de borda da Pizza */
    private String borda;
    /** vetor que armazena os sabores da Pizza*/
    private Sabor[] sabores;
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Pizza(){
        
    }
    public Pizza(Sabor sabor, Integer tamanho){
        this.sabores = new Sabor[]{sabor};
        this.tamanho = tamanho;
        setPreco(sabor.getCategoria().getPreco()[this.tamanho]);
    }
    public Pizza(Sabor sabor1, Sabor sabor2, Integer tamanho){
        this.tamanho = tamanho;
        this.sabores = new Sabor[]{sabor1, sabor2};
        if(sabor1.getCategoria().getPreco()[this.tamanho] >= sabor2.getCategoria().getPreco()[this.tamanho])
            setPreco(sabor1.getCategoria().getPreco()[this.tamanho]);
        else 
            setPreco(sabor2.getCategoria().getPreco()[this.tamanho]);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public Sabor[] getSabores() {
        return sabores;
    }

    public void setSabores(Sabor[] sabores) {
        this.sabores = sabores;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Equals/HashCode">
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        final Pizza other = (Pizza) obj;
        if (!Objects.equals(this.tamanho, other.tamanho)) {
            return false;
        }
        if (!Objects.equals(this.borda, other.borda)) {
            return false;
        }/** Sabores iguais são considerados os mesmos ainda que estejam em ordem diferentes */
        if(sabores.length == other.sabores.length){
            if(sabores.length > 1){
                if (!(sabores[0].equals(other.sabores[1]) && sabores[1].equals(other.sabores[0]) || sabores[0].equals(other.sabores[0]) && sabores[1].equals(other.sabores[1]))) {
                    return false;
                }}
            else if(!Arrays.deepEquals(this.sabores, other.sabores))
                return false;
        }
        else
            return false;
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
        DecimalFormat df = new DecimalFormat("0.00");
        String tamanhoStr = ""; 
        if(null !=tamanho)
                 switch (tamanho) {
               case 0:
                   tamanhoStr = "pequena" ;
                   break;
               case 1:
                   tamanhoStr = "media" ;
                   break;
               case 2:
                   tamanhoStr = "grande" ;
                   break;
               case 3:
                   tamanhoStr = "gigante" ;
                   break;
               case 4:
                   tamanhoStr = "familia" ;
                   break;
               default:
                   break;
           }
        if(this.sabores.length == 2)
            return getQuantidade() + "x Pizza [" 
                + sabores[0].getNome() + ", " 
                + sabores[1].getNome() + "] " 
                + tamanhoStr + " " 
                + this.borda + " R$" + df.format(getPreco());
        else
            return getQuantidade() + "x Pizza " 
                + sabores[0].getNome() + " "
                + tamanhoStr + " " 
                + this.borda + " R$" +  df.format(getPreco()) ;
    }
        
}
