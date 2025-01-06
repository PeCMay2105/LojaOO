package model;
import java.util.Date;

public class Venda {
    public Produto produto;
    public Vendedor vendedor;
    public int quantidade;
    public Cliente cliente;
    public float valor;
    public Date data;

    public Venda(Produto produto, Vendedor vendedor, int quantidade, Cliente cliente, float valor, Date data) {
        this.produto = produto;
        this.vendedor = vendedor;
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.valor = valor;
        this.data = data;
    }
}
