package ru.mirea;

public interface OrderOld {
    boolean add(MenuItem menuItem);
    boolean remove(String name);
    int removeAll(String name);
    int dishQuantity();
    int dishQuantity(String name);
    MenuItem[] getDishes();
    int costTotal();
    String[] dishesNames();
    MenuItem[] sortedDishesByCostDesc();
}
interface Order {
    boolean add(MenuItem item);
    String[] itemsNames();
    int itemsQuantity();
    int itemsQuantity(String itemName);
    int itemsQuantity(MenuItem item);
    MenuItem[] getItems();
    boolean remove (String itemName);
    boolean remove (MenuItem item);
    int removeAll (String itemName);
    int removeAll (MenuItem item);
    MenuItem[] sortedItemsByCostDesc();
    int costTotal();
    Customer getCustomer();
    void setCustomer(Customer customer);
}