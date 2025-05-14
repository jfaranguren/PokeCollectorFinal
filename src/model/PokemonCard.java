package model;

public class PokemonCard extends Card {

    private int healthPoints;
    private PokemonType pokemonType;
    private PokemonAttack[] attacks;

    public PokemonCard(String name, int healthPoints, PokemonType pokemonType, PokemonAttack attack){
        super(name);
        this.healthPoints=healthPoints;
        this.pokemonType=pokemonType;
        attacks = new PokemonAttack[2];
        addAttack(attack);
    }

    public PokemonCard(String name, int healthPoints, PokemonType pokemonType, PokemonAttack[] attacks){
        super(name);
        this.healthPoints=healthPoints;
        this.pokemonType=pokemonType;
        this.attacks = attacks;
    }

    public void addAttack(PokemonAttack attack){
        for (int i = 0; i < attacks.length; i++) {
            if(attacks[i]==null){
                attacks[i]=attack;
                break;
            }
        }
     
    }

    public int getHealthPoints(){
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints){
        this.healthPoints=healthPoints;
    }

    public PokemonType getPokemonType(){
        return pokemonType;
    } 

    public void setPokemonType(PokemonType pokemonType){
        this.pokemonType = pokemonType;
    }
    
    public PokemonAttack[] getAttacks() {
        return attacks;
    }

      @Override  
      public String toString(){
        String msg = getName()+", "+healthPoints+", "+pokemonType;
        String pkmAttacks = "";
        for (int i = 0; i < attacks.length; i++) {
            if(attacks[i]!=null){
                pkmAttacks+="\n\t"+attacks[i].toString();
            }
        }

        if (pkmAttacks.equals("")) {
            pkmAttacks = "\nNo tiene ataques registrados";
        }else{
            pkmAttacks = "\n\tAtaques:"+pkmAttacks;
        }

        return msg+pkmAttacks;
    }

    public boolean hasPokemonAttackType(PokemonType type){

        for (PokemonAttack pokemonAttack : attacks) {
            if(pokemonAttack!=null&&pokemonAttack.getType()==type){
                return true; 
            }
        }
        return false;
    }

    @Override
    public double calculatePrice() {
        
        int sumAttPower = 0;

        for (PokemonAttack p : attacks) {
            if(p!=null){
                sumAttPower+=p.getAttackPower();
            }
        }

        return 1000*sumAttPower;
    }
}
