package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class CarteiraView extends TemplateView {
    private HashMap<String,String> info;

    public CarteiraView() {
        super("Carteira de Pagamentos");
        criarInterface();
    }

    private void criarInterface() {
        setorConteudo.setLayout(null);

        //setorTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //setorTitulo.setBounds(250, 20, 300, 30);
        //setorConteudo.add(setorTitulo);

        JButton botaoCredito = new JButton("Cartão de Crédito");
        botaoCredito.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoCredito.setBounds(300, 150, 200, 40);
        setorConteudo.add(botaoCredito);

        JButton botaoDebito = new JButton("Cartão de Débito");
        botaoDebito.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoDebito.setBounds(300, 210, 200, 40);
        setorConteudo.add(botaoDebito);

        JButton botaoPix = new JButton("PIX");
        botaoPix.setFont(new Font("Arial", Font.PLAIN, 16));
        botaoPix.setBounds(300, 270, 200, 40);
        setorConteudo.add(botaoPix);

        JButton botaoPagar = new JButton("Pagar");
        botaoPagar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarAoRodape(botaoPagar);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarAoRodape(botaoSair);

        botaoSair.addActionListener(e -> dispose());

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaCarrinho = new CarrinhoView("Carrinho", info);
                telaCarrinho.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarteiraView carteiraView = new CarteiraView();
            carteiraView.setSize(800, 600);
            carteiraView.setLocationRelativeTo(null);
            carteiraView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            carteiraView.setVisible(true);
        });
    }
}
