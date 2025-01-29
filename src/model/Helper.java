package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class Helper {

    public static List<Produto> converterProdutos(ResultSet rs) throws SQLException
    {
        List<Produto> produtos = new ArrayList<Produto>();
        while (rs.next())
        {
            Produto produto;
            try {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                float preco = rs.getFloat("Preco");
                int estoque = rs.getInt("Estoque");
                String descricao = rs.getString("Descricao");

                produto = new Produto(id,nome,preco,estoque,descricao,"");
                produtos.add(produto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return produtos;
    }
    public static List<Cliente> converterClientes(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente;
            try {

                String cpf = rs.getString("CPF");
                java.sql.Date nascimento;
                try {
                    nascimento = rs.getDate("Data_Nascimento");
                } catch (SQLException e) {
                    throw new RuntimeException("Error parsing date for CPF: " + rs.getString("CPF"), e);
                }
                String nome = rs.getString("Nome");
                String senha = rs.getString("Senha");
                String email = rs.getString("Email");

                cliente = new Cliente(nome,cpf, senha, email, nascimento);
                clientes.add(cliente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return clientes;
    }

}
