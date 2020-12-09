package ru.mirea;

import java.util.HashMap;

public class InternetOrderOld implements OrderOld {

    int newKey = 1;
    int length = 0;
    LinkedListItem head;
    HashMap<Integer, LinkedListItem> internetOrders = new HashMap<>();

    InternetOrderOld(){
        head = new LinkedListItem(0,0, new Dish("",""));
        internetOrders.put(0, head);
        length++;
    }
    InternetOrderOld(MenuItem[] menuItems){
        head = new LinkedListItem(0,0, new Dish("HEADER","HEADER"));
        int ol = menuItems.length+1;
        for(int i = 1;i<ol;i++)
        {
            LinkedListItem LL = new LinkedListItem(i-1, (i+1)%ol, menuItems[i-1]);
            internetOrders.put(newKey, LL);
            length++;
            newKey++;
        }
        head.setPrevious(ol-1);
        head.setNext(1);
        internetOrders.put(0, head);
        length++;
    }
    public boolean add(MenuItem menuItem)
    {
        int lastKey = internetOrders.get(0).getPrevious();
        internetOrders.get(0).setPrevious(newKey);
        internetOrders.get(lastKey).setNext(newKey);
        internetOrders.put(newKey, new LinkedListItem(lastKey,0, menuItem));
        length++;
        newKey++;
        return true;
    }
    void deleteByIndex(int index)
    {
        int previusIndex = internetOrders.get(index).getPrevious();
        int nextIndex = internetOrders.get(index).getNext();
        internetOrders.get(previusIndex).setNext(nextIndex);
        internetOrders.get(nextIndex).setPrevious(previusIndex);
        internetOrders.remove(index);
        length --;
    }

    public boolean remove(String name)
    {

        int index = -1;

        int i = 0;
        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(name))
            {
                index = i;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);

        if(index != -1)
        {
            deleteByIndex(index);
            return true;
        }
        return false;
    }

    public int removeAll(String name)
    {
        int count = 0;
        int index = -1;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(name))
            {
                index = i;
                deleteByIndex(index);
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);


        return count;
    }

    public int dishQuantity(){return length;}
    //вернуть массив
    public MenuItem[] getDishes(){
        MenuItem[] myarray = new MenuItem[dishQuantity()-1];
        int key = internetOrders.get(0).getNext();
        for(int i = 0; i< dishQuantity()-1; i++)
        {
            myarray[i] = internetOrders.get(key).getItem();
            key = internetOrders.get(key).getNext();
        }
        return myarray;
    }

    public int costTotal(){
        int next = -1;
        int totalPrice = 0;
        int index = 0;
        while(next != 0)
        {
            next = internetOrders.get(index).getNext();
            totalPrice += internetOrders.get(index).getItem().getCost();
            index = next;
        }
        return totalPrice;
    }

    public int dishQuantity(String name)
    {
        int count = 0;
        int index = -1;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(name))
            {
                index = i;
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);


        return count;
    }
    public String[] dishesNames(){
        String[] myarray = new String[dishQuantity()];
        int key = internetOrders.get(0).getNext();
        int index = 0;
        while(key!=0)
        {
            String name = internetOrders.get(key).getItem().getName();
            boolean contains = false;
            for(int i = 0; i< dishQuantity(); i++)
            {
                if(myarray[i] == name)
                    contains = true;
            }
            if(!contains)
            {
                myarray[index] = internetOrders.get(key).getItem().getName();
                index++;
            }
            key = internetOrders.get(key).getNext();
        }
        String[] result = new String[index];
        if (index - 1 >= 0) System.arraycopy(myarray, 0, result, 0, index);
        return result;
    }
    public MenuItem[] sortedDishesByCostDesc(){
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
}
class LinkedListItem{
    protected int previous;
    protected int next;
    protected MenuItem menuItem;
    LinkedListItem(int previous, int next, MenuItem menuItem){
        setPrevious(previous);
        setNext(next);
        this.menuItem = menuItem;
    }

    int getPrevious() {return previous;}
    void setPrevious(int previous) {this.previous = previous;}

    int getNext() {return next;}
    void setNext(int next) {this.next = next;}

