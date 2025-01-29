package view;

import model.Cliente;
import model.Global;
import model.Pessoa;
import model.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PerfilView extends TemplateView {

    private Cliente cliente;
    private Vendedor vendedor;
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
    public PerfilView(String titulo, Vendedor vendedor){
        super(titulo);
        this.vendedor = vendedor;
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
            Cliente cli= new Cliente("aranldo","25","penis12","oi", new Date(21,05,2004));
            PerfilView tela = new PerfilView("Piroca",cli);
            tela.setVisible(true);
        });
    }
}