package model;
import java.util.Map;
import java.util.HashMap;

public class Carrinho {
    private int quantidade = 0;
    private double valorTotal = 0.0;
    private Map<Produto,Integer> conteudo;
    private Cliente cliente;

    public Carrinho(Cliente dono){
        this.cliente = dono;
        this.conteudo = new HashMap<Produto,Integer>();

    }

    public int adicionarProduto(Produto produto,int quantidade){
        if(this.conteudo.containsKey(produto.getId())){
            this.conteudo.put(produto, Integer.valueOf(this.conteudo.get(produto) + quantidade));
            this.quantidade += quantidade;
            return 2;
        }
        else{
            this.conteudo.put(produto, Integer.valueOf(quantidade));
            this.quantidade += quantidade;
            return 1;
        }
    }
    public int removerProduto(Produto produto,int quantidade){
        if(this.conteudo.containsKey(produto)){
            if(this.conteudo.get(produto) > quantidade){
                this.conteudo.put(produto, Integer.valueOf(this.conteudo.get(produto) - quantidade));
                this.quantidade -= quantidade;
                return 1;
            }
            else if(this.conteudo.get(produto) == quantidade){
                this.conteudo.remove(produto);
                this.quantidade -= quantidade;
                return 2;
            }else{
                //Tratamento de erro na interface do usuario
                System.out.println("Quantidade de produtos a ser removida maior que a quantidade de produtos no carrinho");
                return 0;
            }
        }else{
            // Tratamento de erro
            System.out.println("O produto selecionado não está no carrinho");
            return 0;
        }
    }

//    public double calcularValorTotal(){
//        for(Produto produto : this.conteudo.keySet()){
//            this.valorTotal += produto.getPreco() * this.conteudo.get(produto);
//        }
//        return this.valorTotal;
//    }


    public Map<Produto, Integer> getConteudo() {
        return conteudo;
    }
}
