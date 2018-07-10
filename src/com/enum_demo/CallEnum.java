package com.enum_demo;

public class CallEnum {
    public static void main(String[] args)
    {
        for (Food f:Food.values()
             ) {
            System.out.println(f.getByName()+" is "+f.getByColor()+" and ordinal is "+f.getByOrdinal());
        }

        System.out.println(Food.Apple.toString());
    }
}
