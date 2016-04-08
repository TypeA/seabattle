package seabattle;

import java.util.ArrayList;
import java.util.Scanner;

import static seabattle.GameLogic.*;

public class Main {

    public static void main(String[] args) {
        System.out.print("This is sea battle game!\nPlease enter your name:");
       // Scanner s = new Scanner(System.in);
     //  String playerName = s.nextLine();
     //   System.out.println("Hi, " + playerName + "!");
        final int count1Deck = 4;
        final int count2Deck = 3;
        final int count3Deck = 2;
        final int count4Deck = 1;
        final int countOfShips = 10;
        ArrayList<Ship> ships = generateShips(countOfShips, count1Deck, count2Deck, count3Deck, count4Deck);

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
