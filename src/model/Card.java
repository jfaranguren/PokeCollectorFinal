package model;

import java.io.Serializable;

public abstract class Card implements Collectable, Serializable{
    
    private String name;

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    


}
