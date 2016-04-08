package seabattle;

import java.util.ArrayList;
import java.util.Scanner;

import static seabattle.GameLogic.*;

public class Main {

    static final int count1Deck = 4;
    static final int count2Deck = 3;
    static final int count3Deck = 2;
    static final int count4Deck = 1;
    static final int countOfShips = 10;

    public static void main(String[] args) {
        System.out.print("This is sea battle game!\nPlease enter your name:");
        Scanner s = new Scanner(System.in);
        String playerName = s.nextLine();
        System.out.println("Hi, " + playerName + "!");
        String[][] playerShipsField = createNewField();
        ArrayList<Ship> ships = generateShips(countOfShips, count1Deck, count2Deck, count3Deck, count4Deck);
        if (ships != null) {
            for (int i = 0; i < ships.size(); i++) {
                playerShipsField = placeTheShip(playerShipsField, ships.get(i));
            }
            printTable(playerShipsField);
            checkTheWinner(playerShipsField,playerName);
        }

    }

    static class Ship {
        private int size;
        private int xPosition;
        private int yPosition;
        private boolean horizontal;

        public void setHorizontal(boolean horizontal) {
            this.horizontal = horizontal;
        }

        public int getSize() {
            return size;
        }

        public int getXPosition() {
            return xPosition;
        }

        public int getYPosition() {
            return yPosition;
        }

        public boolean isHorizontal() {
            return horizontal;
        }

        public Ship(int size, int yPosition, int xPosition, boolean horizontal) {
            this.size = size;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
            this.horizontal = horizontal;
        }
    }
}
