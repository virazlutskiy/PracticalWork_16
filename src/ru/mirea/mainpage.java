package ru.mirea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class mainpage extends JFrame{
    private JPanel panel1;
    private JButton addNewOrder;
    private JButton deleteOrderButton;
    private JTable tableInsadeOrders;
    private JTable tableOutsideOrders;

    DefaultTableModel tableModel;
    DefaultTableModel tableModel2;
    int selectedTable = -1;
    void PrepareTable(){
        tableModel = new DefaultTableModel(0,3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel2 = new DefaultTableModel(0,3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addRow(new String[]{"Номер столика","Заказ","Стоимость"});
        tableModel2.addRow(new String[]{"Адресс","Заказ","Стоимость"});

        tableInsadeOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableOutsideOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableInsadeOrders.setModel(tableModel);
        tableOutsideOrders.setModel(tableModel2);
    }

    public mainpage(){
        PrepareTable();
        TableOrdersManager TOM = new TableOrdersManager();
        InternetOrdersManager IOM = new InternetOrdersManager();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setContentPane(panel1);
        setVisible(true);
        addNewOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var tmp = new dialogCreateNewOrder(TOM,IOM).showDialog();
                if(tmp>=0) {
                    var namesArray = TOM.orders[tmp].itemsNames();
                    String strOrder = "";
                    for (String name : namesArray) {
                        strOrder += TOM.orders[tmp].itemsQuantity(name) + " x " + name + ", ";
                    }
                    tableModel.addRow(new String[]{Integer.toString(tmp), strOrder, Integer.toString(TOM.orders[tmp].costTotal())});
                    //tableModel.addRow(columnNames);
                }
                //if inetrnetOrder
                if(tmp == -2)
                {
                    var namesArray = IOM.orders[IOM.tail-1].itemsNames();
                    String strOrder = "";
                    for (String name : namesArray) {
                        strOrder += IOM.orders[IOM.tail-1].itemsQuantity(name) + " x " + name + ", ";
                    }

                    tableModel2.addRow(new String[]{IOM.orders[IOM.tail-1].getCustomer().getAddress().getFullAddress(),
                            strOrder,
                            Integer.toString(IOM.orders[IOM.tail-1].costTotal())
                    });
                }
            }
        });
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sleectedRow;

                    if(selectedTable == 1)
                    {
                        sleectedRow = tableInsadeOrders.getSelectedRow();
                        if(sleectedRow>0) {
                            TOM.remove(Integer.parseInt((String) tableInsadeOrders.getValueAt(sleectedRow, 0)));
                            tableModel.removeRow(sleectedRow);
                        }
                    }else if(selectedTable == 2){
                        sleectedRow = tableOutsideOrders.getSelectedRow();
                        if(sleectedRow>0) {
                            IOM.remove(sleectedRow);
                            tableModel2.removeRow(sleectedRow);
                        }
                    }

                    }
        });
        tableInsadeOrders.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tableOutsideOrders.clearSelection();
                selectedTable = 1;
            }
        });
        tableOutsideOrders.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                tableInsadeOrders.clearSelection();
                selectedTable = 2;
            }
        });
    }

}
