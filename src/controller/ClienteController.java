package controller;

import model.Cliente;
import model.Global;

public class ClienteController {

    public void criaCliente(String nome , String cpf, String email, String senha, int idade){
        Cliente clienteDTO = new Cliente(nome,cpf,email,senha,idade);
        Global.setPessoa(clienteDTO);
        // Salvar no DB
    };

}
