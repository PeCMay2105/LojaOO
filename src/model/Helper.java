package model;

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

}
