package view;

import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que representa a interface gráfica para adicionar um funcionário.
 */
public class AdicionarFuncionarioView extends TemplateView {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField dataNascimentoField;
    private JTextField telefoneField;

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

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                String nome = nomeField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String telefone = telefoneField.getText();

                // Aqui você pode adicionar a lógica para salvar os dados no banco de dados
                System.out.println("Vendedor adicionado: " + nome);
            }
        });
        panel.add(addButton);

        adicionarConteudo(panel);
    }
}