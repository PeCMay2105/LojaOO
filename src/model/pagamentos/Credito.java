package model.pagamentos;

import model.Cliente;
import model.FormaPagamento;

public class Credito implements FormaPagamento {

    private Cliente cliente;
    public Credito(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean pagar(Float valor) {

        return true;
    }

    @Override
    public void setCliente(Cliente cliente) {

    }

    @Override
    public String getCliente() {
        return "";
    }

}
