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
    private List<Evento> eventos = new ArrayList<Evento>();

    public void addEvento(Evento evento) {
        eventos.add(evento);
    }

    public void removeEvento(String nome) {
        for (Evento evento : eventos) {
            if (evento.getNome().equals(nome)) {
                eventos.remove(evento);
            }
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
                    String artista = eve_.get("Artista").toString();

                    Evento evento = new Evento(nome, data, local, responsavel);
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
