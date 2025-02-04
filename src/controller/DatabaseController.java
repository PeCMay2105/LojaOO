package controller;
import model.*;

import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;


/**
 * Essa classe é responsável por intermediar a interface gráfica com o modelo de Banco de Dados.
 * Gerencia todas as interações com o Banco de dados e o Sistema
 * O principal intermediario dessa classe é a classe Global
 * @see Global
 *
 */
public class DatabaseController {
    Connection conn;

    /**
     * Este método realiza a ação de criar um novo cliente.
     * @throws SQLException
     */
    public DatabaseController() throws SQLException {
        String relativePath = "Database/BDLoja.db";
        String dbPath = new File(relativePath).getAbsolutePath();
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);

    }



    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     * @param id Descrição do segundo parâmetro
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
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

    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
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
    

    /**
     * Este método realiza uma ação importante.
     *
     * @param opcao Enum que possui as tabelas do banco de dados
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
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

    /**
     * Método responsável por autenticar um usuário no sistema
     *
     *
     * @param login login do usuario
     * @param senha senha do usuario
     *
     * @return Retora um resultSet com os dados da tabela escolhida
     */
    public ResultSet autenticar(String login, String senha) throws SQLException {
        // Primeiro tenta autenticar como cliente
        String sqlSelectCliente = "SELECT p.* FROM Pessoa p " +
                "JOIN Cliente c ON p.CPF = c.CPF " +
                "WHERE p.Email = ? AND p.Senha = ?";


        PreparedStatement stmtCliente = conn.prepareStatement(sqlSelectCliente);
        stmtCliente.setString(1, login);
        stmtCliente.setString(2, senha);
        ResultSet rsCliente = stmtCliente.executeQuery();

        if (rsCliente.isBeforeFirst()) {
            return rsCliente; // Retorna os dados do cliente se encontrado
        }

        // Se não encontrou como cliente, tenta como vendedor
        String sqlSelectVendedor = "SELECT * FROM Pessoa p " +
                "JOIN vendedor v On p.Cpf = v.Cpf" +
                " WHERE p.Email = ? AND p.Senha = ?";
        PreparedStatement stmtVendedor = conn.prepareStatement(sqlSelectVendedor);
        stmtVendedor.setString(1, login);
        stmtVendedor.setString(2, senha);
        ResultSet rsVendedor = stmtVendedor.executeQuery();

        if (rsVendedor.isBeforeFirst()) {
            return rsVendedor; // Retorna os dados do vendedor se encontrado
        }

        String sqlSelectAdmin = "SELECT * FROM Pessoa p " +
                "JOIN Administrador a On p.Cpf = a.Cpf" +
                " WHERE p.Email = ? AND p.Senha = ?";
        PreparedStatement stmtAdmin = conn.prepareStatement(sqlSelectAdmin);
        stmtAdmin.setString(1, login);
        stmtAdmin.setString(2, senha);
        ResultSet rsAdmin = stmtAdmin.executeQuery();
        if(rsAdmin.isBeforeFirst()){
            System.out.println(rsAdmin);
            return rsAdmin;
        }
        // Se não encontrou em nenhuma tabela
        return null;
    }

    /**
     * Este método realiza a ação de cadastrar um novo cliente.
     * @param cliente Cliente a ser cadastrado
     * @throws SQLException
     */
    public int cadastrar(Cliente cliente) throws SQLException {
        String insertCliente = "INSERT INTO Cliente (CPF,Endereco) VALUES (?,?)";
        String insertPessoa = "INSERT INTO Pessoa (CPF,Nome,Email,Senha,Data_Nascimento) VALUES (?,?,?,?,?)";
        String insertCarrinho = "INSERT INTO Carrinho (ID,Data_Criacao) VALUES (?,?)";
        try {
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            PreparedStatement stmtCliente = conn.prepareStatement(insertCliente);
            PreparedStatement stmtCarrinho = conn.prepareStatement(insertCarrinho);

            stmtPessoa.setString(1, cliente.getCPF());
            stmtPessoa.setString(2, cliente.getNome());
            stmtPessoa.setDate(5, cliente.getNascimento());
            stmtPessoa.setString(3, cliente.getLogin());
            stmtPessoa.setString(4, cliente.getSenha());

            stmtCliente.setString(1, cliente.getCPF());

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

    /**
     * Este método realiza a ação de cadastrar um novo vendedor.
     * @param vendedor Vendedor a ser cadastrado
     * @throws SQLException
     */
    public void cadastrarVendedor(Vendedor vendedor) throws SQLException {
        String insertCliente = "INSERT INTO Vendedor (CPF, Salario, Comissao, Avaliacao) VALUES (?,?,?,?)";
        String insertPessoa = "INSERT INTO Pessoa (CPF,Nome,Email,Senha,Data_Nascimento) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmtPessoa = conn.prepareStatement(insertPessoa);
            PreparedStatement stmtCliente = conn.prepareStatement(insertCliente);

            stmtPessoa.setString(1, vendedor.getCPF());
            stmtPessoa.setString(2, vendedor.getNome());
            stmtPessoa.setDate(5, vendedor.getNascimento());

            stmtPessoa.setString(3, vendedor.getLogin());
            stmtPessoa.setString(4, vendedor.getSenha());
            stmtCliente.setString(1, vendedor.getCPF());

            stmtCliente.executeUpdate();
            stmtCliente.close();
            stmtPessoa.executeUpdate();
            stmtPessoa.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Retorna as informações de uma instancia Pessoa no Banco de Dados
     * @param login Administrador a ser cadastrado
     * @throws SQLException
     */
    public List<Object> GetPessoaByLogin(String login) throws SQLException {
        String sqlCliente = "SELECT c.CPF, p.Nome, p.Data_Nascimento, p.Telefone, p.Email, p.Senha " +
                "FROM Cliente c INNER JOIN Pessoa p ON c.CPF = p.CPF WHERE p.Email = ?";

        String sqlFuncionario = "SELECT f.CPF, p.Email, p.Nome, p.Senha, f.Comissao, f.Salario, p.Data_Nascimento " +
                "FROM Vendedor f INNER JOIN Pessoa p ON f.CPF = p.CPF WHERE p.Email = ?";

        String sqlAdmin = "SELECT a.CPF, p.Email, p.Nome, p.Senha, a.Salario, p.Data_Nascimento " +
                "FROM Administrador a INNER JOIN Pessoa p ON a.CPF = p.CPF WHERE p.Email = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> list = new ArrayList<>();

        try {
            // Verifica se é Cliente
            stmt = conn.prepareStatement(sqlCliente);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                list.add(new Cliente(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getDate("Data_Nascimento")
                ));
                return list; // Retorna imediatamente se encontrar um cliente
            }
            rs.close();
            stmt.close();

            // Verifica se é Funcionário
            stmt = conn.prepareStatement(sqlFuncionario);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                list.add(new Vendedor(
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("Email"),
                        rs.getString("Senha"),
                        rs.getDate("Data_Nascimento"),
                        rs.getDouble("Salario"),
                        rs.getDouble("Comissao")
                ));
                return list; // Retorna imediatamente se encontrar um funcionário
            }
            rs.close();
            stmt.close();

            // Verifica se é Administrador
            stmt = conn.prepareStatement(sqlAdmin);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            if (rs.next()) {
                list.add(new Administrador(
                        rs.getFloat("Salario"),
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getDate("Data_Nascimento"),
                        rs.getString("Email"),
                        rs.getString("Senha")
                ));
                return list;
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }

        return list; // Retorna uma lista vazia caso o login não exista
    }


    /**
     * Este método realiza a ação de cadastrar um novo produto.
     * @param produto Produto a ser cadastrado
     * @param cliente Cliente que está cadastrando o produto
     * @param quantidade Quantidade de produtos a serem cadastrados
     * @throws SQLException
     */
    void inserirProdutoCarrinho(Produto produto,Cliente cliente,int quantidade) throws SQLException {

        String sqlInsert =
            "INSERT INTO" +
                    " item_carrinho (ID_Carrinho,ID_Produto,Quantidade)" +
                    " VALUES (?,?,?)" +
            " ON CONFLICT " +
                    "(ID_Produto,ID_Carrinho)" +
            " DO UPDATE SET" +
                    " Quantidade = excluded.Quantidade;";

        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        stmt.setString(1, cliente.getCPF());
        stmt.setInt(2, produto.getId());
        stmt.setInt(3, quantidade);

        stmt.executeUpdate();
        stmt.close();
        }

        HashMap<Produto,Integer> GetProdutosByCPF(String CPF) throws SQLException {
        String sqlSelect = "SELECT * FROM item_carrinho INNER JOIN Produto ON ID_Produto = ID WHERE ID_Carrinho = ?";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        stmt.setString(1, CPF);
        ResultSet rs = stmt.executeQuery();

        return Helper.converterProdutosCarrinho(rs);

    }

    /**
     * Este método realiza a ação de cadastrar um novo produto.
     * @param produto Produto a ser cadastrado
     * @param vendedor Vendedor que está cadastrando o produto
     * @throws SQLException
     */
    public void inserirProduto(Produto produto,Vendedor vendedor) throws SQLException {

        String sqlInsert =
                "INSERT INTO" +
                        " Produto (Nome,Preco,Estoque,Descricao,Imagem,ID_Vendedor,ID_Categoria)" +
                        " VALUES (?,?,?,?,?,?,?)" +
                        " ON CONFLICT " +
                        " DO UPDATE SET" +
                        " Estoque = Estoque + ?;";


        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setInt(3, produto.getEstoque());
        stmt.setString(4,produto.getDescricao());
        //stmt.setBlob();
        stmt.setString(6,vendedor.getCPF());
        stmt.setString(7, produto.getCategoria());
        stmt.setInt(8, produto.getEstoque());
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Pesquisa um produto no banco de dados
     * @param query Produto a ser cadastrado
     * @throws SQLException
     * @return Lista de produtos encontrados
     */
    public List<Produto> pesquisaProdutos(String query) throws SQLException {
        String sqlSelect = "SELECT * FROM Produto WHERE produto.nome LIKE ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, "%" + query + "%"); // Adiciona os curingas à query
            try (ResultSet rs = stmt.executeQuery()) {
                produtos = Helper.converterProdutos(rs); // Converte o ResultSet em uma lista de produtos
            }
        } catch (SQLException e) {
            // Trate a exceção conforme necessário
            throw new SQLException("Erro ao buscar produtos: " + e.getMessage(), e);
        }

        return produtos;
    }


    /**
     * retorna a quantidade de produtos no carrinho
     * @param cpf Produto a ser cadastrado
     *
     * @throws SQLException
     * @return quantidade de produtos no carrinho
     */
    public int getQuantidadeCarrinho(String cpf) throws SQLException {
        String sqlSelect = "SELECT * FROM item_Carrinho WHERE ID_Carrinho = '"+cpf+"'";
        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();
        int qtd = 0;
        while (rs.next()) {
            qtd++;
        }
        System.out.println(cpf);
        System.out.println(qtd);
        System.out.println(stmt.toString());
        return qtd;
    }
    public List<Vendedor> getVendedoresByQuery(String query) throws SQLException {
        /*if (query.equals("")) {

        }*/
        String sqlSelect = "SELECT * FROM Pessoa p " +
                "JOIN Vendedor v ON p.CPF = v.CPF ";

        PreparedStatement stmt = conn.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();
        return Helper.converterVendedores(rs);




    }
}





