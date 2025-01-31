package model;

public interface FormaPagamento {

    boolean pagar(Float valor);
    void setCliente(Cliente cliente);
    String getCliente();

}
