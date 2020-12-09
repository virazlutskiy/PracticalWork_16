package ru.mirea;

import javax.swing.*;
import java.awt.event.*;

import static ru.mirea.RestaurantMenu.BEER;


public class dialogAddDish extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox dishComboBox;
    private JTextArea descriptionTextBox;
    private JTextArea costTextBox;

    private MenuItem newMenuItem = BEER;
    MenuItem showDialog(){
        pack();
        setVisible(true);
        return newMenuItem;
    }
    void PreparedishComboBox(){
        dishComboBox.setModel(new DefaultComboBoxModel(RestaurantMenu.allDishes));
    }


    public dialogAddDish() {
        PreparedishComboBox();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        dishComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem tmp = RestaurantMenu.getItemByName((String) dishComboBox.getSelectedItem());
                descriptionTextBox.setText(tmp.getDescription());
                costTextBox.setText(Double.toString(tmp.getCost()));
                newMenuItem = tmp;
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        newMenuItem = null;
        dispose();
    }

    public static void main(String[] args) {
        //dialogAddDish dialog = new dialogAddDish();
        //dialog.pack();
        //dialog.setVisible(true);
        System.exit(0);
    }
}
