package model;

public enum Rarity {

    LEGENDARY(50), EPIC(20), RARE(10), COMMON(5);
    
    private int value;

    private Rarity(int value) {
          this.value=value;
        }
    
        public int getValue(){
        return value;
    }

    

}
