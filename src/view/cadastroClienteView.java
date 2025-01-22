package view;

import controller.ClienteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cadastroClienteView extends TemplateView {
    public cadastroClienteView(String titulo) {
        super(titulo);
        JPanel telaCadastroCliente = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Cadastro");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaCadastroCliente.add(header, gbc);

        JLabel nome = new JLabel("Nome:");
        JTextField campoNome = new JTextField(25);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(nome, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoNome, gbc);

        JLabel cpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(cpf, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoCpf, gbc);

        JLabel email = new JLabel("Email:");
        JTextField campoEmail = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(email, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoEmail, gbc);

        JLabel senha = new JLabel("Senha:");
        JTextField campoSenha = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(senha, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoSenha, gbc);

        JLabel idade = new JLabel("Idade:");
        JTextField campoIdade = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(idade, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoIdade, gbc);



        JLabel telefone = new JLabel("Telefone:");
        JTextField campoTelefone = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(telefone, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoTelefone, gbc);

        JButton cadastrar = new JButton("Cadastrar");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        telaCadastroCliente.add(cadastrar, gbc);

        JButton cancelar = new JButton("Cancelar");
        JButton ajuda = new JButton("Ajuda");
        adicionarConteudo(telaCadastroCliente);
        adicionarAoRodape(cancelar);
        adicionarAoRodape(ajuda);


        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial",false);
                telaInicial.setVisible(true);
                dispose();
            }
        });
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria e exibe a nova interface gráfica de sucesso

                ClienteController clienteController = new ClienteController();
                clienteController.criaCliente(campoNome.getText(), campoCpf.getText(), campoEmail.getText(), campoSenha.getText(), Integer.parseInt(campoIdade.getText()));

                JFrame sucessoFrame = new JFrame("Sucesso");
                sucessoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sucessoFrame.setSize(500, 300);
                sucessoFrame.setLocationRelativeTo(null);
                sucessoFrame.add(new FimCadastroView("Sucesso"));
                sucessoFrame.setVisible(true);
                dispose();
            }
        });

    }
}
