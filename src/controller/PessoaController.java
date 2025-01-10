package controller;

import model.Cliente;
import model.Pessoa;
import model.Vendedor;

public class PessoaController {

    public PessoaController(){

    }
    public Pessoa buscaPessoa(String login){
        // busca os dados da pessoa com este login no banco de dados
        // metodo provisorio
        Pessoa pessoa = new Cliente(); // futuramente, deve-se complementar este método para que ele discrimine a pessoa buscada no banco de dados entre cliente e vendedor, criando o objeto em questão com base nessa info.

        return pessoa;
    }
}
