package ru.mirea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

import static ru.mirea.RestaurantMenu.BEER;

public class dialogCreateInternetOrder extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private JButton addDishButton;
    private JButton removeDishButton;
    private JTextField firstname;
    private JTextField building;
    private JTextField zipcode;
    private JTextField street;
    private JTextField corpus;
    private JTextField letter;
    private JTextField appartment;
    private JTextField secondname;
    private JTextField age;
    private JTextField city;
    InternetOrdersManager IOM;
    private DefaultTableModel tableModel;
    InternetOrder internetOrder = new InternetOrder();
    int updated = -1;

    int showDialog() {
        pack();
        setVisible(true);
        return updated;
    }

    void PrepareTable() {
        tableModel = new DefaultTableModel(0, 3) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);
    }

    public dialogCreateInternetOrder(InternetOrdersManager IOM) {
        this.IOM = IOM;
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
                    internetOrder.add(newDish);
                    tableModel.addRow(new String[]{newDish.getName(), newDish.getDescription(), Double.toString(newDish.getCost())});
                }
            }
        });
        removeDishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if(internetOrder.remove((String) table1.getValueAt(selectedRow,0)))
                    tableModel.removeRow(selectedRow);
            }
        });
    }

    private void onOK() {
        Address address;
        Customer customer;
        if(checkCorrectInput())
        {
            boolean flag = true;
            try{
                address = new Address(city.getText(),
                        Integer.parseInt(zipcode.getText()),
                        street.getText(),
                        Integer.parseInt(building.getText()),
                        letter.getText().charAt(0),
                        Integer.parseInt(appartment.getText()));
                customer = new Customer(firstname.getText(),
                        secondname.getText(),
                        Integer.parseInt(age.getText()),
                        address);
                internetOrder.setCustomer(customer);
            }
            catch (Exception e)
            {
                System.out.println("Поймана ошибка!!!");
                System.out.println(e);
                flag = false;
            }
            if(flag && internetOrder.length!=1) {
                IOM.add(internetOrder);
                updated = -2;
                dispose();
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        //dialogCreateInternetOrder dialog = new dialogCreateInternetOrder();
        //dialog.pack();
        //dialog.setVisible(true);
        System.exit(0);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            System.out.println(strNum + " не число!");
            return false;
        }
        return true;
    }
    boolean checkCorrectInput() {
        if(     isNumeric(zipcode.getText()) &&
                !street.getText().isBlank() &&
                !city.getText().isBlank() &&
                isNumeric(building.getText())&&
                isNumeric(corpus.getText()) &&
                !letter.getText().isBlank() &&
                isNumeric(appartment.getText()) &&
                !firstname.getText().isBlank() &&
                !secondname.getText().isBlank() &&
                isNumeric(age.getText())
        )
        {
         return true;
        }
        return false;
    }
}
