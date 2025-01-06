package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaProdutosView extends TemplateView {

    public ListaProdutosView(String titulo) {
        super(titulo);

        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JPanel produtosPanel = new JPanel();
        produtosPanel.setLayout(new BoxLayout(produtosPanel, BoxLayout.Y_AXIS));

        String[] produtos = {"Produto 1", "Produto 2", "Produto 3", "Produto 4", "Produto 5", "Produto 6", "Produto 7", "Produto 8", "Produto 9", "Produto 10"};

        for (String produto : produtos) {
            JPanel produtoLinha = new JPanel(new BorderLayout());
            produtoLinha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel nomeProduto = new JLabel(produto);
            nomeProduto.setFont(new Font("Arial", Font.PLAIN, 14));
            produtoLinha.add(nomeProduto, BorderLayout.CENTER);

            JButton adicionarButton = new JButton("Adicionar");
            adicionarButton.setFont(new Font("Arial", Font.PLAIN, 12));
            adicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, produto + " adicionado ao carrinho!", "Produto Adicionado", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            produtoLinha.add(adicionarButton, BorderLayout.EAST);

            produtosPanel.add(produtoLinha);
        }

        JScrollPane scrollPane = new JScrollPane(produtosPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        telaInicial.add(scrollPane, gbc);

        JButton verCarrinho = new JButton("Ver carrinho");
        ajustarBotao(verCarrinho, gbc, telaInicial);

        adicionarConteudo(telaInicial);

        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);

        verCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView carrinhoView = new CarrinhoView("Carrinho");
                carrinhoView.setVisible(true);
                dispose();
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial");
                telaInicial.setVisible(true);
                dispose();
            }
        });
    }

    private void ajustarBotao(JButton botao, GridBagConstraints gbc, JPanel painel) {
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        gbc.gridwidth = 2;
        painel.add(botao, gbc);
    }
}
