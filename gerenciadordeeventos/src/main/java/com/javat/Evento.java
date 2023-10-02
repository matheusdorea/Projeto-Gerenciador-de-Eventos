package com.javat;

public class Evento {
    private String nome;
    private String data;
    private String local;
    private String responsavel;
    
    public Evento(String nome, String data, String local, String responsavel) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.responsavel = responsavel;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
    
}
