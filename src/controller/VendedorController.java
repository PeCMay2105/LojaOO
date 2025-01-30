package controller;

import model.Global;
import model.Produto;
import model.Vendedor;

import static model.Global.database;

public class VendedorController {
    public Vendedor vendedor;

    public VendedorController(){
        this.vendedor = (Vendedor) Global.getPessoa();
    }
    public int inserirProduto(Produto produto){
        try{
            database.inserirProduto(produto, vendedor);
            System.out.println("Produto inserido com sucesso");
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
