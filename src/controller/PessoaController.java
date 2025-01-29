package controller;

import model.Cliente;
import model.Helper;
import model.Pessoa;
import model.Vendedor;

import java.sql.ResultSet;
import java.util.List;

import static model.Global.database;

public class PessoaController {

    public PessoaController(){

    }
    public Pessoa buscaPessoa(String login){
        try {

            List listUsuario = database.GetPessoaByLogin(login);
            if (listUsuario.size() > 0) {
                return (Cliente) listUsuario.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Pessoa pessoa = new Cliente(); // futuramente, deve-se complementar este método para que ele discrimine a pessoa buscada no banco de dados entre cliente e vendedor, criando o objeto em questão com base nessa info.

        return pessoa;
    }



}
