package MenuInicio;

import PanelPrincipal.PanelPrincipal;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {

    private final JButton boton1,boton3;

    public MenuPrincipal() {
        this.setTitle("Proyecto de Título - Universidad del Bío-Bío");       
        this.setFocusable(true);
        this.setSize(340, 150);
        this.setLocationRelativeTo(null);
        setLayout(null);
        boton1 = new JButton("Entrenar Jugador");
        boton1.setBounds(10, 25, 150, 60);
        add(boton1);
        boton1.addActionListener(this);
        boton3 = new JButton("Jugador Entrenado");
        boton3.setBounds(170, 25, 150, 60);
        add(boton3);
        boton3.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Sprite_Utileria/ubb.jpg"));
        this.setIconImage(icon);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boton1) {
            new PanelPrincipal(true);
            this.setVisible(false);
        }
         if (e.getSource() == boton3) {
            new PanelPrincipal(false);
            this.setVisible(false);
        }

    }

    public static void main(String[] ar) {
        MenuPrincipal formulario1 = new MenuPrincipal();
        formulario1.setVisible(true);
    }
}
