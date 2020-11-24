/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorretirocajero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hugoa
 */
public class Interfaz extends JFrame{
    private JTextField valueRetirement = new JTextField();
    private JLabel info = new JLabel("");
    private JButton button = new JButton("Ejecutar");
            
    public Interfaz(){
        setTitle("Simulador Cajero");
        setVisible(true);
        setSize(800,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setupWidgets();
        setupEvents();
        valueRetirement.setHorizontalAlignment(JTextField.CENTER);
        valueRetirement.setForeground(Color.red);
        info.setHorizontalAlignment(JTextField.CENTER);
    }
    
    private void setupWidgets(){
        JLabel question = new JLabel("Digite la cantidad de dinero a retirar");
        JLabel name = new JLabel("Hugo Andrés Pantoja Benavides");
        question.setHorizontalAlignment(JTextField.CENTER);
        name.setHorizontalAlignment(JTextField.CENTER);
        question.setFont(new Font("Arial", 16, 16));
        name.setFont(new Font("Arial", 14, 14));
        name.setForeground(Color.blue);
        valueRetirement.setFont(new Font("Arial", 20, 20));
        setLayout(new GridLayout(8,3));
        for (int i = 0; i < 7; i++) {
            add(new JLabel(""));
        }
        add(question);
        add(new JLabel(""));
        add(new JLabel(""));
        add(valueRetirement);
        add(new JLabel(""));
        add(new JLabel(""));
        add(button);
        add(new JLabel(""));
        add(new JLabel(""));
        add(info);
        add(new JLabel(""));
        add(new JLabel(""));
        add(name);
    }
    
    private void setupEvents(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        valueRetirement.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
            }
            @Override
            public void keyReleased(KeyEvent ke) {
                boolean response = validateValue();
                if(response == true){
                    int value = Integer.parseInt(valueRetirement.getText());
                    if(value>=10000 && value<=500000 && value%5000==0){
                        valueRetirement.setForeground(Color.green);
                    }else{
                        valueRetirement.setForeground(Color.red);
                    }
                    info.setText("");
                }else{
                    info.setForeground(Color.red);
                    info.setText("Alerta: El valor debe ser númerico");
                    valueRetirement.setForeground(Color.red);
                }
            }
        });
        
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if(validateValue()==true){
                    int value = Integer.parseInt(valueRetirement.getText());
                    if(value>=10000 && value<=500000){
                        if(value%5000==0){
                            info.setText("");
                            int a50 = value/50000;
                            int a20 = (value-((value/50000)*50000))/20000;
                            int a15 = value-((value/50000)*50000);
                            int a10 = (a15-((a15/20000)*20000))/10000;
                            int a8 = (a15-((a15/20000)*20000));
                            int a5 = (a8-((a8/10000)*10000))/5000;
                            String message = "<html>$50.000: "+a50+"<br>"
                                    + "$20.000: "+a20+"<br>"
                                    + "$10.000: "+a10+"<br>"
                                    + "$5.000: "+a5+"<br>"
                                    + "</html>";
                            JOptionPane.showMessageDialog(null, message);
                            valueRetirement.setForeground(Color.green);
                        }else{
                            info.setText("El valor debe ser múltiplo de $5.000");
                        }
                    }else{
                        info.setForeground(Color.BLUE);
                        info.setText("<html>El valor debe ser mayor a $10.000 <br> y menor"
                                + " a $500.000</html>");
                        valueRetirement.setForeground(Color.red);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent me) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    private boolean validateValue(){
        boolean result = false;
        try {
            Integer.parseInt(valueRetirement.getText());
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
