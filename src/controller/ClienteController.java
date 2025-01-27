package controller;

import model.Cliente;
import model.Global;
import java.sql.Date;
import java.sql.SQLException;
import controller.Tabela;

public class ClienteController {

    public void criaCliente(String nome , String cpf, String email, String senha, Date nascimento) throws SQLException {
        Cliente clienteDTO = new Cliente(nome,cpf,email,senha,nascimento);
        Global.setPessoa(clienteDTO);
        System.out.println("Global.pessoa: "+Global.getPessoa().getNome());
        try{
            Global.database.cadastrar(clienteDTO);
        }catch (SQLException e){
            System.out.println("Erro ao inserir cliente no banco de dados");
        }

    };

}
