package view;

import model.Cliente;
import model.Global;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerfilView extends TemplateView {

    private Cliente cliente;
    Pessoa usuario = Global.getPessoa();
    private JLabel nomeField;
    private JLabel emailField;
    private JButton editarDadosButton;

    public PerfilView(String titulo, Cliente cliente) {
        super(titulo);
        this.cliente = cliente;
        initializeProfileComponents();
        setupProfileLayout();
        setupProfileEvents();
    }

    private void initializeProfileComponents() {
        nomeField = new JLabel(cliente.getNome());
        emailField = new JLabel(cliente.getLogin());
        editarDadosButton = new JButton("Editar Dados");
    }

    private void setupProfileLayout() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setPreferredSize(new Dimension(400, 300));

        ImageIcon imagemPerfil = new ImageIcon("C:\\Users\\Usuário\\Downloads\\download.png");
        JLabel labelImagem = new JLabel(imagemPerfil);
        labelImagem.setBounds(320, 80, 100, 100);
        profilePanel.add(labelImagem);

        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(300, 250, 50, 25);
        profilePanel.add(nomeLabel);

        nomeField.setBounds(350, 250, 100, 25);
        profilePanel.add(nomeField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(300, 300, 50, 25);
        profilePanel.add(emailLabel);

        emailField.setBounds(350, 300, 100, 25);
        profilePanel.add(emailField);

        editarDadosButton.setBounds(550, 422, 150, 25);
        profilePanel.add(editarDadosButton);

        adicionarConteudo(profilePanel);
    }

    private void setupProfileEvents() {
        editarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditarView("Editar Dados", cliente).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente cli= new Cliente("aranldo","25","penis12","oi",9);
            PerfilView tela = new PerfilView("Piroca",cli);
            tela.setVisible(true);
        });
    }
}