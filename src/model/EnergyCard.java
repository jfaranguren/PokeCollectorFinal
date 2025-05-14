package model;

public class EnergyCard extends Card{

    private PokemonType type;

    public EnergyCard(String name, PokemonType type) {
        super(name); //Card(name)
        this.type = type;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EnergyCard [type=" + type + ", getName()=" + getName() + "]";
    }

    @Override
    public double calculatePrice() {
        
        return 500;
    }

    

    

    
    
}
