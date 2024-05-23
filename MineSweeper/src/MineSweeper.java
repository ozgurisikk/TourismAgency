import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper { // Degerlendirme form 5

    String adminMatrix[][]; //Degerlendirme form 1
    String userMatrix[][];
    int row;
    int col;
    int userRow;
    int userCol;
    int sum;


    void run(){ //Degerlendirme formu 6, 7 - Contains all the methods that we created and runs the game. Receiving the size of game board from user.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a row value: ");
        row = input.nextInt();
        System.out.print("Enter a column value: ");
        col = input.nextInt();

        gameBoard();
        addMine();
        adminBoard();
        System.out.println("\n " +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");

        int gameWin = (row * col) - (int)(row * col) / 4; // Degerlendirme form 14 - Declaring the game win condition. When user enters a value that does not include mine in game board, gameWin decreases 1 until equals to (block count - mine count).
        System.out.println("WELCOME TO MINESWEEPER! ENJOY!");

        while(gameWin > 0){

            sum = 0;

            for (int i = 0; i < row; i ++){
                for (int j = 0; j < col; j++){
                    System.out.print(userMatrix[i][j] + " "); // Degerlendirme form 11 - Printing user game board and receiving values for row and column.
                }
                System.out.println();
            }
            System.out.print("Choose a row: "); // Degerlendirme form 9
            userRow = input.nextInt() - 1;
            System.out.print("Choose a column: ");
            userCol = input.nextInt() - 1;

            if (row >= userRow + 1 && userRow + 1 >= 0 && col >= userCol + 1 && userCol + 1 >= 0 && userCol + 1 != 0 && userRow + 1 != 0){ // Degerlendirme form 10 - Checking if the user value is in the boundaries of game board or not. If it is not program ask for a valid value.
                if (adminMatrix[userRow][userCol] == "*"){ // Degerlendirme form 6
                    System.out.println("You have stepped into MINE! You LOST! Game over."); // Degerlendirme form 13, 15 - If user founds a mine this scope works and game ends.
                    break;
                }
                else{ // Degerlendirme form 12 - Here, program checks if there are any mine around user value on matrix. If there are any, sum variable increases one by one and program printing the value of sum with String.valueof(sum) to display how many mines are around the point.
                    if (userRow - 1 >= 0){
                        if (adminMatrix[userRow-1][userCol] == "*"){
                            sum++;
                        }
                    }
                    if (userRow - 1 >= 0 && userCol + 1 < col){
                        if (adminMatrix[userRow -1][userCol + 1] == "*"){
                            sum++;
                        }
                    }
                    if (userCol + 1 < col){
                        if (adminMatrix[userRow][userCol + 1] == "*"){
                            sum++;
                        }
                    }
                    if (userRow + 1 < row && userCol + 1 < col){
                        if (adminMatrix[userRow + 1][userCol + 1] == "*"){
                            sum++;
                        }
                    }
                    if (userRow + 1 < row){
                        if (adminMatrix[userRow + 1][userCol] == "*"){
                            sum++;
                        }
                    }
                    if (userRow + 1 < row && userCol - 1 >= 0){
                        if (adminMatrix[userRow + 1][userCol - 1] == "*"){
                            sum++;
                        }
                    }
                    if (userCol - 1 >= 0 ){
                        if (adminMatrix[userRow][userCol -1] == "*"){
                            sum++;
                        }
                    }
                    if (userRow - 1 >= 0 && userCol - 1 >= 0){
                        if (adminMatrix[userRow - 1][userCol - 1] == "*"){
                            sum++;
                        }
                    }

                    userMatrix[userRow][userCol] = String.valueOf(sum);

                    System.out.println("=================================");

                    gameWin--;
                }

            }else {
                System.out.println("You have entered a wrong value!"); // Degerlendirme form 10

            }

            if (gameWin == 0){ // Degerlendirme form 6
                System.out.println("You Won! Game over!"); // Degerlendirme form 14, 15

            }
        }
    }
    void gameBoard(){ // Creates game boards.

        adminMatrix = new String[row][col];
        userMatrix  = new String[row][col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                adminMatrix[i][j] = "-";
                userMatrix [i][j] = "-";
            }
        }
    }

    void addMine(){ // Degerlendirme form 8 - Calculating mine number with formula and placing it randomly to the game board. Mine number equals to game board block count / 4.
        int mineNum = (int)(row * col) /4;

        for (int i = 0; i < mineNum; i++){
            int randomX =(int)(Math.random() * row);
            int randomY =(int)(Math.random() * col);

            if (adminMatrix[randomX][randomY] == "*"){
                mineNum++;
            }else{
                adminMatrix[randomX][randomY] = "*";
            }
        }
    }

    void adminBoard(){ // That method prints the game board with mines.
        System.out.println("Mine locations: ");

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                System.out.print(adminMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
