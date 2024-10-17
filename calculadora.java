import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {

    private JTextField num1Field, num2Field, resultadoField;
    private JLabel num1Label, num2Label, resultadoLabel;
    private JButton sumaBtn, restaBtn, multiplicacionBtn, divisionBtn, clearBtn;

    public Calculadora() {
        // Configuración del JFrame
        setTitle("Calculadora");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));
        setResizable(false); // Evitar el cambio de tamaño de la ventana

        // Crear componentes
        num1Label = new JLabel("Número 1:");
        num1Field = new JTextField();
        num2Label = new JLabel("Número 2:");
        num2Field = new JTextField();
        resultadoLabel = new JLabel("Resultado:");
        resultadoField = new JTextField();
        resultadoField.setEditable(false); // Campo de resultado no editable

        sumaBtn = new JButton("Suma");
        restaBtn = new JButton("Resta");
        multiplicacionBtn = new JButton("Multiplicación");
        divisionBtn = new JButton("División");
        clearBtn = new JButton("Limpiar");

        // Estilo de botones
        ButtonGroup buttonGroup = new ButtonGroup();
        for (JButton button : new JButton[]{sumaBtn, restaBtn, multiplicacionBtn, divisionBtn, clearBtn}) {
            button.setBackground(Color.LIGHT_GRAY);
            button.setFocusPainted(false);
            buttonGroup.add(button);
        }

        // Añadir componentes al JFrame
        add(num1Label);
        add(num1Field);
        add(num2Label);
        add(num2Field);
        add(sumaBtn);
        add(restaBtn);
        add(multiplicacionBtn);
        add(divisionBtn);
        add(resultadoLabel);
        add(resultadoField);
        add(clearBtn);

        // Acción de los botones
        sumaBtn.addActionListener(new OperacionListener());
        restaBtn.addActionListener(new OperacionListener());
        multiplicacionBtn.addActionListener(new OperacionListener());
        divisionBtn.addActionListener(new OperacionListener());
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1Field.setText("");
                num2Field.setText("");
                resultadoField.setText("");
            }
        });
    }

    // Clase interna para manejar los eventos de los botones
    private class OperacionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double resultado = 0;

                if (e.getSource() == sumaBtn) {
                    resultado = num1 + num2;
                } else if (e.getSource() == restaBtn) {
                    resultado = num1 - num2;
                } else if (e.getSource() == multiplicacionBtn) {
                    resultado = num1 * num2;
                } else if (e.getSource() == divisionBtn) {
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede dividir entre cero");
                        return;
                    }
                }

                resultadoField.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.");
            }
        }
    }

    // Método principal para ejecutar la calculadora
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        calculadora.setVisible(true);
    }
}
