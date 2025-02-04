package view;

import controller.VendedorController;
import model.Pessoa;
import model.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GerenciarFuncionariosView extends TemplateView {
    private JTextField campoPesquisa;
    private JScrollPane scrollPane;
    private JPanel vendedoresPanel;

    private List <Vendedor> vendedores;

    public GerenciarFuncionariosView(String titulo, Pessoa usuarioAtual) {
        super(titulo);
        setLayout(null);
        setSize(900, 700);

        // Campo de pesquisa
        campoPesquisa = new JTextField();
        campoPesquisa.setBounds(500, 130, 200, 30);
        add(campoPesquisa);

        // Botão Pesquisar
        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(730, 130, 100, 30);
        add(botaoPesquisar);

        // Painel de vendedores
        vendedoresPanel = new JPanel();
        vendedoresPanel.setLayout(new BoxLayout(vendedoresPanel, BoxLayout.Y_AXIS));

        // Carregar vendedores
        List<Vendedor> vendedores;
        try {
            vendedores = VendedorController.pesquisarVendedores("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /////// TODO: 06/06/2021 Implementar a lógica para carregar todos os vendedores

        // Adicionar vendedores ao painel
        for (Vendedor vendedor : vendedores) {
            JPanel vendedorLinha = new JPanel(new BorderLayout());
            vendedorLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel nomeVendedor = new JLabel(vendedor.getNome() + " - CPF: " + vendedor.getCPF());
            nomeVendedor.setFont(new Font("Arial", Font.PLAIN, 14));
            vendedorLinha.add(nomeVendedor, BorderLayout.CENTER);

            JButton detalhesButton = new JButton("Detalhes");
            detalhesButton.setFont(new Font("Arial", Font.PLAIN, 12));
            detalhesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implementar lógica para exibir detalhes do vendedor
                }
            });
            vendedorLinha.add(detalhesButton, BorderLayout.WEST);

            JButton editarButton = new JButton("Editar");
            editarButton.setFont(new Font("Arial", Font.PLAIN, 12));
            editarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implementar lógica para editar o vendedor
                }
            });
            vendedorLinha.add(editarButton, BorderLayout.CENTER);

            JButton deletarButton = new JButton("Deletar");
            deletarButton.setFont(new Font("Arial", Font.PLAIN, 12));
            deletarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Implementar lógica para deletar o vendedor
                }
            });
            vendedorLinha.add(deletarButton, BorderLayout.EAST);

            vendedoresPanel.add(vendedorLinha);
        }

        // ScrollPane para a lista de vendedores
        scrollPane = new JScrollPane(vendedoresPanel);
        scrollPane.setBounds(50, 70, 370, 400);
        add(scrollPane);

        // Ação do botão Pesquisar
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaVendedores(campoPesquisa.getText());
            }
        });
    }

    // Método para atualizar a lista de vendedores com base na pesquisa
    private void atualizarListaVendedores(String query) {
        vendedoresPanel.removeAll();
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            VendedorController.pesquisarVendedores(query);
            /////// TODO: 06/06/2021 Implementar a lógica de pesquisa de vendedores
            for (Vendedor vendedor : vendedores) {
                JPanel vendedorLinha = new JPanel(new BorderLayout());
                vendedorLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel nomeVendedor = new JLabel(vendedor.getNome());
                nomeVendedor.setFont(new Font("Arial", Font.PLAIN, 14));
                vendedorLinha.add(nomeVendedor, BorderLayout.CENTER);

                JButton detalhesButton = new JButton("Detalhes");
                detalhesButton.setFont(new Font("Arial", Font.PLAIN, 12));
                detalhesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implementar lógica para exibir detalhes do vendedor
                    }
                });
                vendedorLinha.add(detalhesButton, BorderLayout.WEST);

                JButton editarButton = new JButton("Editar");
                editarButton.setFont(new Font("Arial", Font.PLAIN, 12));
                editarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implementar lógica para editar o vendedor
                    }
                });
                vendedorLinha.add(editarButton, BorderLayout.CENTER);

                JButton deletarButton = new JButton("Deletar");
                deletarButton.setFont(new Font("Arial", Font.PLAIN, 12));
                deletarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Implementar lógica para deletar o vendedor
                    }
                });
                vendedorLinha.add(deletarButton, BorderLayout.EAST);

                vendedoresPanel.add(vendedorLinha);
            }

            vendedoresPanel.revalidate();
            vendedoresPanel.repaint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }





}}