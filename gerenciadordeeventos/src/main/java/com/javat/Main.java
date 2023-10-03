package com.javat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.parser.ParseException;

public class Main {
    //objetos gerais
    private JFrame frame;
    private JPanel panel;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton voltar;
    private JTextField textbox;
    private JTextField textbox2;
    private JLabel label;
    private JLabel label2;
    private JLabel feedback;

    //objetos da tela de adicionar evento
    private JTextField nomeEventoCampo;
    private JLabel dataEventoTexto;
    private JLabel nomeEventoTexto;
    private JTextField dataEventoCampo;
    private JLabel localEventoTexto;
    private JLabel responsavelEventoTexto;
    private JTextField localEventoCampo;
    private JTextField responsavelEventoCampo;

    //objeto que vai controlar os eventos
    private Gerenciador gerenciador = new Gerenciador();

    //método construtor da classe principal
    public Main() {
        //criando janela de menu
        frame = new JFrame("Gerenciador de Shows");

        panel = new JPanel();
        panel.setLayout(null);

        telaMenu();

        //recendo arquivo json
        try {
            gerenciador.loadArchive();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    //restante da tela de menu
    public void telaMenu() {
        //restante da tela de menu
        frame.setTitle("Gerenciador de Shows");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Escolha uma das opções:");
        label.setBounds(200, 20, 150, 25);
        panel.add(label);

        button = new JButton("Adicionar Evento");
        button.setBounds(210, 50, 130, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddEvento();
            }
        });
        panel.add(button);

        button2 = new JButton("Remover Evento");
        button2.setBounds(210, 80, 130, 25);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaRemoveEvento();
            }
        });
        panel.add(button2);

        button3 = new JButton("Editar Evento");
        button3.setBounds(210, 110, 130, 25);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(button3);

        button4 = new JButton("Listar Evento");
        button4.setBounds(210, 140, 130, 25);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(button4);

        button5 = new JButton("Adicionar Artista");
        button5.setBounds(210, 170, 130, 25);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddArtistas();
            }
        });
        panel.add(button5);

        button6 = new JButton("Cadastrar Artista");
        button6.setBounds(200, 200, 150, 25);
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaCadastrarArtista();
            }
        });
        panel.add(button6);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    //tela remover evento
    public void telaRemoveEvento() {
        frame.setTitle("Remover Evento");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Digite o nome do evento que quer remover:");
        label.setBounds(160, 20, 250, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(220, 50, 120, 25);
        panel.add(textbox);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 240, 120, 25);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMenu();
            }
        });
        panel.add(voltar);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    //tela para cadastrar artistas ao gerenciador
    public void telaCadastrarArtista() {
        frame.setTitle("Cadastrar Artista");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do Artista:");
        label.setBounds(20, 50, 120, 25);
        panel.add(label);

        label = new JLabel("Gênero musical:");
        label.setBounds(20, 80, 120, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(120, 50, 120, 25);
        panel.add(textbox);

        textbox2 = new JTextField();
        textbox2.setBounds(120, 80, 120, 25);
        panel.add(textbox2);

        button = new JButton("Cadastrar");
        button.setBounds(10, 210, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //cadastrando Artista
                gerenciador.addArtista(new Artista(textbox.getText(), textbox2.getText()));
            }
        });
        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 240, 120, 25);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMenu();
            }
        });
        panel.add(voltar);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    //tela para adicionar artistas ao evento
    public void telaAddArtistas() {
        frame.setTitle("Adicionar Artistas");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do Evento:");
        label.setBounds(20, 50, 120, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(120, 50, 120, 25);
        panel.add(textbox);

        label = new JLabel("Nome do Artista:");
        label.setBounds(20, 80, 120, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(120, 80, 120, 25);
        panel.add(textbox2);

        button = new JButton("Adicionar");
        button.setBounds(10, 210, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Adicionando Artista
                String nomeEvento = textbox.getText();
                String nomeArtista = textbox2.getText();
                if (gerenciador.eventoExiste(textbox.getText())) {
                    if (gerenciador.artistaExiste(textbox2.getText())) {
                        gerenciador.getEvento(nomeEvento).addArtista(nomeArtista);
                        //atualizando no arquivo
                        try {
                            gerenciador.createJsonFile();
                            System.out.println("Cadastrou");
                        } catch (IOException | ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    //impossível adicionar
                    System.out.println("não foi possível adicionar");
                }
            }
        });
        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 240, 120, 25);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMenu();
            }
        });
        panel.add(voltar);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    //tela adicionar evento
    public void telaAddEvento()  {
        frame.setTitle("Adicionar Evento");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        nomeEventoTexto = new JLabel("Nome do evento:  ");
        nomeEventoTexto.setBounds(10, 20, 100, 25);
        panel.add(nomeEventoTexto);

        nomeEventoCampo = new JTextField();
        nomeEventoCampo.setBounds(120, 20, 100, 25);
        panel.add(nomeEventoCampo);

        dataEventoTexto = new JLabel("Data do evento: ");
        dataEventoTexto.setBounds(10, 50, 100, 25);
        panel.add(dataEventoTexto);

        dataEventoCampo = new JTextField();
        dataEventoCampo.setBounds(120, 50, 100, 25);
        panel.add(dataEventoCampo);

        localEventoTexto = new JLabel("Local do evento: ");
        localEventoTexto.setBounds(10, 80, 100, 25);
        panel.add(localEventoTexto);

        localEventoCampo = new JTextField();
        localEventoCampo.setBounds(120, 80, 100, 25);
        panel.add(localEventoCampo);

        responsavelEventoTexto = new JLabel("Responsável do evento: ");
        responsavelEventoTexto.setBounds(10, 110, 150, 25);
        panel.add(responsavelEventoTexto);

        responsavelEventoCampo = new JTextField();
        responsavelEventoCampo.setBounds(160, 110, 100, 25);
        panel.add(responsavelEventoCampo);

        button = new JButton("Cadastrar");
        button.setBounds(10, 140, 100, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome;
                String data;
                String local;
                String responsavel;
                if (nomeEventoCampo.getText().equals("") ||
                    dataEventoCampo.getText().equals("") ||
                    localEventoCampo.getText().equals("") ||
                    responsavelEventoCampo.getText().equals("")) {
                        feedback.setText("Por favor, preencha os campos corretamente");
                        return;
                    }
                nome = nomeEventoCampo.getText();
                data = dataEventoCampo.getText();
                local = localEventoCampo.getText();
                responsavel = responsavelEventoCampo.getText();
                gerenciador.addEvento(new Evento(nome, data, local, responsavel));
                feedback.setText("Evento adicionado");
                nomeEventoCampo.setText("");
                dataEventoCampo.setText("");
                localEventoCampo.setText("");
                responsavelEventoCampo.setText("");

                //criando arquivo json
                try {
                    gerenciador.createJsonFile();
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(button);

        feedback = new JLabel("");
        feedback.setBounds(10, 170, 2000, 50);
        panel.add(feedback);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 240, 120, 25);
        voltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaMenu();
            }
        });
        panel.add(voltar);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}