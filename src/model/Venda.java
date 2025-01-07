package model;
import java.util.Date;
import java.util.HashMap;

public class Venda {
    public Produto produto;
    public HashMap<String,Float> produtos;
    public Vendedor vendedor;
    public int quantidade;
    public Cliente cliente;
    public float valor;
    public Date data;
    public int valorTotal;

    public Venda(HashMap<String,Float> produtos, Vendedor vendedor, int quantidade, Cliente cliente, float valor, Date data) {
        this.produtos = produtos;
        this.vendedor = vendedor;
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }

    public void adicionaProdutos(String nome,Float preco){
        if(produtos.containsKey(nome)){
            System.out.println("Produto já está no carrinho.");
        }else{produtos.put(nome, preco);}
    }

    public void valorTotal(){
        valorTotal = 0;
        for(Float preco : produtos.values()){
            valorTotal += preco * quantidade;
        }
    }
}
