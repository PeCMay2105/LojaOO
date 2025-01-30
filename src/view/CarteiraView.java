package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarteiraView extends TemplateView {

    private JComboBox<String> comboTipoCartao;
    private JList<String> listaCartoes;
    private DefaultListModel<String> modeloListaCartoes;

    public CarteiraView() {
        super("Carteira de Pagamentos");
        criarInterface();
    }

    private void criarInterface() {
        setLayoutDiferente(new BorderLayout(10, 10));

        JButton botaoPix = new JButton("Pagamento com PIX");
        botaoPix.setFont(new Font("Arial", Font.BOLD, 16));
        botaoPix.setPreferredSize(new Dimension(250, 40));

        comboTipoCartao = new JComboBox<>(new String[]{"Selecione um tipo de pagamento", "Cartão de Crédito", "Cartão de Débito"});
        comboTipoCartao.setSelectedIndex(0);
        comboTipoCartao.setFont(new Font("Arial", Font.PLAIN, 16));
        comboTipoCartao.setPreferredSize(new Dimension(250, 40));

        comboTipoCartao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarListaCartoes();
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoPix);
        painelBotoes.add(comboTipoCartao);

        adicionarConteudo(painelBotoes);

        modeloListaCartoes = new DefaultListModel<>();
        listaCartoes = new JList<>(modeloListaCartoes);
        listaCartoes.setVisible(false);
        JScrollPane scrollPane = new JScrollPane(listaCartoes);

        botaoPix.addActionListener(new PagamentoListener("PIX"));

        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 14));
        adicionarAoRodape(botaoSair);
        botaoSair.addActionListener(e -> dispose());
    }

    private void mostrarListaCartoes() {
        if (comboTipoCartao.getSelectedIndex() == 1 || comboTipoCartao.getSelectedIndex() == 2) {
            if (!listaCartoes.isVisible()) {
                JOptionPane.showMessageDialog(this, "Cartões", "Cartões Cadastrados", JOptionPane.INFORMATION_MESSAGE);
                listaCartoes.setVisible(true);
            }
        } else {
            listaCartoes.setVisible(false);
        }
    }

    private class PagamentoListener implements ActionListener {
        private final String tipoPagamento;

        public PagamentoListener(String tipoPagamento) {
            this.tipoPagamento = tipoPagamento;
        }

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(
                    CarteiraView.this,
                    "Pagamento selecionado: " + tipoPagamento,
                    "Confirmação de Pagamento",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    protected void setupLayout() {
        setorHeader.add(setorTitulo, BorderLayout.CENTER);
        setorHeader.add(botaoVoltar, BorderLayout.WEST);

        setorHeader.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        setorHeader.setBackground(new Color(240, 240, 240, 240));

        setorPrincipal.add(setorHeader, BorderLayout.NORTH);
        setorPrincipal.add(setorConteudo, BorderLayout.CENTER);
        setorPrincipal.add(setorFooter, BorderLayout.SOUTH);

        add(setorPrincipal);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial",true);
                telaInicial.setVisible(true);
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CarteiraView carteiraView = new CarteiraView();
            carteiraView.setVisible(true);
        });
    }

}
