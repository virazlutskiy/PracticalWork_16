package ru.mirea;

import java.util.HashMap;

public class TableOrderOld implements OrderOld
{
    HashMap<MenuItem, Integer> order = new HashMap<>();
    TableOrderOld(){}

    @Override
    public boolean add(MenuItem menuItem) {
        if(order.containsKey(menuItem))
        {
            int amount = 1 + order.get(menuItem);
            order.put(menuItem,amount);
        }
        else
        {
            order.put(menuItem,1);
        }
        return true;
    }

    @Override
    public boolean remove(String name) {

        for (MenuItem key : order.keySet()) {
            if(key.getName() == name)
            {

                if(order.get(key) <= 1)
                {
                    order.remove(key);
                    return true;
                }
                else if(order.get(key) > 1) {
                    int amount = order.get(key) - 1;
                    order.put(key,amount);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public int removeAll(String name) {
        int count = 0;
        for (MenuItem key : order.keySet()) {
            if(key.getName() == name)
            {
                count+=order.get(key);
                order.remove(key);
            }
        }
        return count;
    }

    @Override
    public int dishQuantity() {
        //
        return order.keySet().size();
    }

    @Override
    public MenuItem[] getDishes() {
        int amount = dishQuantity();
        MenuItem[] fullArray = new MenuItem[amount];
        int i = 0;
        for (MenuItem key : order.keySet()) {
            fullArray[i] = key;
            i++;
        }
        return fullArray;
    }

    @Override
    public int costTotal() {
        int totalPrice = 0;
        for (MenuItem key : order.keySet()) {
            totalPrice += key.getCost()*order.get(key);
        }
        return totalPrice;
    }

    @Override
    public int dishQuantity(String name) {
        int amount = 0;
        for (MenuItem key : order.keySet()) {
            amount += order.get(key);
        }
        return amount;
    }

    @Override
    public String[] dishesNames() {
        String[] stringArray = new String[dishQuantity()];
        int i = 0;
        for (MenuItem key : order.keySet()) {
            stringArray[i] = key.getName();
        }
        return stringArray;
    }

    @Override
    public MenuItem[] sortedDishesByCostDesc() {
        var tmp = getDishes();
        for( int i=0; i < tmp.length; i++) {            // i - номер прохода
            for (int j = tmp.length - 1; j > i; j--) {     // внутренний цикл прохода
                if (tmp[j - 1].getCost() < tmp[j].getCost()) {
                    var temp = tmp[j - 1];
                    tmp[j - 1] = tmp[j];
                    tmp[j] = temp;
                }
            }
        }
        return tmp;
    }

    /*public boolean add(Item item,int amount) {
        order.put(item, amount);
        return true;
    }
    public boolean remove(Item item) {
        order.remove(item);
        return true;
    }*/
}
class TableOrder implements Order {
    private HashMap<MenuItem, Integer> order = new HashMap<>();
    private int size;
    private Customer customer;

    @Override
    public boolean add(MenuItem item) {
        if(order.containsKey(item))
        {
            int amount = 1 + order.get(item);
            order.put(item,amount);
        }
        else
        {
            order.put(item,1);
        }
        return true;
    }

    @Override
    public String[] itemsNames() {
        String[] stringArray = new String[itemsQuantity()];
        int i = 0;
        for (MenuItem key : order.keySet()) {
            stringArray[i] = key.getName();
            i++;
        }
        return stringArray;
    }

    @Override
    public int itemsQuantity() {
        return order.keySet().size();
    }

    @Override
    public int itemsQuantity(String itemName) {
        int amount = 0;
        for (MenuItem key : order.keySet()) {
            if(key.getName().equals(itemName))
                amount += order.get(key);
        }
        return amount;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        if(order.containsKey(item))
            return order.get(item);
        return 0;
    }

    @Override
    public MenuItem[] getItems() {
        int amount = itemsQuantity();
        MenuItem[] fullArray = new MenuItem[amount];
        int i = 0;
        for (MenuItem key : order.keySet()) {
            fullArray[i] = key;
            i++;
        }
        return fullArray;
    }

    @Override
    public boolean remove(String itemName) {
        for (MenuItem key : order.keySet()) {
            if(key.getName() == itemName)
            {
                if(order.get(key) <= 1)
                {
                    order.remove(key);
                    return true;
                }
                else if(order.get(key) > 1) {
                    int amount = order.get(key) - 1;
                    order.put(key,amount);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean remove(MenuItem item) {
        for (MenuItem key : order.keySet()) {
            if(key == item)
            {
                if(order.get(key) <= 1)
                {
                    order.remove(key);
                    return true;
                }
                else if(order.get(key) > 1) {
                    int amount = order.get(key) - 1;
                    order.put(key,amount);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        int count = 0;
        for (MenuItem key : order.keySet()) {
            if(key.getName() == itemName)
            {
                count+=order.get(key);
                order.remove(key);
            }
        }
        return count;
    }

    @Override
    public int removeAll(MenuItem item) {
        int count = 0;
        for (MenuItem key : order.keySet()) {
            if(key == item)
            {
                count+=order.get(key);
                order.remove(key);
            }
        }
        return count;
    }

    @Override
    public MenuItem[] sortedItemsByCostDesc() {
        var tmp = getItems();
        for( int i=0; i < tmp.length; i++) {            // i - номер прохода
            for (int j = tmp.length - 1; j > i; j--) {     // внутренний цикл прохода
                if (tmp[j - 1].getCost() < tmp[j].getCost()) {
                    var temp = tmp[j - 1];
                    tmp[j - 1] = tmp[j];
                    tmp[j] = temp;
                }
            }
        }
        return tmp;
    }

    @Override
    public int costTotal() {
        int totalPrice = 0;
        for (MenuItem key : order.keySet()) {
            totalPrice += key.getCost()*order.get(key);
        }
        return totalPrice;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}