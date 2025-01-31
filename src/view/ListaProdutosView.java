package view;

import controller.CarrinhoController;
import controller.Tabela;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import model.Helper;

public class ListaProdutosView extends TemplateView {
    HashMap<String, String> userData;
    JScrollPane scrollPane;
    JPanel produtosPanel;

    public ListaProdutosView(String titulo) {
        super(titulo);

        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campo de pesquisa
        JTextArea campoPesquisa = new JTextArea();
        campoPesquisa.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        telaInicial.add(campoPesquisa, gbc);

        // Botão Pesquisar
        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setPreferredSize(new Dimension(100, 30)); // Tamanho ajustado
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // Botão não preenche o espaço horizontal
        telaInicial.add(botaoPesquisar, gbc);

        // Painel de produtos
        produtosPanel = new JPanel();
        produtosPanel.setLayout(new BoxLayout(produtosPanel, BoxLayout.Y_AXIS));

        // Carregar produtos
        List<Produto> produtos;
        try {
            produtos = Helper.converterProdutos(Global.getDatabase().consulta(Tabela.produto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Adicionar produtos ao painel
        for (Produto produto : produtos) {
            JPanel produtoLinha = new JPanel(new BorderLayout());
            produtoLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel nomeProduto = new JLabel(produto.getNome());
            nomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
            produtoLinha.add(nomeProduto, BorderLayout.CENTER);



            JButton adicionarButton = new JButton("Adicionar");
            adicionarButton.setFont(new Font("Arial", Font.PLAIN, 12));
            adicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Global.pessoa instanceof Cliente) {
                        JOptionPane.showMessageDialog(null, produto + " adicionado ao carrinho!", "Produto Adicionado", JOptionPane.INFORMATION_MESSAGE);

                        CarrinhoController carrinhoController = new CarrinhoController(Global.getPessoa());
                        carrinhoController.adicionarProduto(produto, 1);
                    }
                }
            });
            produtoLinha.add(adicionarButton, BorderLayout.EAST);

            produtosPanel.add(produtoLinha);
        }

        // ScrollPane para a lista de produtos
        scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        telaInicial.add(scrollPane, gbc);

        // Botão Ver Carrinho
        JButton verCarrinho = null;
        if(Global.pessoa instanceof Cliente) {
            try {


                verCarrinho = new JButton("Ver carrinho(" + Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF()) + ")");

                ajustarBotao(verCarrinho, gbc, telaInicial);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        adicionarConteudo(telaInicial);
//        adicionarConteudo(botaoPesquisar);

        // Botões de ajuda e sair
        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);

        // Ação do botão Ver Carrinho
        if(Global.pessoa instanceof Cliente) {
            verCarrinho.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TemplateView carrinhoView = new CarrinhoView("Carrinho", userData);
                    carrinhoView.setVisible(true);
                    dispose();
                }
            });
        }
        // Ação do botão Pesquisar
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaProdutos(campoPesquisa.getText());
            }
        });

        // Ação do botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial", true, Global.getPessoa());
                telaInicial.setVisible(true);
                dispose();
            }
        });
    }

    // Método para ajustar botões
    private void ajustarBotao(JButton botao, GridBagConstraints gbc, JPanel painel) {
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(botao, gbc);
    }

    // Método para atualizar a lista de produtos com base na pesquisa
    private void atualizarListaProdutos(String query) {
        produtosPanel.removeAll();
        List<Produto> produtos;
        try {
            produtos = Global.database.pesquisaProdutos(query);
            System.out.println(produtos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Produto produto : produtos) {
            System.out.println(produto.getNome());
            JPanel produtoLinha = new JPanel(new BorderLayout());
            produtoLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel nomeProduto = new JLabel(produto.getNome());
            nomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
            produtoLinha.add(nomeProduto, BorderLayout.CENTER);

            JButton adicionarButton = new JButton("Adicionar");
            adicionarButton.setFont(new Font("Arial", Font.PLAIN, 12));
            adicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Global.pessoa instanceof Cliente) {
                        JOptionPane.showMessageDialog(null, produto + " adicionado ao carrinho!", "Produto Adicionado", JOptionPane.INFORMATION_MESSAGE);

                        CarrinhoController carrinhoController = new CarrinhoController(Global.getPessoa());
                        carrinhoController.adicionarProduto(produto, 1);
                    }
                }
            });
            produtoLinha.add(adicionarButton, BorderLayout.EAST);

            produtosPanel.add(produtoLinha);

        }

        produtosPanel.revalidate();
        produtosPanel.repaint();
    }
}