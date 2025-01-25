package model;

import java.sql.Date;

public abstract class Pessoa {
    private String nome;
    private String CPF;
    private Date nascimento;
    private String imagePath;
    public Pessoa(String nome, String CPF, Date nascimento) {
        this.nome = nome;
        this.CPF = CPF;
        this.nascimento = nascimento;
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
    public Date getNascimento(){
        return nascimento;
    }
    public void setIdade(int idade){
        this.nascimento = nascimento;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
}

