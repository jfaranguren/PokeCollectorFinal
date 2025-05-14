package model;

import java.io.Serializable;

public abstract class Accesory implements Collectable, Serializable{

    private String name;

    public Accesory(String name) {
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
