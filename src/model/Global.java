package model;

import controller.DatabaseController;

import java.sql.SQLException;

public class Global {
    public static Pessoa pessoa;
    public static Pessoa getPessoa() {return pessoa;}
    public static void setPessoa(Pessoa _pessoa)
    {
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

    public static DatabaseController getDatabase() {return database;}


}
