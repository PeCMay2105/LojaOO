package controller;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static model.Global.database;
import static model.Helper.converterProdutos;

public class CarrinhoController {
    Carrinho carrinho;
    HashMap<String,String> sessionUsuario;
    public CarrinhoController(Pessoa pessoa){ ///userData deve ser um objeto recebido da classe que que chama o controller contento usuario e senha
        ///// Código provisório
        this.carrinho = buscaCarrinho((Cliente) pessoa);

    }


    public Map<Produto,Integer> contentUser(){
        // aqui deve ser implementada a lógica para fazer a requisição para o banco de dados
        // e retornar o conteúdo do carrinho do usuário
        return null;
        //É util criar um helper para parsear o retorno do bando de dados em um arrayList para criar o carrinho nobo

    }

    public Map<Produto,Integer> retornaProdutos(){
        Map<Produto,Integer> conteudo = carrinho.getConteudo();
        return conteudo;
    }

    public void adicionarProduto(Produto produto, int quantidade){
        try {
            database.inserirProdutoCarrinho(produto, Global.getCliente(), quantidade);
            carrinho.adicionarProduto(produto, quantidade);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerProduto(Produto produto, int quantidade){
        carrinho.removerProduto(produto, quantidade);
    }

    public Carrinho buscaCarrinho(Cliente pessoa){
        Tabela opcao = Tabela.item_carrinho;
        Carrinho carrinhoAtual = new Carrinho(pessoa);
        System.out.println("Login:" + pessoa.getLogin());
        System.out.println("Senha:" + pessoa.getSenha());
        try {
            String login = pessoa.getLogin();
            System.out.println("Login: " + login);
            if(login != null && !login.isEmpty()) {
                //ResultSet rs = database.consulta(opcao, pessoa.getCPF());
                //List<Produto> listaCarrinho = Helper.converterProdutos(rs);
                List<Produto> listaCarrinho = database.GetProdutosByCPF(pessoa.getCPF());

                System.out.println("Lista de produtos: " + listaCarrinho);
                if (listaCarrinho == null) {
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("Carrinho Vazio");
                        frame.setSize(300, 150);
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setLocationRelativeTo(null);
                        JLabel label = new JLabel("O carrinho está vazio.", SwingConstants.CENTER);
                        label.setFont(new Font("Arial", Font.BOLD, 16));
                        frame.add(label);
                        frame.setVisible(true);
                    });

                }
                for(Produto produto: listaCarrinho){
                    System.out.println("Produto: " + produto.getNome());
                    carrinhoAtual.adicionarProduto(produto,1);
                }
                return carrinhoAtual;
            }else{
                System.out.println("Login vazio");
                throw new IllegalArgumentException("Invalid CPF");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public Carrinho getCarrinho(){
        return this.carrinho;
    }
}
