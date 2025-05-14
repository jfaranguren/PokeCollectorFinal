package model;

public class Die extends Accesory{

    private int sides;
    private String color;

    public Die(String name, int sides, String color) {
        super(name);
        this.sides = sides;
        this.color = color;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    

    @Override
    public String toString() {
        return "Die [sides=" + sides + ", color=" + color + ", getName()=" + getName() + "]";
    }

    @Override
    public double calculatePrice() {
        
        return 50*sides;
    }

    
    
    
}
