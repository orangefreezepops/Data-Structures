//package kdf11_proj3;

import java.util.List;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Sudoku {

    private static int[][] originalBoard = new int[9][9];

    static boolean isFullSolution(int[][] board) {
        // TODO: Complete this method
        //the solve method checks that the board satisfies the rules first
        //so you can just check that all positions on the board are filled
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        //returns true if the board is filled
        return true;
    }

    static boolean reject(int[][] board) {
        // TODO: Complete this method
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                //checking rows
                for (int k = 0; k < 9; k++) {
                    //System.out.println("pass #: " + (k + 1));
                    //System.out.println(board[i][j] + " vs " + board[i][k]);
                    if ((board[i][j] == board[i][k]) && (j != k) && (board[i][j] != 0)) {
//                        System.out.println("The number " + board[i][j] + " appeared "
//                                + "too many times in row " + (i + 1) + " at ("
//                                + (i + 1) + " , " + (j + 1) + ") and (" + (i + 1) + " , " + (k + 1) + ")");
                        return true;
                    }
                }
                //checking columns
                for (int f = 0; f < 9; f++) {
                    //System.out.println("pass #: " + (f + 1));
                    //System.out.println(board[i][j] + " vs " + board[f][j]);
                    if ((board[i][j] == board[f][j]) && (i != f) && (board[i][j] != 0)) {
//                        System.out.println("The number " + board[i][j] + " appeared "
//                                + "too many times in column " + (j + 1) + " at ("
//                                + (i + 1) + " , " + (j + 1) + ") and (" + (f + 1) + " , " + (j + 1) + ")");
                        return true;
                    }
                }
                //checking 3x3 boxes
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {

                        //box on the top left
                        if ((i == 0 || i == 1 || i == 2) && (j == 0 || j == 1 || j == 2)
                                && (x == 0 || x == 1 || x == 2) && (y == 0 || y == 1 || y == 2)) {
                            //rejecting if the spot on the board
                            //equals any number in its corresponding 
                            //3x3 grid
                            //System.out.println("row: " + x + " column: " + y);
                            //System.out.println(board[i][j] + " vs " + board[x][y]);
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the top left box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box on top middle
                        if ((i == 0 || i == 1 || i == 2) && (j == 3 || j == 4 || j == 5)
                                && (x == 0 || x == 1 || x == 2) && (y == 3 || y == 4 || y == 5)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the top middle box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box on top right
                        if ((i == 0 || i == 1 || i == 2) && (j == 6 || j == 7 || j == 8)
                                && (x == 0 || x == 1 || x == 2) && (y == 6 || y == 7 || y == 8)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the top right box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box in middle left
                        if ((i == 3 || i == 4 || i == 5) && (j == 0 || j == 1 || j == 2)
                                && (x == 3 || x == 4 || x == 5) && (y == 0 || y == 1 || y == 2)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the middle left box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box in the center
                        if ((i == 3 || i == 4 || i == 5) && (j == 3 || j == 4 || j == 5)
                                && (x == 3 || x == 4 || x == 5) && (y == 3 || y == 4 || y == 5)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the center box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box on middle right
                        if ((i == 3 || i == 4 || i == 5) && (j == 6 || j == 7 || j == 8)
                                && (x == 3 || x == 4 || x == 5) && (y == 6 || y == 7 || y == 8)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the middle right box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        // box on bottom left
                        if ((i == 6 || i == 7 || i == 8) && (j == 0 || j == 1 || j == 2)
                                && (x == 6 || x == 7 || x == 8) && (y == 0 || y == 1 || y == 2)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the bottom left box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box on bottom middle
                        if ((i == 6 || i == 7 || i == 8) && (j == 3 || j == 4 || j == 5)
                                && (x == 6 || x == 7 || x == 8) && (y == 3 || y == 4 || y == 5)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the bottom middle box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }

                        //box on the bottom right
                        if ((i == 6 || i == 7 || i == 8) && (j == 6 || j == 7 || j == 8)
                                && (x == 6 || x == 7 || x == 8) && (y == 6 || y == 7 || y == 8)) {
                            if (board[i][j] == board[x][y] && (i != x) && (j != y) && (board[i][j] != 0)) {
//                                System.out.println("The number " + board[i][j] + " appeared "
//                                        + "too many times in the bottom right box at positions ("
//                                        + (i + 1) + " , " + (j + 1) + ") and (" + (x + 1) + " , " + (y + 1) + ")");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //if nothing is in conflict then we can accept the board
        return false;
    }

    static int[][] extend(int[][] board) {
        // TODO: Complete this method
        //new partial solution
        int[][] updated = new int[9][9];
        //looping through original array to duplicate it into the partial solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                updated[i][j] = board[i][j];
            }
        }
        //looping through partial solution now to find the next instance of zero
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (updated[i][j] == 0){
                    //making that instance a 1
                    updated[i][j] = 1;
                    //returning the new board
                    return updated;
                }
            }
        }
        //if no zeros then the board is invalid i.e. return null
        return null;
    }

    static int[][] next(int[][] board) {
        // TODO: Complete this method
        int[][] updated = new int[9][9];
        //copying contents of the board into my updated version
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                updated[i][j] = board[i][j];
            }
        }
        for (int i = 8; i >= 0; i--){
            for (int j = 8; j >= 0; j--){
                //looping through the array backwards to find the last spot modified
                //checking it against the original board
                if (originalBoard[i][j] == 0 && updated[i][j] != 0){
                    if (updated[i][j] == 9){
                        //if the position = 9 you cannot next it
                        return null;
                    } else {
                        //otherwise add
                        updated[i][j] ++;
                        return updated;
                    }
                }
            }
        }
        return null;
    }

    static int[][] stored(int[][] board) {
        //storing the initial board into a 2d array for comparison purposes
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    //storing the initial board 
                    originalBoard[i][j] = board[i][j];
                }
            }
        return originalBoard;
    }

    static void testIsFullSolution() {
        // TODO: Complete this method

        int[][][] solved = new int[][][]{{
            {7, 3, 5, 6, 1, 4, 8, 9, 2},
            {8, 4, 2, 9, 7, 3, 5, 6, 1},
            {9, 6, 1, 2, 8, 5, 3, 7, 4},
            {2, 8, 6, 3, 4, 9, 1, 5, 7},
            {4, 1, 3, 8, 5, 7, 9, 2, 6},
            {5, 7, 9, 1, 2, 6, 4, 3, 8},
            {1, 5, 7, 4, 9, 2, 6, 8, 3},
            {6, 9, 4, 7, 3, 8, 2, 1, 5},
            {3, 2, 8, 5, 6, 1, 7, 4, 9}}};

        int[][][] unsolved = new int[][][]{{
            {0, 9, 5, 7, 4, 3, 8, 6, 1},
            {4, 3, 1, 8, 6, 5, 9, 2, 7},
            {0, 7, 0, 1, 3, 2, 5, 4, 3},
            {3, 0, 7, 4, 5, 9, 2, 1, 6},
            {6, 1, 2, 3, 0, 7, 4, 0, 5},
            {5, 4, 0, 2, 1, 6, 7, 3, 8},
            {7, 6, 0, 5, 0, 4, 0, 8, 0},
            {9, 2, 8, 6, 7, 1, 3, 5, 4},
            {1, 5, 4, 9, 3, 8, 6, 7, 2}},
        //
        {{0, 9, 5, 7, 4, 3, 8, 6, 1},
        {4, 3, 1, 8, 6, 5, 9, 2, 7},
        {0, 7, 0, 1, 3, 2, 5, 4, 3},
        {3, 0, 7, 4, 5, 9, 2, 1, 6},
        {6, 1, 2, 3, 0, 7, 4, 0, 5},
        {5, 4, 0, 2, 1, 6, 7, 3, 8},
        {7, 6, 0, 5, 0, 4, 0, 8, 0},
        {9, 2, 8, 6, 7, 1, 3, 5, 4},
        {1, 5, 4, 9, 3, 8, 6, 7, 2}}};

        System.err.println("\tThese boards should be full\t");
        for (int[][] test : solved) {
            if (isFullSolution(test)) {
                System.err.println("Full Solution: ");
                printBoard(test);
                System.out.println("\n");
            } else {
                System.err.println("Not full solution: ");
                printBoard(test);
                System.out.println("\n");
            }
        }

        System.err.println("\tThese boards should NOT be full\t");
        for (int[][] test : unsolved) {
            if (isFullSolution(test)) {
                System.err.println("Full Solution: ");
                printBoard(test);
                System.out.println("\n");
            } else {
                System.err.println("Not full solution: ");
                printBoard(test);
                System.out.println("\n");
            }
        }
    }

    static void testReject() {
        // TODO: Complete this method

        int[][][] rejected = new int[][][]{
            //two threes in the middle column
            {{2, 9, 5, 7, 4, 0, 8, 6, 1},
            {4, 3, 1, 8, 6, 5, 9, 2, 7},
            {8, 7, 0, 1, 3, 2, 5, 4, 0},
            {3, 0, 7, 4, 5, 9, 2, 1, 6},
            {6, 1, 2, 3, 0, 7, 4, 0, 5},
            {5, 4, 0, 2, 1, 6, 7, 3, 8},
            {7, 6, 0, 5, 0, 4, 1, 8, 0},
            {9, 2, 8, 6, 7, 1, 3, 5, 4},
            {1, 5, 4, 9, 3, 8, 6, 7, 2}},
            //two threes in third row
            {{1, 5, 0, 9, 0, 8, 6, 7, 2},
            {9, 2, 8, 6, 7, 1, 3, 5, 4},
            {7, 6, 3, 5, 3, 4, 1, 8, 9},
            {5, 4, 9, 2, 1, 6, 7, 3, 8},
            {6, 1, 0, 3, 8, 7, 4, 9, 5},
            {3, 8, 7, 4, 0, 9, 2, 1, 6},
            {8, 0, 6, 1, 9, 2, 0, 4, 3},
            {4, 3, 1, 8, 0, 5, 9, 0, 7},
            {2, 9, 5, 7, 4, 3, 8, 6, 1}},
            //two threes in top central box
            {{1, 5, 0, 9, 3, 8, 6, 7, 2},
            {9, 2, 8, 6, 7, 1, 3, 5, 4},
            {7, 6, 0, 3, 0, 4, 1, 8, 9},
            {5, 0, 9, 2, 1, 6, 7, 3, 8},
            {6, 1, 0, 0, 8, 7, 4, 9, 5},
            {3, 0, 7, 4, 0, 0, 2, 1, 6},
            {8, 0, 6, 1, 9, 2, 0, 4, 3},
            {4, 3, 1, 8, 0, 5, 9, 0, 7},
            {2, 9, 5, 7, 4, 3, 8, 6, 1}},
            //two numbers in the top left box
            {{1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the top right box
            {{0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the middle left box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the center box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the middle right box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {0, 0, 0, 0, 0, 0, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the bottom left box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 6, 0, 0, 0, 0, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 0, 0}},
            //two numbers in the bottom central box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 7, 0, 0, 0, 0}},
            //two numbers in the bottom right box
            {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 8, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 8}}
        };

        int[][][] doNotReject = new int[][][]{
            //complete and correct
            {{2, 9, 5, 7, 4, 0, 8, 6, 1},
            {4, 3, 1, 8, 6, 5, 9, 2, 7},
            {8, 7, 6, 1, 3, 2, 5, 4, 0},
            {3, 8, 7, 4, 5, 9, 2, 1, 6},
            {6, 1, 2, 3, 8, 7, 4, 9, 5},
            {5, 4, 9, 2, 1, 6, 7, 3, 8},
            {7, 6, 3, 5, 2, 4, 1, 8, 9},
            {9, 2, 8, 6, 7, 1, 3, 5, 4},
            {1, 5, 4, 9, 0, 8, 6, 7, 2}},
            //ditto
            {{7, 3, 5, 6, 1, 4, 8, 9, 2},
            {8, 4, 2, 9, 7, 3, 5, 6, 1},
            {9, 6, 1, 2, 8, 5, 3, 7, 4},
            {2, 8, 6, 3, 4, 9, 1, 5, 7},
            {4, 1, 3, 8, 5, 7, 9, 2, 6},
            {5, 7, 9, 1, 2, 6, 4, 3, 8},
            {1, 5, 7, 4, 9, 2, 6, 8, 3},
            {6, 9, 4, 7, 3, 8, 2, 1, 5},
            {3, 2, 8, 5, 6, 1, 7, 4, 9}}};

        System.err.println("\tThese should be rejected\t");
        for (int[][] test : rejected) {
            if (reject(test)) {
                System.err.println("Rejected: ");
                printBoard(test);
                System.out.println("\n");
            } else {
                System.err.println("Not Rejected: ");
                printBoard(test);
                System.out.println("\n");
            }
        }
        System.err.println("\tThese should not be rejected\t");
        for (int[][] test : doNotReject) {
            if (reject(test)) {
                System.err.println("Rejected: ");
                printBoard(test);
                System.out.println("\n");
            } else {
                System.err.println("Not Rejected: ");
                printBoard(test);
                System.out.println("\n");
            }
        }
    }

    static void testExtend() {
        // TODO: Complete this method
        int[][][] doNotExtend = new int[][][]{{
            //this is a complete board
            {7, 3, 5, 6, 1, 4, 8, 9, 2},
            {8, 4, 2, 9, 7, 3, 5, 6, 1},
            {9, 6, 1, 2, 8, 5, 3, 7, 4},
            {2, 8, 6, 3, 4, 9, 1, 5, 7},
            {4, 1, 3, 8, 5, 7, 9, 2, 6},
            {5, 7, 9, 1, 2, 6, 4, 3, 8},
            {1, 5, 7, 4, 9, 2, 6, 8, 3},
            {6, 9, 4, 7, 3, 8, 2, 1, 5},
            {3, 2, 8, 5, 6, 1, 7, 4, 9}}};

        int[][][] extend = new int[][][]{{
            //will extend the number at (1,2) to 1
            {7, 0, 5, 6, 1, 4, 8, 9, 2},
            {8, 4, 2, 9, 7, 3, 5, 6, 1},
            {9, 6, 1, 2, 8, 5, 3, 7, 4},
            {2, 8, 0, 3, 4, 0, 0, 5, 7},
            {4, 1, 0, 8, 5, 7, 9, 2, 6},
            {5, 7, 9, 1, 2, 6, 4, 3, 8},
            {1, 5, 7, 0, 0, 2, 6, 8, 3},
            {6, 9, 4, 7, 3, 8, 2, 1, 5},
            {3, 2, 8, 5, 6, 1, 7, 4, 9}}};

        System.err.println("These CANNOT be extended: ");
        for (int[][] test : doNotExtend) {
            printBoard(test);
            System.err.println("\tExtended to ");
            printBoard(extend(test));
        }

        System.err.println("These can be extended: ");
        for (int[][] test : extend) {
            printBoard(test);
            System.err.println("\tExtended to");
            printBoard(extend(test));
        }
    }

    static void testNext() {
        // TODO: Complete this method
        int[][][] next = new int[][][]{{
            //will change the number 4 @ (9,8) to 5
            {7, 3, 5, 6, 1, 4, 8, 9, 2},
            {8, 4, 2, 9, 7, 3, 5, 6, 1},
            {9, 6, 1, 2, 8, 5, 3, 7, 4},
            {2, 8, 6, 3, 4, 9, 1, 5, 7},
            {4, 1, 3, 8, 5, 7, 9, 2, 6},
            {5, 7, 9, 1, 2, 6, 4, 3, 8},
            {1, 5, 7, 4, 9, 2, 6, 8, 3},
            {6, 9, 4, 7, 3, 8, 2, 1, 5},
            {3, 2, 8, 5, 6, 1, 7, 4, 0}},
        //will next the 1 to a 2
        {{1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}},
        //the 1 to a 2
        {{7, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}}
        };

        int[][][] noNext = new int[][][]{{
            //cannot add to a nine
            {9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}},
        //complete board, cannot next
        {{7, 3, 5, 6, 1, 4, 8, 9, 2},
        {8, 4, 2, 9, 7, 3, 5, 6, 1},
        {9, 6, 1, 2, 8, 5, 3, 7, 4},
        {2, 8, 6, 3, 4, 9, 1, 5, 7},
        {4, 1, 3, 8, 5, 7, 9, 2, 6},
        {5, 7, 9, 1, 2, 6, 4, 3, 8},
        {1, 5, 7, 4, 9, 2, 6, 8, 3},
        {6, 9, 4, 7, 3, 8, 2, 1, 5},
        {3, 2, 8, 5, 6, 1, 7, 4, 9}}
        };
        System.out.println("\n");
        System.err.println("These CANNOT be next'd: ");
        for (int[][] test : noNext) {
            System.out.println("\n");
            printBoard(test);
            System.out.println("\n");
            System.err.println("\tNexted to ");
            System.err.println("cannot be nexted because of Null Pointer Exception");;

        }
        System.out.println("\n");
        System.err.println("These can be next'd: ");
        for (int[][] test : next) {
            System.out.println("\n");
            printBoard(test);
            System.out.println("\n");
            System.err.println("\tNexted to ");
            printBoard(extend(next(test)));
        }
    }

    static void printBoard(int[][] board) {
        if (board == null) {
            System.out.println("No assignment");
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.println("----+-----+----");
            }
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.print("\n");
        }
    }

    static int[][] readBoard(String filename) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } catch (IOException e) {
            return null;
        }
        int[][] board = new int[9][9];
        int val = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                try {
                    val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
                } catch (Exception e) {
                    val = 0;
                }
                board[i][j] = val;
            }
        }
        return board;
    }

    static int[][] solve(int[][] board) {
        if (reject(board)) {
            return null;
        }
        if (isFullSolution(board)) {
            return board;
        }
        int[][] attempt = extend(board);
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) {
                return solution;
            }
            attempt = next(attempt);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args[0].equals("-t")) {
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else {
            int[][] board = readBoard(args[0]);
            //i added this call to store the initial board 
            stored(board);
            printBoard(board);
            System.out.println("Solution:");
            printBoard(solve(board));
        }
    }
}
