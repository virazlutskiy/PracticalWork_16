package ru.mirea;

public class Address {
    private String cityName;
    private int zipCode;
    private String streetName;
    private int buildingNumber;
    private char buildingLetter;
    private int apartmentNumber;
    //public Address EMPTY_ADDRESS = new Address();

    private Address(){}
    public Address(String cityName,
            int zipCode,
            String streetName,
            int buildingNumber,
            char buildingLetter,
            int apartmentNumber){
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCityName(){
        return this.cityName;
    }
    public int getZipCode(){
        return this.zipCode;
    }
    public String getStreetName(){
        return this.streetName;
    }
    public int getBuildingNumber(){
        return this.buildingNumber;
    }
    public char getBuildingLetter(){
        return this.buildingLetter;
    }
    public int getApartmentNumber(){
        return this.apartmentNumber;
    }
    public String getFullAddress(){return cityName
        +" "+ zipCode
        +" "+ streetName
        +" "+ buildingNumber
        +" "+ buildingLetter
        +" "+ apartmentNumber;}
}