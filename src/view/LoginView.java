package view;
import controller.LoginController;
import controller.PessoaController;
import model.Global;
import model.Pessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginView extends TemplateView{

    private String login;
    private String senha;
    public LoginView(String titulo) {
        super(titulo);
        JPanel telaCadastroCliente = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Login");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        telaCadastroCliente.add(header, gbc);

        JLabel nome = new JLabel("Email");
        JTextField campoNome = new JTextField(25);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(nome, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoNome, gbc);

        JLabel cpf = new JLabel("CPF:");
        JTextField campoCpf = new JTextField(25);
        gbc.gridy++;
        gbc.gridx = 0;
        telaCadastroCliente.add(cpf, gbc);
        gbc.gridx = 1;
        telaCadastroCliente.add(campoCpf, gbc);

        JButton entrar = new JButton("Entrar");

        JButton cancelar = new JButton("Cancelar");
        JButton ajuda = new JButton("Ajuda");
        adicionarConteudo(telaCadastroCliente);

        adicionarAoRodape(cancelar);
        adicionarAoRodape(ajuda);
        adicionarAoRodape(entrar);


        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TemplateView telaInicial = new TelaInicialView("Tela Inicial",false);
                telaInicial.setVisible(true);
                dispose();
            }
        });
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria e exibe a interface grafica da tela inicial logada
                try{
                    Pessoa usuario = iniciarSessao();
                    TemplateView telaInicial = new TelaInicialView("Tela Inicial", true, usuario);
                    // estou passando um novo parametro para tela inicial, o usuario. na classe tela inicial devemos implementar mudanças
                    //para que a sessão dele continue ativa
                    telaInicial.setVisible(true);
                    dispose();
                }catch(IllegalArgumentException error){
                    JOptionPane.showMessageDialog(null, "Login ou senha inválidos");
                }

            }
        });


    }

    public Pessoa iniciarSessao(){
        String login = this.login;
        String senha = this.senha;
        LoginController controladorLogin = new LoginController(login,senha);
        System.out.println("Login: "+login+" Senha: "+senha);
        if(controladorLogin.autenticar(login,senha)){
            PessoaController controladorPessoa = new PessoaController();
            Pessoa usuario = controladorPessoa.buscaPessoa(login);
            Global.setPessoa(usuario); // este trecho só funciona quando a consulta ao banco de dados está funcionando
            return usuario;

        }
        else{
            throw new IllegalArgumentException("Login ou senha inválidos");

        }
    }
}
