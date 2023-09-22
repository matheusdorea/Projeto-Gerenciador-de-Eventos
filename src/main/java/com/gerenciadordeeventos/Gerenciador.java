package com.gerenciadordeeventos;

import java.util.List;
import java.util.ArrayList;

public class Gerenciador {
    private List<Evento> eventos = new ArrayList<Evento>();

    public Gerenciador() {

    }

    public void addEvento(String nome) {
        eventos.add(new Evento(nome));
    }

    public String showEventos() {
        String res = "";
        for (Evento evento : eventos) {
            res += evento.getNome();
            res += " // ";
        }

        return res;
    }

    public List<Evento> getEventos() {
        return eventos;
    }
}
