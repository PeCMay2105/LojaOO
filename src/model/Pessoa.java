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

}
