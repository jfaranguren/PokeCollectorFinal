package model;

import java.io.Serializable;

public class PokemonAttack implements Serializable{

    private String name;
    private int attackPower;
    private PokemonType type;
   
    public PokemonAttack(String name, int attackPower, PokemonType type) {
        this.name = name;
        this.attackPower = attackPower;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + ", Power: " + attackPower + ", Type: " + type;
    }

    

    

    

    
}
