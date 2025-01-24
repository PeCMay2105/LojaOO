package model;
import java.util.ArrayList;
public class Cliente extends Pessoa{

    private String login;
    private String senha;
    private boolean possuiCartao;
    private ArrayList<String> carteira;


    public Cliente(String nome, String CPF, int id, int idade, String login, String senha, boolean possuiCartao, ArrayList<String> carteira) {
        super(nome, CPF, id, idade);
        this.login = login;
        this.senha = senha;
        this.possuiCartao = possuiCartao;
        this.carteira = carteira;
    }
    public Cliente(String nome,String CPF,String login,String senha, int idade){
        super(nome,CPF,1,idade); // O atributo id vai ser removido.
        this.login = login;
        this.possuiCartao = false;
        this.senha = senha;
        this.carteira = new ArrayList<String>();
    }
    public Cliente(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public boolean getPossuiCartao() {
        return possuiCartao;
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
