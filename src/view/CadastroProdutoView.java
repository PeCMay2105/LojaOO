package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CadastroProdutoView extends TemplateView {

    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoId;
    private JTextField campoMarca;
    private JButton botaoSalvar;

    public CadastroProdutoView() {
        super("Cadastro de Produto");
        cadastroProduto();
    }

    private void cadastroProduto() {
        setLayoutDiferente(new GridLayout(5, 2, 10, 10));

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        JLabel labelPreco = new JLabel("Preço:");
        campoPreco = new JTextField();

        JLabel labelId = new JLabel("ID:");
        campoId = new JTextField();

        JLabel labelMarca = new JLabel("Marca:");
        campoMarca = new JTextField();

        botaoSalvar = new JButton("Salvar");
        adicionarAoRodape(botaoSalvar);

        adicionarConteudo(labelNome);
        adicionarConteudo(campoNome);
        adicionarConteudo(labelPreco);
        adicionarConteudo(campoPreco);
        adicionarConteudo(labelId);
        adicionarConteudo(campoId);
        adicionarConteudo(labelMarca);
        adicionarConteudo(campoMarca);

        botaoSalvar.addActionListener(this::salvarProduto);
    }

    private void salvarProduto(ActionEvent e) {
        String nome = campoNome.getText().trim();
        String precoTexto = campoPreco.getText().trim();
        String idTexto = campoId.getText().trim();
        String marca = campoMarca.getText().trim();

        if (nome.isEmpty() || precoTexto.isEmpty() || idTexto.isEmpty() || marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float preco = Float.parseFloat(precoTexto);
            int id = Integer.parseInt(idTexto);

            System.out.println("Produto cadastrado:");
            System.out.println("Nome: " + nome);
            System.out.println("Preço: " + preco);
            System.out.println("ID: " + id);
            System.out.println("Marca: " + marca);

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            campoNome.setText("");
            campoPreco.setText("");
            campoId.setText("");
            campoMarca.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e ID devem ser numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroProdutoView tela = new CadastroProdutoView();
            tela.setVisible(true);
        });
    }
}
