package seabattle;

import java.util.ArrayList;

public class GameLogic {

    public static ArrayList<Main.Ship> generateShips(int cAll, int c1, int c2, int c3, int c4) {
        ArrayList<Main.Ship> ships = new ArrayList<Main.Ship>();
        String[][] field = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = " ";
            }
        }
        Main.Ship ship = new Main.Ship(4, 8, 0, false);
        System.out.println("Can be placed " + canBePlaced(field, ship));
        for (int i = 0; i < cAll; i++) {

        }
        System.out.println("\nAll ships are generated!\n");
        return null;
    }

    private static boolean canBePlaced(String[][] field, Main.Ship ship) {
        boolean flag = true;
        if (ship.isHorizontal()) {
            if (ship.getXPosition() <= field[0].length - ship.getSize()) {
                if (ship.getYPosition() == 0) {
                    if (ship.getXPosition() == field[0].length - ship.getSize()) {                                      //горизонтальный, на 0 строке, прижат к правому краю
                        for (int i = ship.getXPosition() - 1; i < field[0].length; i++) {
                            if (!(field[0][i].equals(" ") && field[1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getXPosition() == 0) {                                                              //горизонтальный, на 0 строке, прижат к левому краю
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[0][i].equals(" ") && (field[1][i].equals(" ")))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //горизонтальный, на 0 строке, не прижат
                        for (int i = ship.getXPosition() - 1; i < ship.getSize() + ship.getXPosition() + 1; i++) {
                            if (!(field[0][i].equals(" ") && (field[1][i].equals(" ")))) {
                                flag = false;
                            }
                        }
                    }
                } else if (ship.getYPosition() == field.length - 1) {
                    if (ship.getXPosition() == field[0].length - ship.getSize()) {                                      //горизонтальный, на последней строке, прижат к правому краю
                        for (int i = ship.getXPosition() - 1; i < field[0].length; i++) {
                            if (!(field[field.length - 2][i].equals(" ") && field[field.length - 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getXPosition() == 0) {                                                              //горизонтальный, на последней строке, прижат к левому краю
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[field.length - 2][i].equals(" ") && field[field.length - 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //горизонтальный, на последней строке, не прижат
                        for (int i = ship.getXPosition() - 1; i < ship.getSize() + ship.getXPosition() + 1; i++) {
                            if (!(field[field.length - 2][i].equals(" ") && field[field.length - 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    }
                } else {
                    if (ship.getXPosition() == field[0].length - ship.getSize()) {                                      //горизонтальный, на любой строке, прижат к правому краю
                        for (int i = ship.getXPosition() - 1; i < field[0].length; i++) {
                            if (!(field[ship.getYPosition() - 1][i].equals(" ") && field[ship.getYPosition()][i].equals(" ") && field[ship.getYPosition() + 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getXPosition() == 0) {                                                              //горизонтальный, на любой строке, прижат к левому краю
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[ship.getYPosition() - 1][i].equals(" ") && field[ship.getYPosition()][i].equals(" ") && field[ship.getYPosition() + 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //горизонтальный, на любой строке, не прижат
                        for (int i = ship.getXPosition() - 1; i < ship.getSize() + ship.getXPosition() + 1; i++) {
                            if (!(field[ship.getYPosition() - 1][i].equals(" ") && field[ship.getYPosition()][i].equals(" ") && field[ship.getYPosition() + 1][i].equals(" "))) {
                                flag = false;
                            }
                        }
                    }
                }

            } else {
                flag = false;
            }
        } else {
            if (ship.getYPosition() <= field.length - ship.getSize()) {
                if (ship.getXPosition() == 0) {
                    if (ship.getYPosition() == field.length - ship.getSize()) {                                         //вертикальный, в 0 столбце, прижат вниз
                        for (int i = ship.getYPosition() - 1; i < field.length; i++) {
                            if (!(field[i][0].equals(" ") && field[i][1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getYPosition() == 0) {                                                              //вертикальный, в 0 столбце, прижат наверх
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[i][0].equals(" ") && (field[i][1].equals(" ")))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //вертикальный, в 0 столбце, не прижат
                        for (int i = ship.getYPosition() - 1; i < ship.getSize() + ship.getYPosition() + 1; i++) {
                            if (!(field[i][0].equals(" ") && (field[i][0].equals(" ")))) {
                                flag = false;
                            }
                        }
                    }
                } else if (ship.getXPosition() == field[0].length - 1) {
                    if (ship.getYPosition() == field.length - ship.getSize()) {                                          //вертикальный, в последнем столбце, прижат вниз
                        for (int i = ship.getYPosition() - 1; i < field.length; i++) {
                            if (!(field[i][field.length - 2].equals(" ") && field[i][field.length - 1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getYPosition() == 0) {                                                               //вертикальный, в последнем столбце, прижат наверх
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[i][field.length - 2].equals(" ") && field[i][field.length - 1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //вертикальный, в последнем столбце, не прижат
                        for (int i = ship.getYPosition() - 1; i < ship.getSize() + ship.getYPosition() + 1; i++) {
                            if (!(field[i][field.length - 2].equals(" ") && field[i][field.length - 1].equals(" "))) {
                                flag = false;
                            }
                        }
                    }
                } else {
                    if (ship.getYPosition() == field.length - ship.getSize()) {                                          //вертикальный, в любом столбце, прижат вниз
                        for (int i = ship.getYPosition() - 1; i < field.length; i++) {
                            if (!(field[i][ship.getXPosition()-1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition()+1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getYPosition() == 0) {                                                               //вертикальный, в любом столбце, прижат наверх
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[i][ship.getXPosition()-1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition()+1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //вертикальный, в любом столбце, не прижат
                        for (int i = ship.getYPosition() - 1; i < ship.getSize() + ship.getYPosition() + 1; i++) {
                            if (!(field[i][ship.getXPosition()-1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition()+1].equals(" "))){
                                flag = false;
                            }
                        }
                    }

                }
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public static void printTable(ArrayList<Main.Ship> ships) {
        char line = 'A';
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.print("\n" + line++);//U25A0
        }
    }

}
