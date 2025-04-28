import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraPrecoFinal extends JFrame {

    private JTextField txtValorVenda;
    private JComboBox<String> cmbFormaPagamento;
    private JTextField txtPrecoFinal;

    public CalculadoraPrecoFinal() {
        super("Cálculo do preço final");

        setLayout(new GridLayout(4, 2));

        // Componentes
        add(new JLabel("Entre com o valor da venda:"));
        txtValorVenda = new JTextField();
        add(txtValorVenda);

        add(new JLabel("Escolha a forma de pagamento:"));
        cmbFormaPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cheque", "Cartão"});
        add(cmbFormaPagamento);

        add(new JLabel("Preço final a pagar"));
        txtPrecoFinal = new JTextField();
        txtPrecoFinal.setEditable(false);
        add(txtPrecoFinal);

        JButton btnCalcular = new JButton("Calcular");
        JButton btnLimpar = new JButton("Limpar");

        add(btnCalcular);
        add(btnLimpar);

        // Ações dos botões
        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularPrecoFinal();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void calcularPrecoFinal() {
        try {
            double valorVenda = Double.parseDouble(txtValorVenda.getText());
            String formaPagamento = (String) cmbFormaPagamento.getSelectedItem();
            double precoFinal = 0.0;

            if ("Dinheiro".equals(formaPagamento)) {
                precoFinal = valorVenda * 0.95; // 5% de desconto
            } else if ("Cheque".equals(formaPagamento)) {
                precoFinal = valorVenda * 1.05; // 5% de acréscimo
            } else if ("Cartão".equals(formaPagamento)) {
                precoFinal = valorVenda * 1.10; // 10% de acréscimo
            }

            txtPrecoFinal.setText(String.format("%.2f", precoFinal));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor válido para a venda.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtValorVenda.setText("");
        cmbFormaPagamento.setSelectedIndex(0);
        txtPrecoFinal.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CalculadoraPrecoFinal();
            }
        });
    }
}
