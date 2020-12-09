package ru.mirea;

import static ru.mirea.RestaurantMenu.BEER;
import static ru.mirea.RestaurantMenu.BURGER;

public class Tester {
    Tester(){
        TestAll();
    }
    void TestAll(){
        System.out.println("Запускаю тестирование модулей:");
        TesterTableOrdersManager();
    }
    void TesterTableOrdersManager()
    {
        boolean failed = false;
        String result = "✅";
        try{
            var TOM = new TableOrdersManager();
            Order ord1 = new TableOrder();
            ord1.add(BEER);
            ord1.add(BEER);
            ord1.add(BURGER);
            ord1.remove("Пиво");
            TOM.add(ord1,1);
        }catch(Exception e){
            failed = true;
        }
        finally {
            if(failed)
                result = "❌";
            System.out.printf("[%s] | TesterTableOrdersManager \n",result);
        }
    }

}
