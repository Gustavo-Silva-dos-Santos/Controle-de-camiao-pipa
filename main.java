package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main extends JFrame {
    private PipaIrregularDao pipaIrregularDao = new PipaIrregularDao();

    // Componentes da interface gráfica
    private JTextField nomeClienteField, diaField, mesField, anoField, ruaField, numeroField, bairroField;
    private JCheckBox temFotoCheck;
    private JTextArea situacaoAguaArea;
    private JTextArea listagemPipasArea;

    public Main() {
        super("Sistema de Cadastro de Pipas Irregulares");
        setLayout(new BorderLayout());

        // Painel de cadastro
        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(new GridLayout(9, 2));
        painelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastro de Pipa Irregular"));

        painelCadastro.add(new JLabel("Nome do Cliente:"));
        nomeClienteField = new JTextField(20);
        painelCadastro.add(nomeClienteField);

        painelCadastro.add(new JLabel("Dia:"));
        diaField = new JTextField(5);
        painelCadastro.add(diaField);

        painelCadastro.add(new JLabel("Mês:"));
        mesField = new JTextField(5);
        painelCadastro.add(mesField);

        painelCadastro.add(new JLabel("Ano:"));
        anoField = new JTextField(5);
        painelCadastro.add(anoField);

        painelCadastro.add(new JLabel("Tem foto:"));
        temFotoCheck = new JCheckBox();
        painelCadastro.add(temFotoCheck);

        painelCadastro.add(new JLabel("Situação da Água:"));
        situacaoAguaArea = new JTextArea(2, 20);
        painelCadastro.add(new JScrollPane(situacaoAguaArea));

        painelCadastro.add(new JLabel("Rua:"));
        ruaField = new JTextField(20);
        painelCadastro.add(ruaField);

        painelCadastro.add(new JLabel("Número:"));
        numeroField = new JTextField(5);
        painelCadastro.add(numeroField);

        painelCadastro.add(new JLabel("Bairro:"));
        bairroField = new JTextField(20);
        painelCadastro.add(bairroField);

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER)); // Alinha os botões no centro

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setPreferredSize(new Dimension(120, 40)); // Define o tamanho do botão "Cadastrar"
        painelBotoes.add(cadastrarButton); // Adiciona o botão "Cadastrar"

        JButton listarButton = new JButton("Listar Pipas");
        listarButton.setPreferredSize(new Dimension(120, 40)); // Define o tamanho do botão "Listar Pipas"
        painelBotoes.add(listarButton); // Adiciona o botão "Listar Pipas"

        // Painel de listagem
        JPanel painelListagem = new JPanel(new BorderLayout());
        painelListagem.setBorder(BorderFactory.createTitledBorder("Listagem de Pipas Cadastradas"));

        listagemPipasArea = new JTextArea(10, 30);
        listagemPipasArea.setEditable(false);
        painelListagem.add(new JScrollPane(listagemPipasArea), BorderLayout.CENTER);

        // Adiciona os painéis principais ao frame
        add(painelCadastro, BorderLayout.NORTH);
        add(painelListagem, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH); // Adiciona o painel de botões ao sul do frame

        // Ação para o botão de cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPipa();
            }
        });

        // Ação para o botão de listar
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPipas();
            }
        });

        // Configurações da janela
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    // Método para cadastrar uma nova pipa
    private void cadastrarPipa() {
        String cliente = nomeClienteField.getText();
        int dia = Integer.parseInt(diaField.getText());
        int mes = Integer.parseInt(mesField.getText());
        int ano = Integer.parseInt(anoField.getText());
        boolean temFoto = temFotoCheck.isSelected();
        String situacaoAgua = situacaoAguaArea.getText();
        String rua = ruaField.getText();
        int numero = Integer.parseInt(numeroField.getText());
        String bairro = bairroField.getText();

        PipaIrregular pipa = new PipaIrregular(cliente, dia, mes, ano, temFoto, situacaoAgua, rua, numero, bairro);
        pipaIrregularDao.cadastrarPipa(pipa);

        // Limpa os campos após o cadastro
        nomeClienteField.setText("");
        diaField.setText("");
        mesField.setText("");
        anoField.setText("");
        situacaoAguaArea.setText("");
        ruaField.setText("");
        numeroField.setText("");
        bairroField.setText("");
        temFotoCheck.setSelected(false);

        JOptionPane.showMessageDialog(this, "Pipa irregular cadastrada com sucesso!");
    }

    // Método para listar todas as pipas cadastradas
    private void listarPipas() {
        List<PipaIrregular> pipas = pipaIrregularDao.listarPipas();
        if (pipas.isEmpty()) {
            listagemPipasArea.setText("Nenhuma pipa cadastrada.");
        } else {
            StringBuilder listagem = new StringBuilder();
            for (PipaIrregular pipa : pipas) {
                listagem.append(pipa.toString()).append("\n");
            }
            listagemPipasArea.setText(listagem.toString());
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
