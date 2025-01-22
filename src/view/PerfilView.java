// src/view/PerfilView.java
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
        emailField = new JLabel(cliente.getNome());
        editarDadosButton = new JButton("Editar Dados");
    }

    private void setupProfileLayout() {
        JPanel profilePanel = new JPanel(new GridLayout(3, 2, 10, 10));

        profilePanel.add(new JLabel("Nome:"));
        profilePanel.add(nomeField);

        profilePanel.add(new JLabel("Email:"));
        profilePanel.add(emailField);

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
}