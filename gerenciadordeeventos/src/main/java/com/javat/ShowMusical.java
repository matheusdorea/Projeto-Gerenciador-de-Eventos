package com.javat;

public class ShowMusical extends Evento{
    private Banda banda;
    private Artista artistaSolo;
    private Ingresso bilheteria;
    private Evento eventoInfo;
    
    public ShowMusical(String nome, String data, String local, String responsavel, Banda banda, Artista artistaSolo,
            Ingresso bilheteria, Evento eventoInfo) {
        super(nome, data, local, responsavel);
        this.banda = banda;
        this.artistaSolo = artistaSolo;
        this.bilheteria = bilheteria;
        this.eventoInfo = eventoInfo;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public Artista getArtistaSolo() {
        return artistaSolo;
    }

    public void setArtistaSolo(Artista artistaSolo) {
        this.artistaSolo = artistaSolo;
    }

    public Ingresso getBilheteria() {
        return bilheteria;
    }

    public void setBilheteria(Ingresso bilheteria) {
        this.bilheteria = bilheteria;
    }

    public Evento getEventoInfo() {
        return eventoInfo;
    }

    public void setEventoInfo(Evento eventoInfo) {
        this.eventoInfo = eventoInfo;
    }

    
    
}
