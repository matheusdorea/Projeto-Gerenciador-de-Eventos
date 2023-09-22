package com.gerenciadordeeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JButton button;
    private JTextField nomeEventoCampo;
    private JLabel nomeEventoTexto;
    private JLabel feedback;
    private Gerenciador gerenciador = new Gerenciador();

    public Main() {
        frame = new JFrame("Gerenciador de Eventos");

        panel = new JPanel();
        panel.setLayout(null);

        nomeEventoTexto = new JLabel("Nome do evento:  ");
        nomeEventoTexto.setBounds(10, 20, 100, 25);
        panel.add(nomeEventoTexto);

        nomeEventoCampo = new JTextField();
        nomeEventoCampo.setBounds(120, 20, 100, 25);
        panel.add(nomeEventoCampo);

        button = new JButton("Cadastrar");
        button.setBounds(10, 80, 100, 25);
        button.addActionListener((ActionListener) this);
        panel.add(button);

        feedback = new JLabel("resultado");
        feedback.setBounds(10, 120, 400, 25);
        panel.add(feedback);

        frame.add(panel);
        frame.setSize(350, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String nome;
        nome = nomeEventoCampo.getText();
        gerenciador.addEvento(nome);
        feedback.setText(gerenciador.showEventos());
    }
}