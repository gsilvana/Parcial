package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlador.Controlador;
import java.util.List;

public class Lista extends JFrame {
    private JTextArea textArea;
    private JComboBox<String> comboBox;
    private JTextField nameField;
    private Controlador controlador;

    public Lista() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titleLabel = new JLabel("Selección de Frutas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Opciones de la lista
        String[] options = {
            "Moras", "Manzanas", "Uvas", "Sandias", "Melones",
            "Frambuesas", "Mandarinas", "Cocos", "Naranjas", "Mangos", 
            "Tamarindo","Cerezas", "Pepino", "Lechuga", "Tomate",
            "Apio", "Acelga", "Pimentón", "Cebolla", "Espinaca"
        };

        // Lista desplegable
        comboBox = new JComboBox<>(options);

        // Campo de texto para ingresar el nombre
        nameField = new JTextField(20);

        controlador = new Controlador(this);

        //Botón para guardar la selección
        JButton saveButton = new JButton("Guardar Selección");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameField.getText();
                String fruta = (String) comboBox.getSelectedItem();
                controlador.guardarSelección(nombre, fruta);
            }
        });

        // Área de texto para mostrar las selecciones guardadas
        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);

        // Panel para agregar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Nombre:"));
        centerPanel.add(nameField);
        centerPanel.add(comboBox);
        panel.add(centerPanel, BorderLayout.CENTER);

        panel.add(saveButton, BorderLayout.EAST);
        panel.add(new JScrollPane(textArea), BorderLayout.SOUTH);

        // Agregar el panel a la ventana
        add(panel);

        pack();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void mostrarFrutas(List<String> frutas) {
        textArea.setText("");
        for (String fruta : frutas) {
            textArea.append(fruta + "\n");
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lista().setVisible(true);
            }
        });
    }
}