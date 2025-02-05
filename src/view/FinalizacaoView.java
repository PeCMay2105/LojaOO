package view;

import controller.DatabaseController;
import controller.Tabela;
import controller.VendaController;
import controller.VendedorController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

public class FinalizacaoView extends TemplateView {
    private JComboBox<String> vendedorComboBox;
    private JLabel valorFinalLabel;
    private VendaController vendaController;

    private int digitosCartao;
    private List<Vendedor> vendedores;

    public FinalizacaoView(String titulo, Pessoa pessoa, int digitosCartao) {
        super(titulo);
        vendaController = new VendaController();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel vendedorLabel = new JLabel("Vendedor:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(vendedorLabel, gbc);

        vendedorComboBox = new JComboBox<>();
        try{
            try {
                List<Vendedor> vendedores = Global.database.getVendedores();
                this.vendedores = vendedores;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        for (Vendedor vendedor : vendedores) {
            vendedorComboBox.addItem(vendedor.getNome());
        }
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(vendedorComboBox, gbc);

        JLabel valorLabel = new JLabel("Valor Final:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(valorLabel, gbc);

        valorFinalLabel = new JLabel(String.format("R$ %.2f", vendaController.obterValorFinal(Global.pessoa.getCPF())));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(valorFinalLabel, gbc);

        JButton finalizarButton = new JButton("Finalizar Compra");
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarCompra();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(finalizarButton, gbc);
    }

    private void finalizarCompra() {
        try {
            Vendedor vendedor = Global.database.getVendedoresByQuery((String) vendedorComboBox.getSelectedItem()).getFirst();

            double valorFinal = vendaController.obterValorFinal(Global.pessoa.getCPF());

            // Lógica para gerar e baixar o recibo
            String recibo = String.format("Recibo\n\nVendedor: %s\nValor Final: R$ %.2f", vendedor.getNome(), valorFinal);
            JOptionPane.showMessageDialog(this, recibo, "Recibo", JOptionPane.INFORMATION_MESSAGE);
            Venda vendaAtual = vendaController.cadastrarVenda(vendedor, digitosCartao, new Date());
            // Código para baixar o recibo
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar Recibo");
                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    try (FileWriter writer = new FileWriter(fileToSave)) {
                        writer.write(recibo);
                    }
                    JOptionPane.showMessageDialog(this, "Recibo salvo com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    Global.database.limparCarrinhoByCpf(Global.pessoa.getCPF());
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar recibo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}