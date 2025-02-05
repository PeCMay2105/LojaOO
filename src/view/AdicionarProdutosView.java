package view;
import controller.VendedorController;
import model.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Classe que representa a interface gráfica para adicionar produtos.
 */
public class AdicionarProdutosView extends TemplateView {

    private Vendedor vendedor;
    private JTextField campoNome;
    private JTextField campoPreco;
    private JTextField campoEstoque;
    private JTextArea campoDescricao;
    private JComboBox<String> campoCategoria;
    private JButton botaoPublicar;
    private JLabel caracteresRestantes;
    private VendedorController vendedorController;
    private FileInputStream fis;
    private JButton botaoImagem;
    private final int MAX_DESCRICAO = 280; // Similar ao limite do Twitter

    /**
     * Construtor da classe AdicionarProdutosView.
     *
     * @param titulo O título da janela.
     * @param vendedor O vendedor atual.
     */
    public AdicionarProdutosView(String titulo, Vendedor vendedor) {
        super(titulo);
        this.vendedor = vendedor;
        this.vendedorController = new VendedorController();

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

        // Botao imagem
        JPanel painelImagem = criarPainelCampo("Imagem do produto");
        botaoImagem = new JButton();
        botaoImagem.setPreferredSize(new Dimension(350, 35));
        painelImagem.add(botaoImagem);
        painelPrincipal.add(botaoImagem);
        botaoImagem.addActionListener(e -> {
            addImagem();
        });


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

    /**
     * Cria um painel para um campo de entrada.
     *
     * @param labelText O texto do rótulo do campo.
     * @return O painel criado.
     */
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
    @Override
    protected void aoVoltar(){
        TelaInicialView telaInicial = new TelaInicialView("Tela Inicial", true, vendedor);
        telaInicial.setVisible(true);
        dispose();
    }

    /**
     * Atualiza o contador de caracteres restantes na descrição.
     */
    private void atualizarContador() {
        int restantes = MAX_DESCRICAO - campoDescricao.getText().length();
        caracteresRestantes.setText(restantes + " caracteres restantes");
        caracteresRestantes.setForeground(restantes < 0 ? Color.RED : Color.GRAY);
        botaoPublicar.setEnabled(restantes >= 0);
    }

    /**
     * Publica o produto após validar os campos.
     */
    private void publicarProduto() {
        // Validação dos campos
        String nome = campoNome.getText().trim();
        String precoText = campoPreco.getText().trim();
        String estoqueText = campoEstoque.getText().trim();
        String descricao = campoDescricao.getText().trim();

        if (nome.isEmpty() || precoText.isEmpty() || estoqueText.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos os campos são obrigatórios!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação do preço
        float preco;
        try {
            preco = Float.parseFloat(precoText);
            if (preco <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Preço deve ser um número válido e maior que zero!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação do estoque
        int estoque;
        try {
            estoque = Integer.parseInt(estoqueText);
            if (estoque < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Quantidade em estoque deve ser um número válido e não negativo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificação da descrição
        if (descricao.length() > MAX_DESCRICAO) {
            JOptionPane.showMessageDialog(this,
                    "Descrição não pode exceder " + MAX_DESCRICAO + " caracteres!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Produto novoProduto = new Produto(
                nome,
                preco,
                estoque,
                descricao,
                fis,
                campoCategoria.getSelectedItem().toString()
        );

        vendedorController.inserirProduto(novoProduto);

        JOptionPane.showMessageDialog(this,
                "Produto publicado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        // Volta para a tela inicial
        new TelaInicialView("Início", true, vendedor).setVisible(true);
        dispose();
    }
    void addImagem()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem");
        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                fis = new FileInputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}