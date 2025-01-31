package controller;

import model.*;

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

                if(listUsuario.get(0) instanceof Vendedor) {
                    return (Vendedor) listUsuario.get(0);
                }
                else if(listUsuario.get(0) instanceof Administrador){
                    return (Administrador) listUsuario.get(0);
                }
                return (Cliente) listUsuario.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Pessoa pessoa = new Cliente(); // futuramente, deve-se complementar este método para que ele discrimine a pessoa buscada no banco de dados entre cliente e vendedor, criando o objeto em questão com base nessa info.

        return pessoa;
    }



}
