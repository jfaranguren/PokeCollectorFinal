package model;

public class Coin extends Accesory {
    
    private String headsSimbol;
    private String tailsSimbol;
    private Rarity rarity;
    
    public Coin(String name, String headsSimbol, String tailsSimbol, Rarity rarity) {
        super(name);
        this.headsSimbol = headsSimbol;
        this.tailsSimbol = tailsSimbol;
        this.rarity = rarity;
    }

    public String getHeadsSimbol() {
        return headsSimbol;
    }

    public void setHeadsSimbol(String headsSimbol) {
        this.headsSimbol = headsSimbol;
    }

    public String getTailsSimbol() {
        return tailsSimbol;
    }

    public void setTailsSimbol(String tailsSimbol) {
        this.tailsSimbol = tailsSimbol;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    

    @Override
    public String toString() {
        return "Coin [headsSimbol=" + headsSimbol + ", tailsSimbol=" + tailsSimbol + ", rarity=" + rarity
                + ", getName()=" + getName() + "]";
    }

    @Override
    public double calculatePrice() {
        
        return 1000*rarity.getValue();
    }


}
