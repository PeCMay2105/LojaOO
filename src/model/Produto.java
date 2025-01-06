package model;

public class Produto {
    private String nome;
    private float preco;
    private String id;
    private String marca;
    
    
public Produto(String nome,float preco,String id,String marca)
{
    this.nome = nome;
    this.preco=preco;
    this.id = id;
    this.marca = marca;
 
}
public float getPreco()
{
    return preco;
}

public String getId()
{
    return id;
}

public String getNome() {
    return nome;
}
}
