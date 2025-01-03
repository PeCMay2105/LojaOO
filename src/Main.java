import javax.swing.SwingUtilities;
import view.TelaInicialView;
import view.TemplateView;
import view.cadastroClienteView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            //Local para inicializar os componentes MVC
            inicializarMVC();
        });
    }

    private static void inicializarMVC(){
        TemplateView telaInicial = new TelaInicialView("Tela Inicial");
        telaInicial.setVisible(true);

        //TemplateView telaCadastroCliente = new cadastroClienteView("Cadastro de Cliente");
        //telaCadastroCliente.setVisible(true);
    }
}
