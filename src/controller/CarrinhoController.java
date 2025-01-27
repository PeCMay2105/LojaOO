package controller;

import model.*;

import java.sql.ResultSet;
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
        carrinho.adicionarProduto(produto, quantidade);
    }
    public void removerProduto(Produto produto, int quantidade){
        carrinho.removerProduto(produto, quantidade);
    }

    public Carrinho buscaCarrinho(Cliente pessoa){
        Tabela opcao = Tabela.carrinho;
        Carrinho carrinhoAtual = new Carrinho(pessoa);
        Helper parser = new Helper();
        try {
            String cpf = pessoa.getCPF();
            if(cpf != null && !cpf.isEmpty()) {
                ResultSet rs = database.consulta(opcao, parseInt(pessoa.getCPF()));
                List<Produto> listaCarrinho = parser.converterProdutos(rs);
                for(Produto produto: listaCarrinho){
                    carrinhoAtual.adicionarProduto(produto,1);
                }
                return carrinhoAtual;
            }else{
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
