/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import controller.Tabela;
import model.Global;
import controller.DatabaseController;
import view.TelaInicialView;

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
        dbCtrol.inserirEPrintar();
        ResultSet rs = dbCtrol.consulta(Tabela.pessoa,1);

        while (rs.next()) {
            System.out.println(rs.getInt("ID"));
        }

}}
