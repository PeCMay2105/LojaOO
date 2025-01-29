package model;

import java.time.Instant;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

public class Vendedor extends Pessoa {

    private String login;
    private String senha;
    ArrayList<Venda> vendas;
    double salario;
    double comissao;

    public Vendedor(String nome, String CPF, String login, String senha, Date nascimento, Double salario, double comissao) {
        super(nome, CPF, nascimento);
        this.login = login;
        this.senha = senha;
        this.salario = salario;
        vendas = new ArrayList<Venda>();
        this.comissao = comissao;


    }


    public String getLogin() {
        return login;
    }

    public Venda vende(Produto produto, Cliente cliente, int quantidade) {
        HashMap<String, Float> produtos = new HashMap<>();
        produtos.put(produto.getNome(), produto.getPreco());

        float valorTotal = produto.getPreco() * quantidade;
        Date now = new Date(new java.util.Date().getTime());
        Venda venda = new Venda(produtos, this, quantidade, cliente, valorTotal, now);

        vendas.add(venda);

        return venda;
    }

    public int getId(){
        return super.getId();
    }

}
