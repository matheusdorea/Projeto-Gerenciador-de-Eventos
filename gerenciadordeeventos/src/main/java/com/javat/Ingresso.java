package com.javat;

public class Ingresso {
    private String numeroDoIngresso;
    private String tipo;
    private String status;
    private String preco;

    public Ingresso(String numeroDoIngresso, String tipo, String status, String preco) {
        this.numeroDoIngresso = numeroDoIngresso;
        this.tipo = tipo;
        this.status = status;
        this.preco = preco;
    }

    public String getNumeroDoIngresso() {
        return numeroDoIngresso;
    }

    public void setNumeroDoIngresso(String numeroDoIngresso) {
        this.numeroDoIngresso = numeroDoIngresso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    
}
