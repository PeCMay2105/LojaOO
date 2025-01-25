package controller;

import model.Cliente;
import model.Global;
import java.sql.Date;
public class ClienteController {

    public void criaCliente(String nome , String cpf, String email, String senha, Date nascimento){
        Cliente clienteDTO = new Cliente(nome,cpf,email,senha,nascimento);
        Global.setPessoa(clienteDTO);
        // Salvar no DB
    };

}
