package view;

import model.Cliente;
import model.Global;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarView extends TemplateView {

    private Cliente cliente;
    private JTextField nomeField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton salvarButton;

    public EditarView(String titulo, Cliente cliente) {
        super(titulo);
        this.cliente = cliente;
        initializeComponents();
        setupLayout();
        setupEvents();
    }

    private void initializeComponents() {
        nomeField = new JTextField(cliente.getNome(), 20);
        emailField = new JTextField(cliente.getLogin(), 20);
        senhaField = new JPasswordField(20);
        salvarButton = new JButton("Salvar");
    }

    private void setupLayout() {
        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        editPanel.add(new JLabel("Nome:"));
        editPanel.add(nomeField);

        editPanel.add(new JLabel("Email:"));
        editPanel.add(emailField);

        editPanel.add(new JLabel("Senha:"));
        editPanel.add(senhaField);

        editPanel.add(new JLabel("Confirmar Senha:"));
        editPanel.add(senhaField);
        // adicionar metodo para lançar erro caso as senhas não sejam iguais

        editPanel.add(new JLabel());

        editPanel.add(salvarButton);

        adicionarConteudo(editPanel);
    }

    protected void setupEvents() {
        if (salvarButton != null) {
            salvarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // salvar no BD
                    Pessoa dadosAtuais = Global.getPessoa();
                    Global.setPessoa(new Cliente(nomeField.getText(), dadosAtuais.getCPF(), emailField.getText(), senhaField.getText(), dadosAtuais.getIdade()));
                    PerfilView perfil = new PerfilView("Perfil", (Cliente) Global.getPessoa());
                    perfil.setVisible(true);
                    dispose();
                }
            });
        } else {
            System.err.println("salvarButton is null");
        }
    }
}