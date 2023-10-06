package com.javat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.json.simple.parser.ParseException;

public class Main {
    //Objetos gerais
    private JFrame frame;
    private JPanel panel;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton voltar;
    private JTextField textbox;
    private JTextField textbox2;
    private JTextField textbox3;
    private JLabel label;
    private JLabel feedback;
    private JLabel data;
    private JLabel hora;

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
        //Criando janela de menu
        frame = new JFrame("Gerenciador de Shows");

        panel = new JPanel();
        panel.setLayout(null);

        telaMenu();

        //Recebendo arquivo json de eventos
        try {
            gerenciador.loadEventArchive();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //Recebendo arquivo json de artistas
        try {
            gerenciador.loadArtistArchive();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        //recebendo json de bandas
        try {
            gerenciador.loadBandArchive();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    //Restante da tela de menu
    public void telaMenu() {
        //restante da tela de menu
        frame.setTitle("Gerenciador de Shows");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("MENU");
        label.setBounds(260, 40, 150, 25);
        panel.add(label);

        button = new JButton("Adicionar Show");
        button.setBounds(100, 70, 150, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddEvento();
            }
        });
        panel.add(button);

        button2 = new JButton("Remover Show");
        button2.setBounds(100, 100, 150, 25);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaRemoveEvento();
            }
        });
        panel.add(button2);

        button3 = new JButton("Editar Show");
        button3.setBounds(100, 130, 150, 25);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaEditarEvento();
            }
        });
        panel.add(button3);

        button4 = new JButton("Add Patrocinador");
        button4.setBounds(100, 160, 150, 25);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddPatrocinador();
            }
        });
        panel.add(button4);

        button6 = new JButton("Cadastrar Artista");
        button6.setBounds(300, 70, 150, 25);
        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaCadastrarArtista();
            }
        });
        panel.add(button6);

        button5 = new JButton("Adicionar Artista");
        button5.setBounds(300, 100, 150, 25);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddArtistas();
            }
        });
        panel.add(button5);

        button7 = new JButton("Cadastrar Banda");
        button7.setBounds(300, 130, 150, 25);
        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaCadastrarBanda();
            }
        });
        panel.add(button7);

        button8 = new JButton("Adicionar Banda");
        button8.setBounds(300, 160, 150, 25);
        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaAddBanda();
            }
        });
        panel.add(button8);

        data = new JLabel();
        data.setBounds(10, 10, 200, 25);
        panel.add(data);
        hora = new JLabel();
        hora.setBounds(500, 10, 200, 25);
        panel.add(hora);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                DateTimeFormatter formatterDate = DateTimeFormat.forPattern("dd/MM/yyyy");
                DateTimeFormatter formatterTime = DateTimeFormat.forPattern("HH:mm:ss");
                while (true) {
                    DateTime dateTime = new DateTime();
                    String strDate = dateTime.toString(formatterDate);
                    String strTime = dateTime.toString(formatterTime);
                    publish(strDate); // Atualiza a GUI com a nova data
                    publish(strTime); // Atualiza a GUI com a nova hora
                    Thread.sleep(1000); // Aguarda 1 segundo antes de obter a próxima atualização
                }
            }
        
            @Override
            protected void process(java.util.List<String> chunks) {
                // Atualiza a interface do usuário com a nova data e hora
                String latestDate = chunks.get(chunks.size() - 2); // Obtém a última data publicada
                String latestTime = chunks.get(chunks.size() - 1); // Obtém a última hora publicada
                if (data != null && hora != null) {
                    data.setText(latestDate);
                    hora.setText(latestTime);
                }
            }
        };
        worker.execute(); // Inicia o SwingWorker para atualização em segundo plano 
    }

    //Tela para adicionar show
     public void telaAddEvento()  {
        frame.setTitle("Adicionar Evento");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        nomeEventoTexto = new JLabel("Nome do evento:  ");
        nomeEventoTexto.setBounds(10, 20, 200, 25);
        panel.add(nomeEventoTexto);

        nomeEventoCampo = new JTextField();
        nomeEventoCampo.setBounds(10, 50, 200, 25);
        panel.add(nomeEventoCampo);

        dataEventoTexto = new JLabel("Data do evento: ");
        dataEventoTexto.setBounds(10, 80, 200, 25);
        panel.add(dataEventoTexto);

        dataEventoCampo = new JTextField();
        dataEventoCampo.setBounds(10, 110, 200, 25);
        panel.add(dataEventoCampo);

        localEventoTexto = new JLabel("Local do evento: ");
        localEventoTexto.setBounds(10, 140, 200, 25);
        panel.add(localEventoTexto);

        localEventoCampo = new JTextField();
        localEventoCampo.setBounds(10, 170, 200, 25);
        panel.add(localEventoCampo);

        responsavelEventoTexto = new JLabel("Responsável do evento: ");
        responsavelEventoTexto.setBounds(10, 200, 200, 25);
        panel.add(responsavelEventoTexto);

        responsavelEventoCampo = new JTextField();
        responsavelEventoCampo.setBounds(10, 230, 200, 25);
        panel.add(responsavelEventoCampo);

        button = new JButton("Cadastrar");
        button.setBounds(10, 280, 120, 25);
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
                } else {
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

                    //Criando arquivo json para eventos
                    try {
                        gerenciador.createEventFile();;
                    } catch (IOException | ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                
            }
        });
        panel.add(button);

        feedback = new JLabel("");
        feedback.setBounds(10, 241, 2000, 50);
        panel.add(feedback);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 310, 120, 25);
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

    //Tela para remover show
    public void telaRemoveEvento() {
        frame.setTitle("Remover Show");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Digite o nome do show que quer remover:");
        label.setBounds(10, 20, 250, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 200, 25);
        panel.add(textbox);

        button = new JButton("Remover");
        button.setBounds(10, 100, 130, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (textbox.getText().equals("")) {
                    feedback.setText("Por favor, preencha o campo corretamente!");
                    return;
                }else {
                    if (gerenciador.eventoExiste(textbox.getText())) {
                        String nomeEvento = textbox.getText();
                        gerenciador.removeEvento(nomeEvento);
                        try {
                            gerenciador.createEventFile();
                        } catch (IOException | ParseException e1) {
                            e1.printStackTrace();
                        }
                        feedback.setText("Show removido!");
                        textbox.setText("");
                    } else {
                        feedback.setText("Show não encontrado!");
                    }
                    
                }
            }
        });
        feedback = new JLabel("");
        feedback.setBounds(10, 61, 2000, 50);
        panel.add(feedback);

        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 130, 130, 25);
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

    //Tela para editar evento
    public void telaEditarEvento() {
        frame.setTitle("Editar Evento");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Digite o nome do evento:");
        label.setBounds(10, 20, 200, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(160, 20, 120, 25);
        panel.add(textbox);


        button = new JButton("Procurar");
        button.setBounds(300, 20, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textbox.getText();
                Evento evento = gerenciador.getEvento(nome);
                if (textbox.getText().equals("")) {
                    feedback.setText("Por favor, preencha o campo corretamente");
                } else {
                    if (gerenciador.eventoExiste(nome)) {
                        textbox2.setText(evento.getNome());
                        dataEventoCampo.setText(evento.getData());
                        localEventoCampo.setText(evento.getLocal());
                        responsavelEventoCampo.setText(evento.getResponsavel());
                        feedback.setText("Edição habilitada!");
                    } else {
                        feedback.setText("Show não encontrado!");
                        textbox.setText("");
                    }
                }
            }
        });
        panel.add(button);

        label = new JLabel("Nome:");
        label.setBounds(10, 50, 50, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 80, 200, 25);
        panel.add(textbox2);

        label = new JLabel("Data:");
        label.setBounds(10, 110, 50, 25);
        panel.add(label);

        dataEventoCampo = new JTextField();
        dataEventoCampo.setBounds(10, 140, 200, 25);
        panel.add(dataEventoCampo);

        localEventoTexto = new JLabel("Local:");
        localEventoTexto.setBounds(10, 170, 50, 25);
        panel.add(localEventoTexto);

        localEventoCampo = new JTextField();
        localEventoCampo.setBounds(10, 200, 200, 25);
        panel.add(localEventoCampo);

        responsavelEventoTexto = new JLabel("Responsável:");
        responsavelEventoTexto.setBounds(10, 230, 100, 25);
        panel.add(responsavelEventoTexto);

        responsavelEventoCampo = new JTextField();
        responsavelEventoCampo.setBounds(10, 260, 200, 25);
        panel.add(responsavelEventoCampo);

        feedback = new JLabel("");
        feedback.setBounds(10, 268, 2000, 50);
        panel.add(feedback);
        panel.add(button);

        button2 = new JButton("Alterar");
        button2.setBounds(10, 305, 120, 25);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Evento evento = gerenciador.getEvento(textbox.getText());

                evento.setNome(textbox2.getText());
                evento.setData(dataEventoCampo.getText());
                evento.setLocal(localEventoCampo.getText());
                evento.setResponsavel(responsavelEventoCampo.getText());

                try {
                    gerenciador.createEventFile();
                } catch (IOException | ParseException e1) {
                    e1.printStackTrace();
                }
                feedback.setText("Alteração concluída!");
                textbox.setText("");
                textbox2.setText("");
                dataEventoCampo.setText("");
                localEventoCampo.setText("");
                responsavelEventoCampo.setText("");
            }
        });
        panel.add(button2);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 335, 120, 25);
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

    //Tela para adicionar artistas ao evento
    public void telaAddArtistas() {
        frame.setTitle("Adicionar Artistas");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do show que será adicionado o artista: ");
        label.setBounds(10, 20, 2000, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 200, 25);
        panel.add(textbox);

        label = new JLabel("Nome do Artista:");
        label.setBounds(10, 80, 200, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 110, 200, 25);
        panel.add(textbox2);

        feedback = new JLabel("");
        feedback.setBounds(10, 121, 2000, 50);
        panel.add(feedback);

        button = new JButton("Adicionar");
        button.setBounds(10, 160, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Adicionando Artista
                String nomeEvento = textbox.getText();
                String nomeArtista = textbox2.getText();
                if (nomeEvento.equals("") ||
                    nomeArtista.equals("")){
                        feedback.setText("Por favor, preencha os campos corretamente!");
                        return;
                } else {
                    if (gerenciador.eventoExiste(textbox.getText())) {
                        if (gerenciador.artistaExiste(textbox2.getText())) {
                            gerenciador.getEvento(nomeEvento).addArtista(nomeArtista);
                            //atualizando no arquivo de eventos
                            try {
                                gerenciador.createEventFile();
                            } catch (IOException | ParseException e1) {
                                e1.printStackTrace();
                            }
                            feedback.setText("Artista adicionado!");
                            textbox.setText("");
                            textbox2.setText("");
                        } else {
                            feedback.setText("Este artista não está cadastrado!");
                        }
                    } else {
                        feedback.setText("Show não encontrado!");
                    }
                }
            }
        });
        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 190, 120, 25);
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

    //Tela para adicionar Patrocinio ao evento
    public void telaAddPatrocinador() {
        frame.setTitle("Adicionar Patrocinador");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do show que será adicionado o Patrocinador: ");
        label.setBounds(10, 20, 2000, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 200, 25);
        panel.add(textbox);

        label = new JLabel("Nome do Patrocinador:");
        label.setBounds(10, 80, 200, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 110, 200, 25);
        panel.add(textbox2);

        label = new JLabel("Valor do patrocínio:");
        label.setBounds(10, 140, 200, 25);
        panel.add(label);

        textbox3 = new JTextField();
        textbox3.setBounds(10, 170, 200, 25);
        panel.add(textbox3);

        feedback = new JLabel("");
        feedback.setBounds(10, 200, 2000, 50);
        panel.add(feedback);

        button = new JButton("Adicionar");
        button.setBounds(10, 230, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Adicionando Artista
                String nomeEvento = textbox.getText();
                String nomePat = textbox2.getText();
                double valorPat = 0;
                Patrocinador pat = null;
                
                valorPat = Double.parseDouble(textbox3.getText());

                //criando novo patrocinador com os valores coletados
                pat = new Patrocinador(nomePat, valorPat);
                

                if (nomeEvento.equals("") ||
                    nomePat.equals("") ||
                    textbox3.getText().equals("")){
                        feedback.setText("Por favor, preencha os campos corretamente!");
                        return;
                } else {
                    if (gerenciador.eventoExiste(textbox.getText())) {
                        gerenciador.getEvento(nomeEvento).addPatrocinador(pat);
                        
                        for (Patrocinador patr : gerenciador.getEvento(nomeEvento).getPatrocinadores()) {
                            System.out.println(patr.getNome());
                        }
                        //atualizando no arquivo de eventos
                        try {
                            gerenciador.createEventFile();
                        } catch (IOException | ParseException e1) {
                            e1.printStackTrace();
                        }
                        feedback.setText("Patrocinador adicionado!");
                        textbox.setText("");
                        textbox2.setText("");
                    } else {
                        feedback.setText("Evento não encontrado");
                    }
                }
            }
        });
        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 260, 120, 25);
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

    //Tela para cadastrar artistas ao gerenciador
    public void telaCadastrarArtista() {
        frame.setTitle("Cadastrar Artista");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do Artista:");
        label.setBounds(10, 20, 110, 25);
        panel.add(label);

        label = new JLabel("Gênero musical:");
        label.setBounds(10, 80, 120, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 200, 25);
        panel.add(textbox);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 110, 200, 25);
        panel.add(textbox2);

        button = new JButton("Cadastrar");
        button.setBounds(10, 160, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textbox.getText().equals("") ||
                    textbox2.getText().equals("")) {
                        feedback.setText("Por favor, preencha os campos corretamente!");
                        return;
                } else {
                    //cadastrando Artista
                    gerenciador.addArtista(new Artista(textbox.getText(), textbox2.getText()));

                    //criando arquivo de artistas
                    try {
                        gerenciador.createArtistFile();
                    } catch (IOException | ParseException e1) {
                        e1.printStackTrace();
                    }

                }
                feedback.setText("Artista Cadastrado!");
                textbox.setText("");
                textbox2.setText("");
            }
        });

        feedback = new JLabel("");
        feedback.setBounds(10, 121, 2000, 50);
        panel.add(feedback);

        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 190, 120, 25);
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

    //tela para adicionar Banda ao evento
    public void telaAddBanda() {
        frame.setTitle("Adicionar Bandas");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome do Evento:");
        label.setBounds(10, 20, 120, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 120, 25);
        panel.add(textbox);

        label = new JLabel("Nome da Banda:");
        label.setBounds(10, 80, 120, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 110, 120, 25);
        panel.add(textbox2);

        feedback = new JLabel("");
        feedback.setBounds(10, 125, 2000, 50);
        panel.add(feedback);

        button = new JButton("Adicionar");
        button.setBounds(10, 165, 120, 25);
        button.addActionListener(new ActionListener() {

            
            public void actionPerformed(ActionEvent e) {
                String nomeEvento = textbox.getText();
                String nomeBanda = textbox2.getText();
                if (nomeEvento.equals("") ||
                    nomeBanda.equals("")){
                        feedback.setText("Por favor, preencha os campos corretamente!");
                } else {
                    if (gerenciador.eventoExiste(textbox.getText())) {
                        if (gerenciador.bandaExiste(textbox2.getText())) {
                            //Adicionando Banda
                            gerenciador.getEvento(nomeEvento).addBanda(nomeBanda);
                            //atualizando no arquivo de eventos
                            try {
                                gerenciador.createEventFile();
                                System.out.println("Cadastrou");
                            } catch (IOException | ParseException e1) {
                                e1.printStackTrace();
                            }
                            feedback.setText("Banda adicionada!");
                            textbox.setText("");
                            textbox2.setText("");
                        } else {
                            feedback.setText("Esta banda não está cadastrada!");
                        }
                    } else {
                        feedback.setText("Show não encontrado!");
                    }
                }
            }
        });

        panel.add(button);

        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 195, 120, 25);
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

    //tela para cadastrar bandas ao gerenciador
    public void telaCadastrarBanda() {
        frame.setTitle("Cadastrar Banda");

        frame.remove(panel);

        panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel("Nome da Banda:");
        label.setBounds(10, 20, 120, 25);
        panel.add(label);

        textbox = new JTextField();
        textbox.setBounds(10, 50, 120, 25);
        panel.add(textbox);

        label = new JLabel("Gênero musical:");
        label.setBounds(10, 80, 120, 25);
        panel.add(label);

        textbox2 = new JTextField();
        textbox2.setBounds(10, 110, 120, 25);
        panel.add(textbox2);

        label = new JLabel("Número de integrantes:");
        label.setBounds(10, 140, 150, 25);
        panel.add(label);

        textbox3 = new JTextField();
        textbox3.setBounds(10, 170, 120, 25);
        panel.add(textbox3);

        feedback = new JLabel("");
        feedback.setBounds(10, 185, 2000, 50);
        panel.add(feedback);













        
        button = new JButton("Cadastrar");
        button.setBounds(10, 225, 120, 25);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (textbox.getText().equals("") ||
                    textbox2.getText().equals("") ||
                    textbox3.getText().equals("")) {
                        feedback.setText("Por favor, preencha os campos corretamente!");
                } else {
                    try {
                        int integrantes = Integer.parseInt(textbox3.getText());
                        //Verificando se integrantes é um numero positivo
                        if (integrantes > 0) {
                            String nome = textbox.getText();        
                            String generoMusical = textbox2.getText();
                            //Cadastrando Banda
                            gerenciador.addBanda(new Banda(nome, generoMusical, integrantes));
                            
                            //Criando arquivo de banda
                            try {
                                gerenciador.createBandFile();
                            } catch (IOException | ParseException e1) {
                                e1.printStackTrace();
                            }
                            feedback.setText("Banda Cadastrada!");
                            textbox.setText("");
                            textbox2.setText("");
                            textbox3.setText("");
                        } else {
                            feedback.setText("O número de integrantes deve ser um valor inteiro maior que zero");
                        }
                    } catch (NumberFormatException ex) {
                        feedback.setText("O número de integrantes deve ser um valor inteiro");
                    }
            
                }
            }
        });
        panel.add(button);












        voltar = new JButton("Voltar ao menu");
        voltar.setBounds(10, 255, 120, 25);
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