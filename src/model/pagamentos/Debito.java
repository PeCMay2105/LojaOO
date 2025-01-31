package model.pagamentos;

import model.Cliente;
import model.FormaPagamento;

public class Debito implements FormaPagamento {

    private Cliente cliente;
    public Debito(Cliente cliente) {
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
