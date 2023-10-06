package com.javat;

public class Patrocinador {
    private double patrocinio;
    private String nome;

    public Patrocinador(String nome, double patrocinio) {
        this.nome = nome;
        this.patrocinio = patrocinio;
    }

    public double getPatrocinio() {
        return patrocinio;
    }

    public void setPatrocinio(double patrocinio) {
        this.patrocinio = patrocinio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
