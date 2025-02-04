package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe utilitária para conversão de ResultSet em listas de objetos.
 */
public class Helper {

    /**
     * Converte um ResultSet em uma lista de produtos.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Uma lista de produtos.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
    public static List<Produto> converterProdutos(ResultSet rs) throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        while (rs.next()) {
            Produto produto;
            try {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                float preco = rs.getFloat("Preco");
                int estoque = rs.getInt("Estoque");
                String descricao = rs.getString("Descricao");

                produto = new Produto(id, nome, preco, estoque, descricao, "");
                produtos.add(produto);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return produtos;
    }

    /**
     * Converte um ResultSet em um mapa de produtos e suas quantidades.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Um mapa de produtos e suas quantidades.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
    public static HashMap<Produto, Integer> converterProdutosCarrinho(ResultSet rs) throws SQLException {
        HashMap<Produto, Integer> produtos = new HashMap<>();
        while (rs.next()) {
            Produto produto;
            try {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                float preco = rs.getFloat("Preco");
                int estoque = rs.getInt("Estoque");
                String descricao = rs.getString("Descricao");
                int qtd = rs.getInt("Quantidade");

                produto = new Produto(id, nome, preco, estoque, descricao, "");
                produtos.put(produto, qtd);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return produtos;
    }

    /**
     * Converte um ResultSet em uma lista de clientes.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Uma lista de clientes.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */
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

                cliente = new Cliente(nome, cpf, senha, email, nascimento);
                clientes.add(cliente);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return clientes;
    }


    /**
     * Converte um ResultSet em uma lista de vendedores.
     *
     * @param rs O ResultSet a ser convertido.
     * @return Uma lista de vendedores.
     * @throws SQLException Se ocorrer um erro ao acessar o ResultSet.
     */


    public static List<Vendedor> converterVendedores(ResultSet rs) throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        while (rs.next()) {
            Vendedor vendedor;
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
                float salario = rs.getFloat("Salario");
                float comissao = rs.getFloat("Comissao");

                vendedor = new Vendedor(nome, cpf, senha, email, nascimento, Double.valueOf(salario), comissao);
                vendedores.add(vendedor);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return vendedores;
    }
}