package model;

import java.util.ArrayList;
import java.util.Date;

public class Vendedor extends Pessoa {

    private String login;
    private String senha;
    ArrayList<Venda> vendas;
    Float salario;
    double comissao;

    public Vendedor(String nome, String CPF, String login, String senha, int id, int idade, Float salario, double comissao) {
        super(nome, CPF, id, idade);
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
        float valorTotal = produto.getPreco() * quantidade;
        Date now = new Date();
        Venda venda = new Venda(produto, this, quantidade, cliente, valorTotal, now);

        vendas.add(venda);

        return venda;
    }

}
