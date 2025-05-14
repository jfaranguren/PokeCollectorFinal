package model;

public class TrainerCard extends Card {
    
    private String description;

    public TrainerCard(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TrainerCard [description=" + description + ", getName()=" + getName() + "]";
    }

    @Override
    public double calculatePrice(){

        return 20*description.length();
    }

    

}
