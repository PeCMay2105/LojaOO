package controller;
import java.sql.*;
import java.io.File;


public class DatabaseController {

    public DatabaseController() {
    }


    public void inserirEPrintar() throws SQLException {
        String relativePath = "Database/BDLoja.db";
        String dbPath = new File(relativePath).getAbsolutePath();
        String url = "jdbc:sqlite:" + dbPath;
        //System.out.println(url);
        //url = "jdbc:sqlite:/home/inacio/Github/projetoTP1/Database/BDLoja.db";
        //System.out.println(url);

        Connection conn = DriverManager.getConnection(url);

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
        conn.close();
    }


    public Object consulta(Tabela opcao, int id) {


        switch (opcao) {
            case carrinho:
                break;
            default:
                break;


        }

        return null;
    }
}

