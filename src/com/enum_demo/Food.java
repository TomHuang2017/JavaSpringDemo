package com.enum_demo;
public enum Food {
    Apple("Apple",1,"Red"),
    Orige("Orige",2,"Yellow"),
    Banaber("Banaber",3,"Yello");

    private String name;
    private int Ordinal;
    private String Color;
    private Food(String name,int ordinal,String color)
    {
        this.name=name;
        this.Ordinal=ordinal;
        this.Color=color;
    }
    public String getByName()
    {
        return this.name;
    }
    public int getByOrdinal()
    {
        return this.Ordinal;
    }
    public String getByColor()
    {
        return this.Color;
    }
}
