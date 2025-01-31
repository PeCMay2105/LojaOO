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
public float setPreco(float preco)
{
    this.preco = preco;
    return preco;
}

public float getPreco()
{
    return preco;
}

public String setNome(String nome)
{
    this.nome = nome;
    return nome;
}
public int getId()
{
    return id;
}
public int setEstoque(int estoque)
{
    this.estoque = estoque;
    return estoque;
}
public int getEstoque(){return estoque;}
public String setDescricao(String descricao)
{
    this.descricao = descricao;
    return descricao;
}
public String getDescricao(){return descricao;}

public String setCategoria(String categoria)
{
    this.categoria = categoria;
    return categoria;
}
public String getNome() {
    return nome;
}
public String getCategoria() {return categoria;}
}
