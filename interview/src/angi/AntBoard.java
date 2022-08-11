package angi;
import java.util.*;

/*
Hard to explain but here we go
You have a board (n*n matrix) filled with booleans
You have an ant with an initial posicion x and y this position is asured it will always be inside the board
You also have an starting direction by default is north
You will be passed a list of actions that can be of 3 types:
rotate -> the direction of the ant will be move clockwise or anticlockwise depending on the value of the position
move -> the ant has to move to the next position depending on the direction
flip -> you will change toggle the position the ant is in
the expected response is a list of all the visited positions of the ant
 */
public class AntBoard {



        public static void main(String[] args) {
            Boolean[][] matrix = {{true, true},{true,true}};
            System.out.println(simulate(matrix,0,0,
                    List.of("rotate","rotate","move","flip","rotate","move","flip","rotate", "move")));
        }

        public static String simulate(Boolean[][] board, int start_row, int start_column, List<String> actions) {
            StringBuffer result = new StringBuffer(start_row + "," + start_column);
            String direction = "north";

            for(String action: actions) {

                switch (action) {
                    case "flip":
                        flip(board,start_row,start_column);
                        break;
                    case "rotate":
                        direction = rotate(board, start_row, start_column, direction);
                        // System.out.println(direction);
                        break;
                    case "move":
                        List<Integer> newInt =  move(start_row,start_column,direction);
                        start_row = newInt.get(0);
                        start_column = newInt.get(1);
                        result.append("|" + start_row + "," + start_column);
                        break;
                }
            }

            return result.toString();
        }

        public static void flip(Boolean[][] board, int start_row, int start_column){
            if(board!=null) {
                board[start_row][start_column] = !board[start_row][start_column];
            }
        }
        public static String rotate(Boolean[][] board, int start_row, int start_column, String direction) {
            String newDirection = null;
            Boolean color = board[start_row][start_column];
            if(color) {
                switch (direction) {
                    case "north":
                        newDirection = "east";
                        break;
                    case "east":
                        newDirection = "south";
                        break;
                    case "south":
                        newDirection = "west";
                        break;
                    case "west":
                        newDirection = "north";
                        break;
                }
            }else {
                switch (direction) {
                    case "north":
                        newDirection = "west";
                        break;
                    case "east":
                        newDirection = "north";
                        break;
                    case "south":
                        newDirection = "east";
                        break;
                    case "west":
                        newDirection = "south";
                        break;
                }
            }
            return newDirection;
        }

        public static List<Integer> move(int start_row, int start_column, String direction) {

            switch (direction) {
                case "north":
                    start_row--;
                    break;
                case "east":
                    start_column++;
                    break;
                case "south":
                    start_row++;
                    break;
                case "west":
                    start_column--;
                    break;
            }

            return List.of(start_row,start_column);
        }



}
