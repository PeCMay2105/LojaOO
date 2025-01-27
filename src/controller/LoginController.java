package controller;

import model.Pessoa;

import java.sql.ResultSet;

import static model.Global.database;

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
            // Agora o metodo requisita uma linha ao banco de dados e se houver uma resposta diferente de null retorna true
         try{
             ResultSet resultado =  database.autenticar(login, senha);
             if(resultado != null){
                 return true;
             }
             else{
                 return false;
             }
         }catch (Exception e){
             return false;
         }
    }
}
