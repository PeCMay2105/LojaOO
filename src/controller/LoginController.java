package controller;

import model.Pessoa;

public class LoginController {
    String senha;
    String login;
    public LoginController(String login, String senha)
    {
        this.login = login;
        this.senha = senha;
    }
    public boolean autenticar(String login,String senha){
        // requisicao ao banco de dados com os parametros login e senha
        if (requisitaDB(login, senha))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean requisitaDB(String login, String senha){
        // requisicao ao banco de dados com os parametros login e senha
        // Metodo provisorio
        boolean requisicao = true;

      if(requisicao == true)
      {
          return true;
      }
      else
      {
          return false;
      }

    }

}
