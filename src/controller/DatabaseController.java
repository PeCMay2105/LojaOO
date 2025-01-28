package controller;
import model.Carrinho;
import model.Cliente;

import java.sql.*;
import java.io.File;



public class DatabaseController {
    Connection conn;
    public DatabaseController() throws SQLException {
        String relativePath = "Database/BDLoja.db";
        String dbPath = new File(relativePath).getAbsolutePath();
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);

    }


    public void inserir(Tabela opcao, Object item) throws SQLException {

        String table = "";
        String idString = "ID";

        switch (opcao) {
            case carrinho:
                table =  "Carrinho";
                Carrinho carrinho = (Carrinho) item;
                idString = "";


                break;
            case categoria:
                table =  "Categoria";
                break;
            case cliente:
                table =  "Cliente";
                idString = "CPF";
                break;
            case item_carrinho:
                table =  "Item_Carrinho";
                break;
            case item_pedido:
                table =  "Item_Pedido";
                break;
            case pagamento:
                table =  "Pagamento";
                break;
            case pedido:
                table =  "Pedido";
                break;
            case pessoa:
                table =  "Pessoa";
                idString = "CPF";
                break;
            case produto:
                table =  "Produto";

                break;
            case vendedor:
                table =  "Vendedor";
                idString = "CPF";
                break;
            default:
                break;

        }


        String sqlInsert = "INSERT INTO "+table+" "+ idString;

        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        stmt.executeUpdate();
        stmt.close();
    }



    public ResultSet consulta(Tabela opcao, int id) throws SQLException {
        String table;
        String idString = "ID";

        switch (opcao) {
            case carrinho:
                table =  "Carrinho";

                break;
            case categoria:
                table =  "Categoria";
                break;
            case cliente:
                table =  "Cliente";
                idString = "CPF";
                break;
            case item_carrinho:
                table =  "Item_Carrinho";
                break;
            case item_pedido:
                table =  "Item_Pedido";
                break;
            case pagamento:
                table =  "Pagamento";
                break;
            case pedido:
                table =  "Pedido";
                break;
            case pessoa:
                table =  "Pessoa";
                idString = "CPF";
                break;
            case produto:
                table =  "Produto";

                break;
            case vendedor:
                table =  "Vendedor";
                idString = "CPF";
                break;
            default:
                return null;

        }

        String sqlSelect = "SELECT * FROM "+table+" WHERE "+ idString + " = " +id;
        System.out.println(sqlSelect);
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);


        return stmt.executeQuery();
    }
    public ResultSet consulta(Tabela opcao, String id) throws SQLException {
        String table;
        String idString = "ID";

        switch (opcao) {
            case carrinho:
                table = "Carrinho";
                break;
            case categoria:
                table = "Categoria";
                break;
            case cliente:
                table = "Cliente";
                idString = "Email";
                break;
            case item_carrinho:
                table = "Item_Carrinho";
                break;
            case item_pedido:
                table = "Item_Pedido";
                break;
            case pagamento:
                table = "Pagamento";
                break;
            case pedido:
                table = "Pedido";
                break;
            case pessoa:
                table = "Pessoa";
                idString = "CPF";
                break;
            case produto:
                table = "Produto";
                break;
            case vendedor:
                table = "Vendedor";
                idString = "CPF";
                break;
            default:
                return null;
        }

        String sqlSelect = "SELECT * FROM " + table + " WHERE " + idString + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, id);
        System.out.println(sqlSelect);

        return stmt.executeQuery();
    }
    

    public ResultSet consulta(Tabela opcao) throws SQLException {
        String table;

        switch (opcao) {
            case carrinho:
                table =  "Carrinho";

                break;
            case categoria:
                table =  "Categoria";
                break;
            case cliente:
                table =  "Cliente";
                break;
            case item_carrinho:
                table =  "Item_Carrinho";
                break;
            case item_pedido:
                table =  "Item_Pedido";
                break;
            case pagamento:
                table =  "Pagamento";
                break;
            case pedido:
                table =  "Pedido";
                break;
            case pessoa:
                table =  "Pessoa";
                break;
            case produto:
                table =  "Produto";

                break;
            case vendedor:
                table =  "Vendedor";
                break;
            default:
                return null;

        }

        String sqlSelect = "SELECT * FROM "+table;
        System.out.println(sqlSelect);
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);


        return stmt.executeQuery();
    }
    public ResultSet autenticar(String login, String senha) throws SQLException {
        String sqlSelect = "SELECT * FROM Cliente WHERE Email = ? AND Senha = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (!rs.isBeforeFirst()) {
            // No data found
            return null;
        }
        return rs;
    }
    public int cadastrar(Cliente cliente) throws SQLException {
        String sqlInsert = "INSERT INTO Cliente (CPF,Data_Nascimento,Nome,Senha,Email) VALUES (?,?,?,?,?)"; // método está com problema
        try {
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);
            stmt.setString(1, cliente.getCPF());
            stmt.setDate(2, cliente.getNascimento());
            stmt.setString(3, cliente.getNome());
            stmt.setString(5, cliente.getLogin());
            stmt.setString(4, cliente.getSenha());
            stmt.executeUpdate();
            stmt.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