    MenuItem getItem() {return menuItem;}
    void setItem(MenuItem menuItem) {this.menuItem = menuItem;}
}
class InternetOrder implements Order {

    int newKey = 1;
    int length = 0;
    LinkedListItem head;
    HashMap<Integer, LinkedListItem> internetOrders = new HashMap<>();
    Customer customer;

    InternetOrder(){
        head = new LinkedListItem(0,0, new Dish("HEADER","HEADER"));
        internetOrders.put(0, head);
        length++;
    }
    InternetOrder(MenuItem[] menuItems){
        head = new LinkedListItem(0,0, new Dish("HEADER","HEADER"));
        int ol = menuItems.length+1;
        for(int i = 1;i<ol;i++)
        {
            LinkedListItem LL = new LinkedListItem(i-1, (i+1)%ol, menuItems[i-1]);
            internetOrders.put(newKey, LL);
            length++;
            newKey++;
        }
        head.setPrevious(ol-1);
        head.setNext(1);
        internetOrders.put(0, head);
        length++;
    }
    void deleteByIndex(int index)
    {
        int previusIndex = internetOrders.get(index).getPrevious();
        int nextIndex = internetOrders.get(index).getNext();
        internetOrders.get(previusIndex).setNext(nextIndex);
        internetOrders.get(nextIndex).setPrevious(previusIndex);
        internetOrders.remove(index);
        length --;
    }


    @Override
    public boolean add(MenuItem item) {
        int lastKey = internetOrders.get(0).getPrevious();
        internetOrders.get(0).setPrevious(newKey);
        internetOrders.get(lastKey).setNext(newKey);
        internetOrders.put(newKey, new LinkedListItem(lastKey,0, item));
        length++;
        newKey++;
        return true;
    }

    @Override
    public String[] itemsNames() {
        String[] myarray = new String[itemsQuantity()];
        int key = internetOrders.get(0).getNext();
        int index = 0;
        while(key!=0)
        {
            String name = internetOrders.get(key).getItem().getName();
            boolean contains = false;
            for(int i = 0; i< itemsQuantity(); i++)
            {
                if(myarray[i] == name)
                    contains = true;
            }
            if(!contains)
            {
                myarray[index] = internetOrders.get(key).getItem().getName();
                index++;
            }
            key = internetOrders.get(key).getNext();
        }
        String[] result = new String[index];
        if (index - 1 >= 0) System.arraycopy(myarray, 0, result, 0, index);
        return result;
    }

    @Override
    public int itemsQuantity() {
        return length;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int count = 0;
        int index = -1;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(itemName))
            {
                index = i;
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);


        return count;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int count = 0;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.equals(item))
            {
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);

        return count;
    }

    @Override
    public MenuItem[] getItems() {
        MenuItem[] myarray = new MenuItem[itemsQuantity()-1];
        int key = internetOrders.get(0).getNext();
        for(int i = 0; i< itemsQuantity()-1; i++)
        {
            myarray[i] = internetOrders.get(key).getItem();
            key = internetOrders.get(key).getNext();
        }
        return myarray;
    }

    @Override
    public boolean remove(String itemName) {
        int index = -1;

        int i = 0;
        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(itemName))
            {
                index = i;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);

        if(index != -1)
        {
            deleteByIndex(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(MenuItem item) {
        int index = -1;

        int i = 0;
        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.equals(item))
            {
                index = i;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);

        if(index != -1)
        {
            deleteByIndex(index);
            return true;
        }
        return false;
    }

    @Override
    public int removeAll(String itemName) {
        int count = 0;
        int index = -1;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.getName().equals(itemName))
            {
                index = i;
                deleteByIndex(index);
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);


        return count;
    }

    @Override
    public int removeAll(MenuItem item) {
        int count = 0;
        int index = -1;
        int i = 0;

        do {
            int next = internetOrders.get(i).getNext();
            if(internetOrders.get(i).menuItem.equals(item))
            {
                index = i;
                deleteByIndex(index);
                count++;
            }
            if(next == 0)
                break;
            i = next;
        }while(i != 0);


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
        int next = -1;
        int totalPrice = 0;
        int index = 0;
        while(next != 0)
        {
            next = internetOrders.get(index).getNext();
            totalPrice += internetOrders.get(index).getItem().getCost();
            index = next;
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



