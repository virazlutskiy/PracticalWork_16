package ru.mirea;

/*
public class OrderManager {

    HashMap<Integer, TableOrder> tablesOrders = new HashMap<>();
    public void add(TableOrder tableOrder, int tableNumber) {
        try {
            if (tablesOrders.containsKey(tableNumber))
                throw new OrderAlreadyAddedException("Стол " + tableNumber + " уже занят другим заказом");
            tablesOrders.put(tableNumber, tableOrder);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void remove(int tableId) {
        try {
            if (!tablesOrders.containsKey(tableId))
                throw new IllegalTableNumber("Столик с номером " + tableId + " не существует");
            tablesOrders.remove(tableId);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    //todo
     ///*void add(Order order, int tableNumber);
    Order getOrder(int tableNumber);
    addDish(Item dish, int tableNumber);
    remove
    freeTableNumber();
    freeTableNumbers();
    getOrders();
    ordersCostSummary();
    dishQuantity(dishName)
    //


    HashMap<String, Order> internetOrders = new HashMap<>();// адресс/заказ
    void add(String address,Order order){
        try {
            if (internetOrders.containsKey(address))
                throw new OrderAlreadyAddedException(address + " уже занят другим заказом");
            internetOrders.put(address, order);
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    Order getOrder(String address){return internetOrders.get(address);}
    void remove(String address){
        internetOrders.remove(address);}
    void addDish(String address, MenuItem menuItem){
        internetOrders.get(address).add(menuItem);}
    Order[] getInternetOrders(){
        int amount = internetOrders.size();
        Order[] Orders = new Order[amount];
        int i = 0;
        for(String key: internetOrders.keySet())
        {
            Orders[i] = internetOrders.get(key);
            i++;
        }
        return Orders;
    }
    int internetOrdersCostSummary(){
        int cost = 0;
        for(String key: internetOrders.keySet())
        {
            cost += internetOrders.get(key).costTotal();
        }
        return cost;
    }
    int internetDishQuantity(String dishName){
        int quantity = 0;
        for(String key: internetOrders.keySet())
        {
            quantity+=internetOrders.get(key).dishQuantity(dishName);
        }
        return quantity;
    }

}
*/
interface OrdersManager{
    int itemsQuantity(String itemName);
    int itemsQuantity(MenuItem item);
    Order[] getOrders();
    int ordersCostSummary();
    int ordersQuanity();
}
class TableOrdersManager implements OrdersManager{
    //20 столиков в ресторане. Номер индекса - номер столика.
    Order[] orders = new TableOrder[20];

    void add(Order order, int tableNumber){
        orders[tableNumber] = order;
    }
    void addItem(MenuItem item,int tableNumber){}
    int freeTableNumber(){
        int counter = 0;
        for(int i =0;i<20;i++)
        {
            if(orders[i]==null)
                counter++;
        }
        return counter;
    }
    int[] freeTableNumbers(){
        int[] result = new int[freeTableNumber()];
        int j = 0;
        for(int i = 0;i<20;i++)
        {
            if(orders[i]==null)
            {
                result[j] = i;
                j++;
            }
        }
        return result;
    }
    void remove(int TableNumber){
        orders[TableNumber] = null;
    }
    int remove(OrderOld orderOld){return 0;}
    int removeAll(OrderOld orderOld){return 0;}

    @Override
    public int itemsQuantity(String itemName) {
        return 0;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return 0;
    }

    @Override
    public Order[] getOrders() {
        return orders;
    }

    @Override
    public int ordersCostSummary() {
        return 0;
    }

    @Override
    public int ordersQuanity() {
        return 0;
    }
}
class InternetOrdersManager implements OrdersManager{
    Order[] orders = new InternetOrder[20];
    int head = 0;
    int tail = 0;
    int size = 20;
    boolean add(InternetOrder internetOrder){
        if(tail>size) {
            System.arraycopy(orders, 0, orders, 0, size * 2);
            size*=2;
        }
        orders[tail] = internetOrder;
        tail++;
        return true;
    }
    boolean remove(int position){
        position--;
        Order[] tmp = new Order[size-1];
        int j = 0;
        for(int i =0;i<size;i++)
        {
            if(i!=position) {
                tmp[j] = orders[i];
                j++;
            }
        }
        orders = tmp;
        size--;
        tail--;
        return true;
    }

    @Override
    public int itemsQuantity(String itemName) {
        return 0;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        return 0;
    }

    @Override
    public Order[] getOrders() {
        return orders;
    }

    @Override
    public int ordersCostSummary() {
        return 0;
    }

    @Override
    public int ordersQuanity() {
        return size;
    }
}