package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Collectable> bigCollection;

    public Controller() {
        bigCollection = new ArrayList<Collectable>(); // Cambios

        try {
            loadCollectionFromFile();
        } catch (Exception e) {
            testData();
        }
    }

    //Incompleto
    public void saveCollectionToFile() throws FileNotFoundException, IOException{

        File dataBase = new File("data\\MyCollection.txt");
        dataBase.createNewFile();   

    }

    //Incompleto
    public void loadCollectionFromFile() throws ClassNotFoundException, EOFException, FileNotFoundException, IOException {

        File dataBase = new File("data\\MyCollection.txt");

        if(bigCollection.isEmpty()){
            throw new EOFException();
        }

    }

    public void testData() {

        saveCard("Leafeon", 80, 3, "Hoja afilada", 60, 3);
        saveCard("Jolteon", 80, 4, "Attacktrueno", 40, 4);
        saveCard("Energia Basica", 1);
        saveAccesory("Dado del Campeon", 6, "Rojo");
    }

    public int getCollectionSize() {
        return bigCollection.size();
    }

    /**
     * Descripcion: ...
     * pre: El arreglo collection debe estar inicializado
     * pos: PokemonCard queda añadido al arreglo collection
     * 
     * @param name                 String El nombre de la carta a registrar
     * @param healthPoints         int ...
     * @param pokemonTypeSelection int ...
     * @param attackPower          int ...
     * @return boolean true si se añade, false si no
     */
    public boolean saveCard(String name, int healthPoints, int pokemonTypeSelection, String attackName,
            int attackPower, int attackType) {

        PokemonType pokemonType = calculatePokemonType(pokemonTypeSelection);

        PokemonCard newCard = new PokemonCard(name, healthPoints, pokemonType,
                new PokemonAttack(attackName, attackPower, calculatePokemonType(attackType)));

        return bigCollection.add(newCard);
    }

    public boolean saveCard(String name, int type) {

        PokemonType pokemonType = calculatePokemonType(type);

        EnergyCard newCard = new EnergyCard(name, pokemonType);

        return bigCollection.add(newCard);

    }

    public boolean saveCard(String name, String type) {

        TrainerCard newCard = new TrainerCard(name, type);

        return bigCollection.add(newCard);

    }

    public boolean saveAccesory(String name, int sides, String color) {

        return bigCollection.add(new Die(name, sides, color));
    }

    public boolean saveAccesory(String name, String headsSimbol, String tailsSimbol, int rarity) {

        return bigCollection.add(new Coin(name, headsSimbol, tailsSimbol, calculateRarity(rarity)));
    }

    /**
     * Descripcion:
     * pre: El arreglo collection debe estar inicializado
     * 
     * @return String la lista de cartas registradas
     */
    public String getCollectionInfo() {
        String list = "Las cartas registradas son:\n";

        for (int i = 0; i < bigCollection.size(); i++) {

            list += (i + 1) + "|" + bigCollection.get(i).getName()+ "\n"; // collection[i] es un objeto PokemonCard

        }
        return list;
    }

    public boolean verifyElement(int position) {

        if (position <= bigCollection.size() && bigCollection.get(position) != null) {
            return true;
        }
        return false;
    }

    public void modifyCard(String name, int healthpoints, int pokemontype, String attackName, int attackPower,
            int attackType, int position) {
        PokemonType temp = calculatePokemonType(pokemontype);
        PokemonCard newCard = new PokemonCard(name, healthpoints, temp,
                new PokemonAttack(attackName, attackPower, calculatePokemonType(attackType)));
        bigCollection.set(position - 1, newCard);
    }

    public boolean modifyFieldPokemonCard(int position, int option, String data) {
        int dataInteger = 0;

        if (option != 1) {
            dataInteger = Integer.parseInt(data);
        }

        if (bigCollection.get(position) instanceof PokemonCard) {

            PokemonCard temp = ((PokemonCard) bigCollection.get(position));

            switch (option) {
                case 1:
                    temp.setName(data);
                    return true;
                case 2:
                    temp.setPokemonType(calculatePokemonType(dataInteger));
                    return true;

                case 3:
                    temp.setHealthPoints(dataInteger);
                    return true;

                default:
                    break;
            }

        }

        return false;

    }

    public Collectable deleteElement(int position) {

        return bigCollection.remove(position);

    }

    public String getCardInformationByType() {

        String msg = "La coleccion de cartas tiene la siguiente composicion\n";
        int pokemonCount = 0, trainerCount = 0, energyCount = 0;

        for (Collectable card : bigCollection) {
            if (card instanceof PokemonCard) {
                pokemonCount++;
            }
            if (card instanceof TrainerCard) {
                trainerCount++;
            }
            if (card instanceof EnergyCard) {
                energyCount++;
            }
        }
        msg += "La cantidad de cartas tipo Pokemon es: " + pokemonCount + "\n";
        msg += "La cantidad de cartas tipo Trainer es: " + trainerCount + "\n";
        msg += "La cantidad de cartas tipo Energy es: " + energyCount + "\n";

        return msg;

    }

    public String getCardInfomationByPokemonType(int typeSelected) {

        int pokemonCount = 0, energyCount = 0;
        PokemonType type = calculatePokemonType(typeSelected);

        String msg = "La coleccion de cartas tiene la siguiente composicion por tipo " + type + "\n";

        for (Collectable card : bigCollection) {

            // Validacion energia
            if (card instanceof EnergyCard) {
                /*
                 * EnergyCard eC = (EnergyCard) card;
                 * 
                 * if (eC.getType() == type) {
                 * 
                 * energyCount++;
                 * }
                 */

                if (((EnergyCard) card).getType() == type) {
                    energyCount++;
                }
            }

            // Validacion pokemon
            if (card instanceof PokemonCard) {

                if (((PokemonCard) card).hasPokemonAttackType(type)) {

                    pokemonCount++;
                }
            }
        }

        msg += "La cantidad de cartas Pokemon es: " + pokemonCount + "\n";
        msg += "La cantidad de cartas Energy es: " + energyCount + "\n";

        return msg;

    }

    public String getElementInfo(int position) {

        if (position > bigCollection.size() || position < 0) {
            return "Error";
        }

        return bigCollection.get(position).toString();

    }

    public String getElementPrice(int position) {

        if (position > bigCollection.size() || position < 0) {
            return "Error";
        }

        return "El precio es: " + bigCollection.get(position).calculatePrice();

    }

    public String getPokemonTypeList() {

        String msg = "Los tipos registrados son: \n";
        PokemonType[] types = PokemonType.values();

        for (int i = 0; i < types.length; i++) {
            msg += (i + 1) + ". " + types[i] + "\n";
        }

        return msg + "\n";

    }


    public PokemonType calculatePokemonType(int option) {

        PokemonType temp = PokemonType.AGUA;
        switch (option) {
            case 1:
                temp = PokemonType.AGUA;
                break;
            case 2:
                temp = PokemonType.FUEGO;
                break;
            case 3:
                temp = PokemonType.PLANTA;
                break;
            case 4:
                temp = PokemonType.ELECTRICO;
                break;
            default:
                temp = PokemonType.AGUA; //Cambiar?
                break;
        }

        return temp;

    }

    public String getRarityList() {

        String msg = "Las rarezas registradas son: \n";
        Rarity[] rarities = Rarity.values();

        for (int i = 0; i < rarities.length; i++) {
            msg += (i + 1) + ". " + rarities[i] + "\n";
        }

        return msg + "\n";

    }


    public Rarity calculateRarity(int option) {

        return Rarity.values()[option];

    }

}
