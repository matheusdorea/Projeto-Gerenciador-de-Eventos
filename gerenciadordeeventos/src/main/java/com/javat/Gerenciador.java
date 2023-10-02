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
    private List<Evento> eventos = new ArrayList<Evento>();

    public Gerenciador() {

    }

    public void addEvento(String nome, String data, String local, String responsavel) {
        eventos.add(new Evento(nome, data, local, responsavel));
    }

    public String showEventos() {
        String res = "";
        int contador = 1;
        for (Evento evento : eventos) {
            res += "Evento " + contador + ": Nome: " + evento.getNome() + ", Data: " + evento.getData() + ", Local: " + evento.getLocal() + ", Responsável: " + evento.getResponsavel() + " | ";
            contador++;
        }

        return res;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void importJsonFile() {
        
    }

    public void createJsonFile() throws FileNotFoundException, IOException, ParseException {
        JSONArray array;

		FileWriter writeFile = null;
        File file = new File("saida.json");
        if (!file.exists()) {
            array = new JSONArray();
        } else {
            JSONParser parser = new JSONParser();
            array = (JSONArray) parser
                .parse(new InputStreamReader(new FileInputStream("saida.json"), "UTF-8"));
        }

        for (Evento evento : eventos) {
            JSONObject jsonObject = new JSONObject();

            //Armazena dados em um Objeto JSON
            jsonObject.put("Nome", evento.getNome());
            jsonObject.put("Data", evento.getData());
            jsonObject.put("Local", evento.getLocal());
            jsonObject.put("Responsável", evento.getResponsavel());

            array.add(jsonObject);
            try{
                writeFile = new FileWriter("saida.json");
                //Escreve no arquivo conteudo do Objeto JSON
                writeFile.write(array.toJSONString());
                writeFile.close();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            }
    }
}
