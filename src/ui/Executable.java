package ui;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Controller;

public class Executable {

    private Scanner input;
    private Controller controller; //

    public static void main(String[] args) {
        Executable exe = new Executable();
        exe.menu();
    }

    public Executable() {
        initializer();
    }

    public void initializer() {
        input = new Scanner(System.in);
        controller = new Controller();
    }

    public void menu() {

        int option = -1;
        do {
            System.out.println("Bienvenido a PokeCollector");
            System.out.println("Digite una opcion");
            System.out.println("1) Registrar Carta");
            System.out.println("2) Registrar un Accesorio");
            System.out.println("3) Listar la coleccion");
            System.out.println("4) Modificar un Pokemon");
            System.out.println("5) Borrar un elemento de la coleccion");
            System.out.println("6) Consultar las estadisticas de la coleccion");
            System.out.println("7) Consultar cuantas cartas hay por tipo de Pokemon");
            System.out.println("8) Consultar informacion de un elemento de la coleccion");
            System.out.println("0) Guardar los cambios y Salir");

            try {
                option = input.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("REGISTRO DE CARTA");
                        System.out.println("Digite una opcion");
                        System.out.println("1) Crear PokemonCard");
                        System.out.println("2) Crear TrainerCard");
                        System.out.println("3) Crear EnergyCard");
                        int register = input.nextInt();
                        switch (register) {
                            case 1:
                                registerPokemonCard();
                                break;
                            case 2:
                                registerTrainerCard();
                                break;
                            case 3:
                                registerEnergyCard();
                                break;
                            default:
                                System.out.println("Opcion invalida, regresando al menu principal");
                                break;
                        }
                        break;
                    
                    case 2:
                        System.out.println("REGISTRO DE ACCESORIO");
                        System.out.println("Digite una opcion");
                        System.out.println("1) Crear Dado");
                        System.out.println("2) Crear Moneda");
                        int registerAccesory = input.nextInt();
                        switch (registerAccesory) {
                            case 1:
                                // registerDie();
                                System.out.println("Pending");
                                break;
                            case 2:
                                registerCoin();
                                break;
                            default:
                                System.out.println("Opcion invalida, regresando al menu principal");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println(controller.getCollectionInfo());
                        break;
                    case 4:
                        System.out.println("Digite una opcion");
                        System.out.println("1) Para modificar TODOS los atributos de una carta ");
                        System.out.println("2) Para modificar ALGUNO de los atributos de la carta");
                        int modify = input.nextInt();
                        if (modify == 1) {
                            modifyPokemonCard();
                        } else {
                            modifyFieldPokemonCard();
                        }

                        break;
                    case 5:
                        deleteCard();
                        break;
                    case 6:
                        System.out.println(controller.getCardInformationByType());
                        break;
                    case 7:
                        showCollectionByPokemonType();
                        break;
                    case 8:
                        showElementInfo();
                        break;

                    case 0:
                        saveChanges();
                        break;

                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida, volviendo al menu principal");

            } 
            finally {
                if (option != 0) {
                    backToMenu();
                }

            }

        } while (option != 0);

    }

    public void backToMenu() {

        input.nextLine();

        System.out.println("\nDigite 1 para volver al menu principal");
        String menu = input.nextLine();

        // Limpieza de pantalla
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public void saveChanges() {

        System.out.println("¿Desea guardar los cambios? \n1) Si\n2) No");
        int option = input.nextInt();

        if (option == 1) {
            
            try {
                controller.saveCollectionToFile();
                System.out.println("Cambios guardados");
            } catch (FileNotFoundException e) {
                System.out.println("Error de guardado, archivo no encontrado");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error de escritura de archivos");
                e.printStackTrace();
            }
            
        } else {
            System.out.println("Los cambios han sido descartados");
        }
        System.out.println("Gracias por usar nuestros servicios. Adios!");

    }

    public void showElementInfo() {

        System.out.println(controller.getCollectionInfo());

        System.out.println("Digite la posicion de la carta en la coleccion");
        int pos = input.nextInt() - 1;

        System.out.println(controller.getElementInfo(pos));

    }

    public void showCollectionByPokemonType() {

        System.out.println(controller.getPokemonTypeList());

        System.out.println("Digite el tipo");
        int type = input.nextInt();

        System.out.println(controller.getCardInfomationByPokemonType(type));

    }

    public void registerTrainerCard() {

        input.nextLine();

        System.out.println("Digite el nombre");
        String name = input.nextLine();

        System.out.println("Digite la descripcion");
        String description = input.nextLine();

        if (controller.saveCard(name, description)) {
            System.out.println("Carta registrada exitosamente");
        } else {
            System.out.println("Coleccion llena");
        }

    }

    public void registerEnergyCard() {

        input.nextLine();

        System.out.println("Digite el nombre");
        String name = input.nextLine();

        System.out.println(controller.getPokemonTypeList());

        System.out.println("Digite el tipo de Energia");
        int type = input.nextInt();

        if (controller.saveCard(name, type)) {
            System.out.println("Carta registrada exitosamente");
        } else {
            System.out.println("Coleccion llena");
        }

    }

    public void registerPokemonCard() {

        input.nextLine();

        System.out.println("Digite el nombre");
        String name = input.nextLine();

        System.out.println("Digite los puntos de vida");
        int hp = input.nextInt();

        System.out.println(controller.getPokemonTypeList());

        System.out.println("Digite el tipo del Pokemon");
        int type = input.nextInt();

        input.nextLine();

        System.out.println("Digite el nombre del ataque");
        String attackName = input.nextLine();

        System.out.println("Digite el poder de ataque");
        int attackPower = input.nextInt();

        System.out.println(controller.getPokemonTypeList());

        System.out.println("Digite el tipo del ataque");
        int attackType = input.nextInt();

        if (controller.saveCard(name, hp, type, attackName, attackPower, attackType)) {
            System.out.println("Carta registrada exitosamente");
        } else {
            System.out.println("Coleccion llena");
        }

    }

    public void registerCoin() {

        input.nextLine();

        System.out.println("Digite el nombre");
        String name = input.nextLine();

        System.out.println("Digite la descripcion de la cara de la moneda");
        String heads = input.nextLine();

        System.out.println("Digite la descripcion del sello de la moneda");
        String tails = input.nextLine();

        System.out.println(controller.getRarityList());

        System.out.println("Digite el tipo de Rareza");
        int rarity = input.nextInt() - 1;

        if (controller.saveAccesory(name, heads, tails, rarity)) {
            System.out.println("Moneda registrada exitosamente");
        } else {
            System.out.println("Coleccion llena");
        }

    }

    public void modifyPokemonCard() {

        System.out.println(controller.getCollectionInfo());
        System.out.println("Digite la posicion de la carta la cual quiere modificar");
        int position = input.nextInt();
        if ((position > 200) || (position < 0)) {
            System.out.println("Digite una posicion valida");
        } else if (controller.verifyElement(position - 1)) {

            input.nextLine();
            System.out.println("Digite el nombre del pokemon: ");
            String name = input.nextLine();

            System.out.println("Digite los puntos de vida");
            int hp = input.nextInt();

            System.out.println(controller.getPokemonTypeList());
            System.out.println("Digite el tipo de pokemon: ");
            int type = input.nextInt();

            input.nextLine();

            System.out.println("Digite el nombre del ataque");
            String attackName = input.nextLine();

            System.out.println("Digite el poder de ataque");
            int attackPower = input.nextInt();

            System.out.println(controller.getPokemonTypeList());

            System.out.println("Digite el tipo del ataque");
            int attackType = input.nextInt();

            controller.modifyCard(name, hp, type, attackName, attackPower, attackType, position);

        }

    }

    public void modifyFieldPokemonCard() {

        System.out.println(controller.getCollectionInfo());
        System.out.println("Digite la posicion de la carta la cual quiere modificar");
        int position = input.nextInt();
        if ((position > controller.getCollectionSize()) || (position < 0)) {
            System.out.println("Digite una posicion valida");
        } else if (controller.verifyElement(position - 1)) {

            System.out.println("Digite que quiere modificar \n1. Nombre\n2. Tipo\n3. Puntos de vida");
            int option = input.nextInt();
            String data = "";
            input.nextLine();

            switch (option) {
                case 1:

                    System.out.println("Digite el nuevo nombre");
                    break;
                case 2:
                    System.out.println(controller.getPokemonTypeList());
                    System.out.println("Digite el nuevo tipo");
                    break;
                case 3:
                    System.out.println("Digite los nuevos puntos de vida");
                    break;
            }
            data = input.nextLine();

            boolean result = controller.modifyFieldPokemonCard(position - 1, option, data);

            if (result) {
                System.out.println("Campo actualizado exitosamente");
            } else {

                System.out.println("Error, no fue posible actualizar el campo");
            }
        }

    }

    public void deleteCard() {

        System.out.println(controller.getCollectionInfo());
        System.out.println("Digite la posicion del elemento que quiere borrar");
        int position = input.nextInt();
        if ((position > controller.getCollectionSize()) || (position < 0)) {
            System.out.println("Digite una posicion valida");
        } else if (controller.verifyElement(position - 1)) {

            if (controller.deleteElement(position - 1) != null) {
                System.out.println("Elemento de la coleccion borrado exitosamente");
            } else {
                System.out.println("El Elemento no pudo ser borrado");
            }

        }

    }

}