package view;
import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
public class AdicionarProdutosView extends TemplateView {

    private Vendedor vendedor;
    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoEstoque;
    private JTextArea campoDescricao;
    private JComboBox<String> campoCategoria;
    private JButton botaoPublicar;
    private JLabel caracteresRestantes;
    private final int MAX_DESCRICAO = 280; // Similar ao limite do Twitter

    public AdicionarProdutosView(String titulo, Vendedor vendedor) {
        super(titulo);
        this.vendedor = vendedor;



        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);

        // Cabeçalho
        JLabel headerLabel = new JLabel("Novo Produto");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelPrincipal.add(headerLabel);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Nome
        JPanel painelNome = criarPainelCampo("Nome do produto");
        campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(350, 35));
        painelNome.add(campoNome);
        painelPrincipal.add(painelNome);

        // Campo Preço
        JPanel painelPreco = criarPainelCampo("Preço (R$)");
        campoPreco = new JTextField();
        campoPreco.setPreferredSize(new Dimension(150, 35));
        // Permite apenas números e um ponto decimal
        campoPreco.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }
        });
        painelPreco.add(campoPreco);
        painelPrincipal.add(painelPreco);

        // Campo Estoque
        JPanel painelEstoque = criarPainelCampo("Quantidade em estoque");
        campoEstoque = new JTextField();
        campoEstoque.setPreferredSize(new Dimension(100, 35));
        // Permite apenas números
        campoEstoque.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
        painelEstoque.add(campoEstoque);
        painelPrincipal.add(painelEstoque);

        // Campo Categoria
        JPanel painelCategoria = criarPainelCampo("Categoria");
        String[] categorias = {"Eletrônicos", "Roupas", "Acessórios", "Livros", "Casa", "Outros"};
        campoCategoria = new JComboBox<>(categorias);
        campoCategoria.setPreferredSize(new Dimension(200, 35));
        painelCategoria.add(campoCategoria);
        painelPrincipal.add(painelCategoria);

        // Campo Descrição
        JPanel painelDescricao = criarPainelCampo("Descrição do produto");
        campoDescricao = new JTextArea();
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setPreferredSize(new Dimension(350, 100));
        scrollDescricao.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Contador de caracteres
        caracteresRestantes = new JLabel(MAX_DESCRICAO + " caracteres restantes");
        caracteresRestantes.setForeground(Color.GRAY);
        campoDescricao.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { atualizarContador(); }
            public void insertUpdate(DocumentEvent e) { atualizarContador(); }
            public void removeUpdate(DocumentEvent e) { atualizarContador(); }
        });

        painelDescricao.add(scrollDescricao);
        painelDescricao.add(caracteresRestantes);
        painelPrincipal.add(painelDescricao);

        // Botão Publicar
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.setBackground(Color.WHITE);
        botaoPublicar = new JButton("Publicar");
        botaoPublicar.setPreferredSize(new Dimension(120, 40));
        botaoPublicar.setBackground(new Color(29, 161, 242)); // Azul do Twitter
        botaoPublicar.setForeground(Color.WHITE);
        botaoPublicar.setFocusPainted(false);
        botaoPublicar.setBorderPainted(false);
        botaoPublicar.addActionListener(e -> publicarProduto());
        painelBotoes.add(botaoPublicar);
        painelPrincipal.add(painelBotoes);

        adicionarConteudo(painelPrincipal);

        // Botão Voltar no rodapé
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            new TelaInicialView("Início",true, vendedor).setVisible(true);
            dispose();
        });
        adicionarAoRodape(botaoVoltar);
    }

    private JPanel criarPainelCampo(String labelText) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        painel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        painel.add(label);
        painel.add(Box.createRigidArea(new Dimension(0, 5)));

        return painel;
    }

    private void atualizarContador() {
        int restantes = MAX_DESCRICAO - campoDescricao.getText().length();
        caracteresRestantes.setText(restantes + " caracteres restantes");
        caracteresRestantes.setForeground(restantes < 0 ? Color.RED : Color.GRAY);
        botaoPublicar.setEnabled(restantes >= 0);
    }

    private void publicarProduto() {
        // Validação dos campos
        if (campoNome.getText().trim().isEmpty() ||
                campoPreco.getText().trim().isEmpty() ||
                campoEstoque.getText().trim().isEmpty() ||
                campoDescricao.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos são obrigatórios!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float preco = Float.parseFloat(campoPreco.getText());
            int estoque = Integer.parseInt(campoEstoque.getText());

            if (preco <= 0 || estoque < 0) {
                throw new NumberFormatException();
            }

            Produto novoProduto = new Produto(
                    campoNome.getText(),
                    preco,
                    estoque,
                    campoDescricao.getText(),
                    campoCategoria.getSelectedItem().toString()
            );

            // Aqui você deve adicionar o código para salvar o produto no banco de dados
            // ProdutoDAO.salvar(novoProduto);

            JOptionPane.showMessageDialog(this,
                    "Produto publicado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Volta para a tela inicial
            new TelaInicialView("Início",true, vendedor).setVisible(true);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Preço e estoque devem ser números válidos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}