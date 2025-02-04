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
        setLayout(null);
        setSize(900, 700); // Aumentando o tamanho do painel principal

        // Campo de pesquisa
        JTextArea campoPesquisa = new JTextArea();
        campoPesquisa.setBounds(500, 130, 200, 30);
        add(campoPesquisa);

        // Botão Pesquisar
        JButton botaoPesquisar = new JButton("Pesquisar");
        botaoPesquisar.setBounds(730, 130, 100, 30);
        add(botaoPesquisar);

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

            JLabel nomeProduto = new JLabel(produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()));
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
        scrollPane.setBounds(50, 70, 370, 400); // Aumentando tamanho da lista de produtos
        add(scrollPane);

        // Botão Ver Carrinho
        JButton verCarrinho = null;
        if (Global.pessoa instanceof Cliente) {
            try {
                verCarrinho = new JButton("Ver carrinho(" + Global.database.getQuantidadeCarrinho(Global.pessoa.getCPF()) + ")");
                verCarrinho.setBounds(50, 570, 200, 50);
                add(verCarrinho);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Botões de ajuda e sair
        JButton ajuda = new JButton("Ajuda");
        ajuda.setBounds(650, 600, 100, 30);
        add(ajuda);

        JButton sair = new JButton("Sair");
        sair.setBounds(770, 600, 100, 30);
        add(sair);

        sair.addActionListener(e -> dispose());

        // Ação do botão Ver Carrinho
        if (Global.pessoa instanceof Cliente) {
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
        // Definir posição do título
        setorTitulo.setBounds(300, 10, 400, 30);
        setorTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(setorTitulo);

// Definir posição do botão de voltar
        botaoVoltar.setBounds(10, 10, 50, 30);
        add(botaoVoltar);

        // Ação do botão Voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    TemplateView TelaInicialView = new TelaInicialView("", true,Global.pessoa);
                    TelaInicialView.setVisible(true);
                    dispose();

            }
        });
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

    public static void main(String[] args) {
        TemplateView tela = new ListaProdutosView("Lista de Produtos");
        tela.setVisible(true);
    }
}
