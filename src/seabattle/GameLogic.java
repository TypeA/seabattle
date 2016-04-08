package seabattle;

import java.util.ArrayList;

public class GameLogic {

    public static ArrayList<Main.Ship> generateShips(int cAll, int c1, int c2, int c3, int c4) {
        boolean notPlaced = true;
        int tryCounter = 0;
        boolean notGenerated = true;
        int generateCounter = 0;
        ArrayList<Main.Ship> ships = new ArrayList<Main.Ship>();
        String[][] field = new String[10][10];
        while (notGenerated && generateCounter < 5) {                                                                   //цикл формирования всего поля
            for (int i = 0; i < 10; i++) {                                                                              //создание пустого поля
                for (int j = 0; j < 10; j++) {
                    field[i][j] = " ";
                }
            }
            for (int i = 0; i < cAll; i++) {                                                                            //цикл создания всех кораблей
                if (i < c4) {                                                                                           //для 4х палубника
                    while (notPlaced && tryCounter < 10) {                                                              //цикл попыток сгенерировать правильный корабль
                        Main.Ship ship = new Main.Ship(4, (int) (Math.random() * 10), (int) (Math.random() * 10), (String.valueOf((int) (Math.random() * 2)).equals("1")));
                        if (canBePlaced(field, ship)) {                                                                 //проверка на возможность размещения
                            notPlaced = false;
                            ships.add(ship);
                            field = placeTheShip(field, ship);
                        } else {
                            ship.setHorizontal(!ship.isHorizontal());                                                   //вращение корабля
                            if (canBePlaced(field, ship)) {
                                notPlaced = false;
                                ships.add(ship);
                                field = placeTheShip(field, ship);
                            } else {
                                tryCounter++;
                            }
                        }
                    }
                } else if ((i < c4 + c3) && (i >= c4)) {                                                                //для трехпалубника
                    while (notPlaced && tryCounter < 10) {
                        Main.Ship ship = new Main.Ship(3, (int) (Math.random() * 10), (int) (Math.random() * 10), (String.valueOf((int) (Math.random() * 2)).equals("1")));
                        if (canBePlaced(field, ship)) {
                            notPlaced = false;
                            ships.add(ship);
                            field = placeTheShip(field, ship);
                        } else {
                            ship.setHorizontal(!ship.isHorizontal());
                            if (canBePlaced(field, ship)) {
                                notPlaced = false;
                                ships.add(ship);
                                field = placeTheShip(field, ship);
                            } else {
                                tryCounter++;
                            }
                        }
                    }
                } else if ((i < c4 + c3 + c2) && (i >= c4 + c3)) {                                                      //для двухпалубника
                    while (notPlaced && tryCounter < 10) {
                        Main.Ship ship = new Main.Ship(2, (int) (Math.random() * 10), (int) (Math.random() * 10), (String.valueOf((int) (Math.random() * 2)).equals("1")));
                        if (canBePlaced(field, ship)) {
                            notPlaced = false;
                            ships.add(ship);
                            field = placeTheShip(field, ship);
                        } else {
                            ship.setHorizontal(!ship.isHorizontal());
                            if (canBePlaced(field, ship)) {
                                notPlaced = false;
                                ships.add(ship);
                                field = placeTheShip(field, ship);
                            } else {
                                tryCounter++;
                            }
                        }
                    }
                } else if ((i < c4 + c3 + c2 + c1) && (i >= c4 + c3 + c2)) {                                            //для однопалубника
                    while (notPlaced && tryCounter < 10) {
                        Main.Ship ship = new Main.Ship(1, (int) (Math.random() * 10), (int) (Math.random() * 10), (String.valueOf((int) (Math.random() * 2)).equals("1")));
                        if (canBePlaced(field, ship)) {
                            notPlaced = false;
                            ships.add(ship);
                            field = placeTheShip(field, ship);
                        } else {
                            tryCounter++;
                        }
                    }
                }
                tryCounter = 0;
                notPlaced = true;
            }
            if (ships.size() == cAll) {
                notGenerated = false;
            } else {
                generateCounter++;
                ships.clear();
            }
        }
        if (notGenerated) {
            System.out.println("\nI can't place all ships\n");
            return null;
        } else {
            System.out.println("\nAll ships are generated!\n");
            return ships;
        }
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
                            if (!(field[i][ship.getXPosition() - 1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition() + 1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else if (ship.getYPosition() == 0) {                                                               //вертикальный, в любом столбце, прижат наверх
                        for (int i = 0; i < ship.getSize() + 1; i++) {
                            if (!(field[i][ship.getXPosition() - 1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition() + 1].equals(" "))) {
                                flag = false;
                            }
                        }
                    } else {                                                                                            //вертикальный, в любом столбце, не прижат
                        for (int i = ship.getYPosition() - 1; i < ship.getSize() + ship.getYPosition() + 1; i++) {
                            if (!(field[i][ship.getXPosition() - 1].equals(" ") && field[i][ship.getXPosition()].equals(" ") && field[i][ship.getXPosition() + 1].equals(" "))) {
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

    private static String[][] placeTheShip(String[][] field, Main.Ship ship) {
        if (ship.isHorizontal()) {
            for (int i = ship.getXPosition(); i < ship.getXPosition() + ship.getSize(); i++) {
                field[ship.getYPosition()][i] = "\u25A0";
            }
        } else {
            for (int i = ship.getYPosition(); i < ship.getYPosition() + ship.getSize(); i++) {
                field[i][ship.getXPosition()] = "\u25A0";
            }
        }
        return field;
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
