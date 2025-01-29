package model;

import controller.DatabaseController;

import java.sql.SQLException;

public class Global {
    public static Pessoa pessoa;
    public static Pessoa getPessoa() {return pessoa;}
    public static void setPessoa(Pessoa _pessoa)
    {
        System.out.println("Pessoa Global setada\nNome:"+ _pessoa.getNome());
        pessoa = _pessoa;
    }
    public static DatabaseController database;

    static {
        try {
            database = new DatabaseController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Cliente getCliente() {return (Cliente) pessoa;}
    public static DatabaseController getDatabase() {return database;}


}
