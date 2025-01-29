package model;

public class Produto {
    private String nome;
    private float preco;
    private int id;
    private int estoque;
    private String descricao;
    private String categoria;


    
public Produto(int id,String nome,float preco,int estoque,String descricao,String categoria)
{
    this.nome = nome;
    this.preco=preco;
    this.id = id;
    this.estoque=estoque;
    this.descricao=descricao;
    this.categoria=categoria;


}
public Produto(String nome,float preco,int estoque,String descricao,String categoria)
{
    this.nome = nome;
    this.preco=preco;
    this.estoque=estoque;
    this.descricao=descricao;
    this.categoria=categoria;}

public float getPreco()
{
    return preco;
}

public int getId()
{
    return id;
}

public String getNome() {
    return nome;
}
}
