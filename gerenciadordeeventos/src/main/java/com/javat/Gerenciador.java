package com.javat;

import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Gerenciador {
    JSONArray array;

    //lista de eventos do gerenciador
    private List<Evento> eventos = new ArrayList<Evento>();

    //lista de artistas cadastrados no gerenciador
    private List<Artista> artistas = new ArrayList<Artista>();

    //método que cadastra artistas
    public void addArtista(Artista artista) {
        artistas.add(artista);
    }

    //função que verifica se o artista está cadastrado
    public boolean artistaExiste(String nome) {
        boolean res = false;
        for (Artista artista : artistas) {
            if (artista.getNome().equals(nome)) {
                res = true;
            }
        }

        return res;
    }

    //método que pega um artista pelo nome
    public Artista getArtista(String nome) {
        Artista res = null;
        if (artistaExiste(nome)) {
            for (Artista artista : artistas) {
                if (artista.getNome().equals(nome)) {
                    res = artista;
                }
            }
            return res;
        }
        return null;
    }

    //função que pega o evento pelo nome
    public Evento getEvento(String nome) {
        Evento res = null;
        if (eventoExiste(nome)) {
                for (Evento evento : eventos) {
                    if (evento.getNome().equals(nome)) {
                        res = evento;
                    }
                }

            return res;
        } else {
            System.out.println("Evento não encontrado");
            return res;
        }
    }

    //função que verifica se evento existe
    public boolean eventoExiste(String nome) {
        boolean res = false;
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nome)) {
                res = true;
            }
        }

        return res;
    }

    //método que adiciona evento à lista
    public void addEvento(Evento evento) {
        eventos.add(evento);
    }

    //método que remove eventos
    public void removeEvento(String nome) {
        if (eventoExiste(nome)) {
            for (Evento evento : eventos) {
                if (evento.getNome().equals(nome)) {
                    eventos.remove(evento);
                }
            }
        } else {
            System.out.println("Evento não encontrado");
        }
    }

    public void loadArchive() throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        File file = new File("eventos.json");
        if (!file.exists()) {
            array = new JSONArray();
        } else {
            array = (JSONArray) parser
                .parse(new InputStreamReader(new FileInputStream("eventos.json"), "UTF-8"));

                for (Object o : array) {
                    JSONObject eve_ = (JSONObject) o;
                    // Salva nas variaveis os dados retirados do arquivo
                    String nome = eve_.get("Nome").toString();
                    String data = eve_.get("Data").toString();
                    String local = eve_.get("Local").toString();
                    String responsavel = eve_.get("Responsavel").toString();
                    List<String> artistasLoc = new ArrayList<String>();
                    for (String artista : (List<String>) eve_.get("Artistas")) {
                        artistasLoc.add(artista);
                    }

                    Evento evento = new Evento(nome, data, local, responsavel);
                    for (String string : artistasLoc) {
                        evento.addArtista(string);
                    }
                    addEvento(evento);
                }
        }
    }

    //to string

    // public String showEventos() {
    //     String res = "";
    //     int contador = 1;
    //     for (Evento evento : eventos) {
    //         res += "Evento " + contador + ": Nome: " 
    //         + evento.getNome() + ", Data: " + evento.getData() + ", Local: " 
    //         + evento.getLocal() + ", Responsável: " + evento.getResponsavel() + " | ";
    //         contador++;
    //     }

    //     return res;
    // }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void createJsonFile() throws FileNotFoundException, IOException, ParseException {
		FileWriter writeFile = null;
        array.clear();
        for (Evento evento : eventos) {
                JSONObject jsonObject = new JSONObject();

                //Armazena dados em um Objeto JSON
                jsonObject.put("Nome", evento.getNome());
                jsonObject.put("Data", evento.getData());
                jsonObject.put("Local", evento.getLocal());
                jsonObject.put("Responsavel", evento.getResponsavel());
                jsonObject.put("Artistas", evento.getArtistas());

                // List<String> artitasNomes = new ArrayList<String>();
                // for (Artista artista: evento.getArtistas()) {
                //     artitasNomes.add(null)
                // }

                array.add(jsonObject);
            }
            try{
                writeFile = new FileWriter("eventos.json");
                writeFile.write(array.toJSONString());
                writeFile.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
    }
}
