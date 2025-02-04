package view;

import controller.ProdutoController;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarProdutoView extends TemplateView {
    private JTextField nomeField;
    private JTextField estoqueField;
    private JTextField precoField;
    private JTextArea descricaoArea;
    private JTextField imagemField;
    private Produto produto;

    public EditarProdutoView(String titulo, Produto produto) {
        super(titulo);
        this.produto = produto;
        setTitle("Editar Produto");
        setSize(400, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nomeLabel, gbc);

        nomeField = new JTextField(produto.getNome());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(nomeField, gbc);

        JLabel estoqueLabel = new JLabel("Estoque:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(estoqueLabel, gbc);

        estoqueField = new JTextField(String.valueOf(produto.getEstoque()));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(estoqueField, gbc);

        JLabel precoLabel = new JLabel("Preço:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(precoLabel, gbc);

        precoField = new JTextField(String.valueOf(produto.getPreco()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(precoField, gbc);

        JLabel descricaoLabel = new JLabel("Descrição:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(descricaoLabel, gbc);

        descricaoArea = new JTextArea(produto.getDescricao());
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(new JScrollPane(descricaoArea), gbc);

        JLabel imagemLabel = new JLabel("Imagem:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(imagemLabel, gbc);

//        imagemField = new JTextField(produto.getImagem());
//        gbc.gridx = 1;
//        gbc.gridy = 4;
//        gbc.gridwidth = 2;
//        add(imagemField, gbc);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarProduto();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(salvarButton, gbc);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 5;
        add(cancelarButton, gbc);
    }

    private void salvarProduto() {
        produto.setNome(nomeField.getText());
        produto.setEstoque(Integer.parseInt(estoqueField.getText()));
        produto.setPreco(Float.parseFloat(precoField.getText()));
        produto.setDescricao(descricaoArea.getText());
        //produto.setImagem(imagemField.getText());
        try {
            ProdutoController produtoController = new ProdutoController();
            produtoController.atualizarProduto(produto);
            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dispose();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}