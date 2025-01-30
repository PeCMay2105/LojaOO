/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import controller.Tabela;
import model.Global;
import controller.DatabaseController;
import view.TelaInicialView;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Pedro Maia
 */
public class LojaOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        TelaInicialView telaInicialView = new TelaInicialView("S.I.S.T.E.M.O",false);
        telaInicialView.show();
        DatabaseController dbCtrol = Global.getDatabase();
        ResultSet rs = dbCtrol.consulta(Tabela.pessoa,1);
        while (rs.next()) {
            System.out.println(rs.getInt("ID"));
        }
        Produto produto = new Produto("Gato",30,30,"pinto","Acessorios");
        Global.database.inserirProduto(produto,new Vendedor("geilson","ae","41242","412442", new Date(2431),1d,2d));

}}
