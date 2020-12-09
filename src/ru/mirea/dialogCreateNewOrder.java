package ru.mirea;

import javax.swing.*;
import java.awt.event.*;

public class dialogCreateNewOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton IternetOrderButton;
    private JButton TableOrderButton;
    private JPanel screen2;
    private JPanel screen1;

    private int tablenum = -1;
    public dialogCreateNewOrder(TableOrdersManager TOM,InternetOrdersManager IOM) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);
        final Order newOrder;

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
        pack();
        TableOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablenum = new dialogCreateTableOrder(TOM).showDialog();
                onOK();
            }
        });
        IternetOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tablenum = new dialogCreateInternetOrder(IOM).showDialog();
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        setVisible(false);
        tablenum = -1;
        dispose();
    }

    int showDialog() {
        setVisible(true);
        return tablenum;
    }

    public static void main(String[] args) {
        //dialogCreateNewOrder dialog = new dialogCreateNewOrder(TOM);
        //dialog.pack();
       //dialog.setVisible(true);
        System.exit(0);
    }
}
