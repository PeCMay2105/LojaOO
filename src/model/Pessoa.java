package model;

public abstract class Pessoa {
    private String nome;
    private String CPF;
    private int id;
    private int idade;
    private String imagePath;
    public Pessoa(String nome, String CPF, int id, int idade) {
        this.nome = nome;
        this.CPF = CPF;
        this.id = id;
        this.idade = idade;
    }
    public Pessoa(){

    }
    public String getCPF(){return CPF;}

    public String setImagem(String imagePath){
        this.imagePath = imagePath;
        return imagePath;
    }
    public String getImagem(){
        return imagePath;
    }

    public void setCPF(String CPF){
        this.CPF = CPF;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getIdade(){
        return idade;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
}

