package com.gerenciadordeeventos;

public class Evento {
    private String nome;
    
    public Evento(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
