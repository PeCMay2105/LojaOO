package Model;

public abstract class Pessoa {
    private String nome;
    private String CPF;
    private int id;
    private int idade;

    public Pessoa(String nome, String CPF, int id, int idade) {
        this.nome = nome;
        this.CPF = CPF;
        this.id = id;
        this.idade = idade;
    }

}
