package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialView extends TemplateView {

    public TelaInicialView(String titulo) {
        super(titulo);

        JPanel telaInicial = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("<html>Bem vindo ao S.I.S.T.E.M.O- Solução Inovadora para Suportar Transações Eficientes no Mercado Online</html>");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new Dimension(400, 100)); // Ajuste aqui o tamanho desejado

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaInicial.add(header, gbc);

        JButton verProdutos = new JButton("Ver produtos");
        ajustarBotao(verProdutos, gbc, telaInicial);

        JButton verCarrinho = new JButton("Ver carrinho");
        ajustarBotao(verCarrinho, gbc, telaInicial);

        JButton fazerLogin = new JButton("Fazer login");
        ajustarBotao(fazerLogin, gbc, telaInicial);

        JButton cadastrar = new JButton("Cadastrar");
        ajustarBotao(cadastrar, gbc, telaInicial);

        adicionarConteudo(telaInicial);

        JButton ajuda = new JButton("Ajuda");
        JButton sair = new JButton("Sair");
        adicionarAoRodape(ajuda);
        adicionarAoRodape(sair);


        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaCadastroCliente = new cadastroClienteView("Cadastro de Cliente");
                telaCadastroCliente.setVisible(true);
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
