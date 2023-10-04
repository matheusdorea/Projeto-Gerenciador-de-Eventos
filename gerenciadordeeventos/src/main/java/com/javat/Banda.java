package com.javat;

public class Banda {
    private String nome;
    private String generoMusical;
    private int integrantes;
    
    public Banda(String nome, String generoMusical, int integrantes) {
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.integrantes = integrantes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }

    public int getIntegrantes() {
        return this.integrantes;
    }

}
