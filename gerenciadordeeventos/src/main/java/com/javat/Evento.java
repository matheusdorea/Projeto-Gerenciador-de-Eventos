package com.javat;

import java.util.List;
import java.util.ArrayList;

public class Evento {
    private String nome;
    private String data;
    private String local;
    private String responsavel;

    private List<Artista> artistas = new ArrayList<Artista>();

    private Ingresso bilheteria;

    private String eventoInfo;
    
    public Evento(String nome, String data, String local, String responsavel) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.responsavel = responsavel;
    }

    public void addArtista(Artista artista) {
        artistas.add(artista);
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

    public Ingresso getBilheteria() {
        return bilheteria;
    }

    public void setBilheteria(Ingresso bilheteria) {
        this.bilheteria = bilheteria;
    }

    public String getEventoInfo() {
        return eventoInfo;
    }

    public void setEventoInfo(String eventoInfo) {
        this.eventoInfo = eventoInfo;
    }
    
}
