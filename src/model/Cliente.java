package model;
import java.util.ArrayList;
import java.sql.Date;

public class Cliente extends Pessoa{

    private String login;
    private String senha;
    private boolean possuiCartao;
    private ArrayList<String> carteira;
    private String telefone;
    private Carrinho carrinho = new Carrinho(this);

    public Cliente(String nome, String CPF, Date nascimento, String login, String senha, boolean possuiCartao, ArrayList<String> carteira) {
        super(nome, CPF,nascimento);
        this.login = login;
        this.senha = senha;
        this.possuiCartao = possuiCartao;
        this.carteira = carteira;
    }
    public Cliente(String nome,String CPF,String login,String senha, Date nascimento){
        super(nome,CPF,nascimento);
        System.out.println("Nome: "+nome + "\nCPF: "+CPF);
        this.login = login;
        this.possuiCartao = false;
        this.senha = senha;
        this.carteira = new ArrayList<String>();
    }
    public Cliente(){

    }
    public Carrinho getCarrinho() {
        return carrinho;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    public void setLogin(String login) {
        this.login = login;
    }


    public boolean getPossuiCartao() {
        return possuiCartao;
    }
    public void setPossuiCartao(boolean possuiCartao) {
        this.possuiCartao = possuiCartao;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getTelefone() {
        return telefone;
    }


    public ArrayList<String> getCarteira() {
        return carteira;
    }

    public int adicionaPagamento(String MetodoPagamento){
        if(this.carteira.contains(MetodoPagamento)){
            //Tratamento de erro na interface do usuario
            System.out.println("Metodo de pagamento já cadastrado");
            return 0;
        }else{
            this.carteira.add(MetodoPagamento);
            return 1;
        }
    }
    public int adicionarCartao(){
            if(this.possuiCartao){
            //Tratamento de erro na interface do usuario
            System.out.println("Cliente já possui cartão cadastrado");
            return 0;
        }else{
            this.possuiCartao = true;
            return 1;
            }


       // código provisório
    }



}
