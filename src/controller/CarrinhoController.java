package controller;

import model.Carrinho;
import model.Produto;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoController {
    Carrinho carrinho;
    HashMap<String,String> sessionUsuario;
    public CarrinhoController(HashMap<String,String> userData){ ///userData deve ser um objeto recebido da classe que que chama o controller contento usuario e senha
        ///// Código provisório
        this.sessionUsuario = userData;
        //this.carrinho = new Carrinho(); pra instanciar um carrinho agora é necessário um cliente

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
}
