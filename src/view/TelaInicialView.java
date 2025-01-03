package view;
import javax.swing.*;
import java.awt.*;

public class TelaInicialView extends TemplateView{



    public TelaInicialView(String titulo){
        super(titulo);

        String tituloTela = "Teste de classe";
        JPanel telaInicial = new JPanel(new GridLayout(3,1,10,10));
        JLabel header = new JLabel("Bem vindo ao S.I.S.T.E.M.O- Solução Inovadora para Suportar Transações Eficientes no Mercado Online");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);

        JButton verProdutos = new JButton("Ver produtos");
        JButton verCarrinho = new JButton("Ver carrinho");

        telaInicial.add(header);
        telaInicial.add(verProdutos);
        telaInicial.add(verCarrinho);

        adicionarConteudo(telaInicial);

        adicionarAoRodape(new JButton("Ajuda"));
        adicionarAoRodape(new JButton("Sair"));

    }


}
