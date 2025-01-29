package controller;
import model.Carrinho;
import model.Cliente;
import model.Helper;
import model.Produto;

import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DatabaseController {
    Connection conn;
    public DatabaseController() throws SQLException {
        String relativePath = "Database/BDLoja.db";
        String dbPath = new File(relativePath).getAbsolutePath();
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);

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
        String insertCliente = "INSERT INTO Cliente (CPF,Email,Senha,Endereco) VALUES (?,?,?,?)";
        String insertPessoa = "INSERT INTO Pessoa (CPF,Nome,Data_Nascimento) VALUES (?,?,?)";
        String insertCarrinho = "INSERT INTO Carrinho (ID,Data_Criacao) VALUES (?,?)";
        try {
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            PreparedStatement stmtCliente = conn.prepareStatement(insertCliente);
            PreparedStatement stmtCarrinho = conn.prepareStatement(insertCarrinho);

            stmtPessoa.setString(1, cliente.getCPF());
            stmtPessoa.setString(2, cliente.getNome());
            stmtPessoa.setDate(3, cliente.getNascimento());

            stmtCliente.setString(1, cliente.getCPF());
            stmtCliente.setString(2, cliente.getLogin());
            stmtCliente.setString(3, cliente.getSenha());

            stmtCarrinho.setString(1,cliente.getCPF());
            stmtCarrinho.setDate(2, new Date(1));

            stmtCarrinho.executeUpdate();
            stmtCarrinho.close();
            stmtCliente.executeUpdate();
            stmtCliente.close();
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List GetPessoaByLogin(String login) throws SQLException {
        String sqlSelect =
        "SELECT" +
            " Cliente.CPF," +
            " Pessoa.Nome," +
            " Pessoa.Data_Nascimento," +
            " Pessoa.Telefone," +
            " Cliente.Email," +
            " Cliente.Senha" +
        " FROM " +
                "Cliente" +
        " INNER JOIN" +
            " Pessoa" +
        " ON" +
            " Cliente.CPF = Pessoa.CPF" +
        " WHERE" +
            " Cliente.Email = '"+login+ "' ;";
        System.out.println(sqlSelect);
        PreparedStatement stmt = null;
        try {
            stmt =conn.prepareStatement(sqlSelect);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = stmt.executeQuery();
        List list = new ArrayList();
        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getString("Nome"),rs.getString("CPF"),rs.getString("Email"),rs.getString("Senha"),rs.getDate("Data_Nascimento"));
            list.add(cliente);
        }

            return list;
    }
    void inserirProdutoCarrinho(Produto produto,Cliente cliente,int quantidade) throws SQLException {
        String sqlInsert = "INSERT INTO item_carrinho (ID_Carrinho,ID_Produto,Quantidade) VALUES (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sqlInsert);

        stmt.setString(1, cliente.getCPF());
        stmt.setInt(2, produto.getId());
        stmt.setInt(3, quantidade);

        stmt.executeUpdate();
    }

    List<Produto> GetProdutosByCPF(String CPF) throws SQLException {
        String sqlSelect = "SELECT * FROM item_carrinho INNER JOIN Produto ON ID_Produto = ID WHERE ID_Carrinho = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();

        return Helper.converterProdutos(rs);

    }
}

