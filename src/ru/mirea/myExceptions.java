package ru.mirea;

public class myExceptions {
}
class OrderAlreadyAddedException extends Exception{
    OrderAlreadyAddedException(String string)
    {
     super("OrderAlreadyAddedException: " + string);
    }
}
class IllegalTableNumber extends Exception{
    IllegalTableNumber(String string)
    {
        super("IllegalTableNumber: " + string);
    }
}
