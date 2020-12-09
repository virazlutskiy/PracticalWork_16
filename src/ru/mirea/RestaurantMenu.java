package ru.mirea;
public class RestaurantMenu {
    public static String[] allDishes ={"Пиво",
            "Вино",
            "Водка",
            "Бренди",
            "Шампанское",
            "Виски",
            "Текила",
            "Ром",
            "Вермухт",
            "Ликёр",
            "Ягермейстер",
            "Сок",
            "Коффе",
            "Чай зелёный",
            "Чай черный",
            "Молоко",
            "Вода",
            "Газеровка",
            "Бургер",
            "Картофель пюре",
            "Салат 'Вечно зелёный'"
    };
    public static MenuItem getItemByName(String itemName){
        switch (itemName){
            case "Пиво":
                return BEER;
            case "Вино":
                return WINE;
            case "Водка":
                return VODKA;
            case "Бренди":
                return BRANDY;
            case "Шампанское":
                return CHAMPAGNE;
            case "Виски":
                return WHISKEY;
            case "Текила":
                return TEQUILA;
            case "Ром":
                return RUM;
            case "Вермухт":
                return VERMOUTH;
            case "Ликёр":
                return LIQUOR;
            case "Ягермейстер":
                return JAGERMEISTER;
            case "Сок":
                return JUICE;
            case "Коффе":
                return COFFEE;
            case "Чай зелёный":
                return GREEN_TEA;
            case "Чай черный":
                return BLACK_TEA;
            case "Молоко":
                return MILK;
            case "Вода":
                return WATER;
            case "Газеровка":
                return SODA;
            case "Бургер":
                return BURGER;
            case  "Картофель пюре":
                return MASHED_POTATOES;
            case "Салат 'Вечно зелёный'":
                return SALAD;
            default:
                return WATER;
        }
    }

    public static Drink BEER = new Drink("Пиво","Холодное. 0,5 л", 200,DrinkTypeEnum.BEER,0.3);
    public static Drink WINE = new Drink("Вино","Холодное. 0,5 л", 200,DrinkTypeEnum.WINE,0.3);
    public static Drink VODKA = new Drink("Водка","Холодное. 0,5 л", 200,DrinkTypeEnum.VODKA,0.3);
    public static Drink BRANDY = new Drink("Бренди","Холодное. 0,5 л", 200,DrinkTypeEnum.BRANDY,0.3);
    public static Drink CHAMPAGNE = new Drink("Шампанское","Холодное. 0,5 л", 200,DrinkTypeEnum.CHAMPAGNE,0.3);
    public static Drink WHISKEY = new Drink("Виски","Холодное. 0,5 л", 200,DrinkTypeEnum.WHISKEY,0.3);
    public static Drink TEQUILA = new Drink("Текила","Холодное. 0,5 л", 200,DrinkTypeEnum.TEQUILA,0.3);
    public static Drink RUM = new Drink("Ром","Холодное. 0,5 л", 200,DrinkTypeEnum.RUM,0.3);
    public static Drink VERMOUTH = new Drink("Вермухт","Холодное. 0,5 л", 200,DrinkTypeEnum.VERMOUTH,0.3);
    public static Drink LIQUOR = new Drink("Ликёр","Холодное. 0,5 л", 200, DrinkTypeEnum.LIQUOR,0.3);
    public static Drink JAGERMEISTER = new Drink("Ягермейстер","Холодное. 0,5 л", 200, DrinkTypeEnum.JAGERMEISTER,0.3);
    public static Drink JUICE = new Drink("Сок","Холодное. 0,5 л", 200, DrinkTypeEnum.JUICE,0.3);
    public static Drink COFFEE = new Drink("Коффе","Холодное. 0,5 л", 200, DrinkTypeEnum.COFFEE,0.3);
    public static Drink GREEN_TEA = new Drink("Чай зелёный","Холодное. 0,5 л", 200, DrinkTypeEnum.GREEN_TEA,0.3);
    public static Drink BLACK_TEA = new Drink("Чай черный","Холодное. 0,5 л", 200, DrinkTypeEnum.BLACK_TEA,0.3);
    public static Drink MILK = new Drink("Молоко","Холодное. 0,5 л", 200, DrinkTypeEnum.MILK,0.3);
    public static Drink WATER = new Drink("Вода","Холодное. 0,5 л", 200, DrinkTypeEnum.WATER,0.3);
    public static Drink SODA = new Drink("Газеровка","Холодное. 0,5 л", 200, DrinkTypeEnum.SODA,0.3);

    public static Dish BURGER = new Dish("Бургер","Сочное мясо, мягкие булочки, свежие овощи и соус",500);
    public static Dish MASHED_POTATOES = new Dish("Картофель пюре","Картофель пюре со сливками",400);
    public static Dish SALAD = new Dish("Салат 'Вечно зелёный' ","Свежие овощи с бабушкиной грядки",250);
}
