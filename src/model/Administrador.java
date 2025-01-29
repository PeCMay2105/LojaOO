package model;
import java.sql.Date;
import java.util.ArrayList;

public class Administrador extends Pessoa {

    private float salario;
    private ArrayList<Cliente> clientes;
    private ArrayList<Vendedor> vendedores;

    public Administrador(float salario, String nome, String CPF, Date nascimento) {
        super(nome, CPF, nascimento);
        this.salario = salario;
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
    }

    public float getSalario() {
        return salario;
    }

    public int cadastraCliente(String nome, String CPF, Date nascimento, String login, String senha, boolean possuiCartao) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(CPF)) {
                System.out.println("Cliente já cadastrado.");
                return 0;
            }
        }
        Cliente novoCliente = new Cliente(nome, CPF, nascimento, login, senha, possuiCartao, new ArrayList<>());
        clientes.add(novoCliente);
        System.out.println("Cliente cadastrado.");
        return 1;
    }

    public int cadastraVendedor(String nome, String CPF, String login, String senha, Date nascimento, Double salario, double comissao) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCPF().equals(CPF)) {
                System.out.println("Vendedor já cadastrado.");
                return 0;
            }
        }
        Vendedor novoVendedor = new Vendedor(nome, CPF, login, senha, nascimento, salario, comissao);
        vendedores.add(novoVendedor);
        System.out.println("Vendedor cadastrado.");
        return 1;
    }

    public int deletaCliente(String CPF) {
        for (Cliente cliente : clientes) {
            if (cliente.getCPF().equals(CPF)) {
                clientes.remove(cliente);
                System.out.println("Cliente removido.");
                return 1;
            }
        }
        System.out.println("Cliente não encontrado.");
        return 0;
    }

    public int deletaVendedor(String CPF) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getCPF().equals(CPF)) {
                vendedores.remove(vendedor);
                System.out.println("Vendedor removido.");
                return 1;
            }
        }
        System.out.println("Vendedor não encontrado.");
        return 0;
    }
}


