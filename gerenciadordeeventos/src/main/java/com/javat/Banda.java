package com.javat;

import java.awt.List;

public class Banda {
    private String nome;
    private String generoMusical;
    private String integrantes;
    
    public Banda(String nome, String generoMusical, String integrantes) {
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

}
