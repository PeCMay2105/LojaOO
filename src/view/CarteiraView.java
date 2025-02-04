package view;

import controller.VendaController;
import model.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class CarteiraView extends TemplateView {
    private HashMap<String,String> info;
    private String numero;
    public CarteiraView() {
        super("Carteira de Pagamentos");
        criarInterface();
    }

    private void criarInterface() {
        setorConteudo.setLayout(null);

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

        JPanel painelCartao = new JPanel();
        painelCartao.setLayout(new GridLayout(5, 2, 10, 10));
        painelCartao.setBounds(200, 350, 400, 200);
        painelCartao.setVisible(false);
        setorConteudo.add(painelCartao);

        JLabel labelNumero = new JLabel("Número do Cartão:");
        JTextField campoNumero = new JTextField();
        JLabel labelNome = new JLabel("Nome do Titular:");
        JTextField campoNome = new JTextField();
        JLabel labelValidade = new JLabel("Data de Validade:");
        JTextField campoValidade = new JTextField();
        JLabel labelCodigo = new JLabel("Código de Segurança:");
        JTextField campoCodigo = new JTextField();
        JButton botaoConfirmar = new JButton("Confirmar");

        painelCartao.add(labelNumero);
        painelCartao.add(campoNumero);
        painelCartao.add(labelNome);
        painelCartao.add(campoNome);
        painelCartao.add(labelValidade);
        painelCartao.add(campoValidade);
        painelCartao.add(labelCodigo);
        painelCartao.add(campoCodigo);
        painelCartao.add(new JLabel());
        painelCartao.add(botaoConfirmar);

        botaoCredito.addActionListener(e -> painelCartao.setVisible(true));
        botaoDebito.addActionListener(e -> painelCartao.setVisible(true));

        botaoConfirmar.addActionListener(e -> {
            String numero = campoNumero.getText().trim();
            this.numero = numero;
            String nome = campoNome.getText().trim();
            String validade = campoValidade.getText().trim();
            String codigo = campoCodigo.getText().trim();

            if (numero.isEmpty() || nome.isEmpty() || validade.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (numero.length() != 16) {
                JOptionPane.showMessageDialog(this, "Número do cartão deve ter 16 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Processar os dados do cartão
            JOptionPane.showMessageDialog(this, "Dados do cartão confirmados.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            painelCartao.setVisible(false);
        });

        JButton botaoPagar = new JButton("Pagar");
        botaoPagar.setFont(new Font("Arial", Font.BOLD, 15));
        adicionarAoRodape(botaoPagar);

        botaoPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaFinalizacao = new FinalizacaoView("Finalização", Global.getPessoa(), Integer.valueOf(numero.substring(11, 15)));
                telaFinalizacao.setVisible(true);
                dispose();
            }
        });

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
