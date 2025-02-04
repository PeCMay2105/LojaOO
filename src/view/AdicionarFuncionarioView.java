package view;

import model.Pessoa;
import model.Vendedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import static model.Global.database;

/**
 * Classe que representa a interface gráfica para adicionar um funcionário.
 */
public class AdicionarFuncionarioView extends TemplateView {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField dataNascimentoField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField salarioField;
    private JTextField comissaoField;

    /**
     * Construtor da classe AdicionarFuncionarioView.
     *
     * @param titulo O título da janela.
     * @param usuarioAtual O usuário atual.
     */
    public AdicionarFuncionarioView(String titulo, Pessoa usuarioAtual) {
        super(titulo);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
        dataNascimentoField = new JTextField();
        panel.add(dataNascimentoField);

        panel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField();
        panel.add(telefoneField);

        panel.add(new JLabel("email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("senha:"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        panel.add(new JLabel("salario:"));
        salarioField = new JTextField();
        panel.add(salarioField);

        panel.add(new JLabel("comissao:"));
        comissaoField = new JTextField();
        panel.add(comissaoField);

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                double comissao = Double.valueOf(comissaoField.getText());
                String nome = nomeField.getText();
                String dateText = dataNascimentoField.getText();
                String[] dateParts = dateText.split("/");
                String formattedDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
                Date dataNascimento = Date.valueOf(formattedDate);
                double salario = Double.parseDouble(salarioField.getText());
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                try {
                    database.cadastrarVendedor(new Vendedor(nome, cpf, email, senha, dataNascimento, Double.valueOf(salario), comissao));
                    JOptionPane.showMessageDialog(null, "Vendedor adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar vendedor", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Vendedor adicionado: " + nome);
            }
        });
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaInicialView telaInicial = new TelaInicialView("Tela Inicial", true, usuarioAtual);
                telaInicial.setVisible(true);
                dispose();
            }
        });
        panel.add(botaoAdicionar);

        adicionarConteudo(panel);
    }

    /**
     * Limpa os campos do formulário.
     */
    private void clearForm() {
        cpfField.setText("");
        nomeField.setText("");
        dataNascimentoField.setText("");
        telefoneField.setText("");
        emailField.setText("");
        senhaField.setText("");
        salarioField.setText("");
        comissaoField.setText("");
    }
}