package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Displays the Welcome window with a logo
public class GUI implements ActionListener {
    private JPanel panel;
    private JFrame frame;
    private JLabel label;
    private JButton signIn;

    // MODIFIES: this
    //EFFECTS: initializes the window with respective components
    public GUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("FatToFit");
        frame.setSize(620, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(new FlowLayout());

        label = new JLabel();
        ImageIcon logo = new ImageIcon("logo.png");
        label.setIcon(logo);
        panel.add(label);

        signIn = new JButton("Sign In");
        panel.add(signIn);
        signIn.setActionCommand("signIn");
        signIn.addActionListener(this);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("signIn")) {
            new LoginGUI();
            frame.setVisible(false);
        }
    }
}
