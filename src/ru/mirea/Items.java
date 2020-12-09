package ru.mirea;
enum DrinkTypeEnum{
    BEER,
    WINE,
    VODKA,
    BRANDY,
    CHAMPAGNE,
    WHISKEY,
    TEQUILA,
    RUM,
    VERMOUTH,
    LIQUOR,
    JAGERMEISTER,
    JUICE,
    COFFEE,
    GREEN_TEA,
    BLACK_TEA,
    MILK,
    WATER,
    SODA
}

interface MenuItem {
    double getCost();
    String getName();
    String getDescription();
}
interface Alcoholable
{
    boolean isAlcoholicDrink();
    double getAlcoholVol();
}

final class Drink implements MenuItem, Alcoholable{
    final double cost;
    final String name;
    final String description;
    final DrinkTypeEnum type;
    final double alcoholVol;
    Drink(String name, String description, double cost, DrinkTypeEnum type, double alcoholVol){
        if(name.isBlank())
            throw new IllegalArgumentException("Название напитка не может быть пустым");
        if(description.isBlank())
            throw new IllegalArgumentException("Описание напитка не может быть пустым");
        if(cost<0)
            throw new IllegalArgumentException("стоимость напитка не может быть ниже нуля");
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.alcoholVol = alcoholVol;
    }
    Drink(String name, String description, DrinkTypeEnum type, double alcoholVol){
        if(name.isBlank())
            throw new IllegalArgumentException("Название напитка не может быть пустым");
        if(description.isBlank())
            throw new IllegalArgumentException("Описание напитка не может быть пустым");
        this.name = name;
        this.description = description;
        this.cost = 0;
        this.type = type;
        this.alcoholVol = alcoholVol;
    }
    public double getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isAlcoholicDrink() {
        return alcoholVol != 0;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }
}
final class Dish implements MenuItem {
    final double cost;
    final String name;
    final String description;
    Dish(String name, String description, double cost){
        if(name.isBlank())
            throw new IllegalArgumentException("Название напитка не может быть пустым");
        if(description.isBlank())
            throw new IllegalArgumentException("Описание напитка не может быть пустым");
        if(cost<0)
            throw new IllegalArgumentException("стоимость напитка не может быть ниже нуля");
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
    Dish(String name, String description){
        if(name.isBlank())
            throw new IllegalArgumentException("Название напитка не может быть пустым");
        if(description.isBlank())
            throw new IllegalArgumentException("Описание напитка не может быть пустым");
        this.name = name;
        this.description = description;
        this.cost = 0;
    }
    public double getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}