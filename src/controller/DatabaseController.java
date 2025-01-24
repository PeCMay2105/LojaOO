package controller;
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


    public void inserirEPrintar() throws SQLException {


        String sql = "INSERT INTO Pessoa (Nome, Email, Telefone, Endereco) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "Inácio Leal");
        stmt.setString(2, "Inacio@gmail.com");
        stmt.setString(3, "12345");
        stmt.setString(4, "Rj");
        stmt.executeUpdate();
        stmt.close();
        String sqlSelect = "SELECT * FROM Pessoa";
        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);


        ResultSet rs = stmtSelect.executeQuery();
        while (rs.next()) {
            String nome = rs.getString("Nome");
            String email = rs.getString("Email");
            String telefone = rs.getString("Telefone");
            String endereco = rs.getString("Endereco");
            System.out.println(nome + " " + email + " " + telefone + " " + endereco);

        }
        rs.close();
        stmtSelect.close();
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
}

