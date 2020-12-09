package ru.mirea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class dialogCreateTableOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox tableNumberComboBox;
    private JTable table1;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JButton убратьЗаказButton;

    private DefaultTableModel tableModel;
    private TableOrder tableOrder = new TableOrder();
    TableOrdersManager TOM;
    int showDialog(){
        pack();
        setVisible(true);
        return Integer.parseInt((String)tableNumberComboBox.getSelectedItem());
    }
    void PrepareTableNumberComboBox(TableOrdersManager TOM){
        this.TOM = TOM;
        String result[] = new String[TOM.freeTableNumber()];
        int[] numbers = TOM.freeTableNumbers();
        for(int i =0;i< TOM.freeTableNumber();i++)
        {
            result[i] = Integer.toString(numbers[i]);
        }
        tableNumberComboBox.setModel(new DefaultComboBoxModel(result));
    }
    void PrepareTable(){
        tableModel = new DefaultTableModel(0,3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);
    }
    public dialogCreateTableOrder(TableOrdersManager TOM) {


        PrepareTableNumberComboBox(TOM);
        PrepareTable();
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
        addDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var tmp = new dialogAddDish();
                MenuItem newDish = tmp.showDialog();
                if(newDish!= null) {
                    tableOrder.add(newDish);
                    tableModel.addRow(new String[]{newDish.getName(), newDish.getDescription(), Double.toString(newDish.getCost())});
                }

            }
        });
        removeDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(tableOrder.remove((String) table1.getValueAt(selectedRow,0)))
                    tableModel.removeRow(selectedRow);
            }
        });
    }

    private void onOK() {
        if(tableOrder.itemsQuantity()!=0)
            TOM.add(tableOrder,Integer.parseInt((String) tableNumberComboBox.getSelectedItem()));
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        /*
        dialogCreateTableOrder dialog = new dialogCreateTableOrder();
        dialog.pack();
        dialog.setVisible(true);
        */
        System.exit(0);
    }
}
