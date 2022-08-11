
/*
There is an islan a matrix full of integers
when you pass a position where it is raining in the island.
You need to determine if this water is going to the ocean or not
the water can only go: up, down, rigth and left
the water only will move if the position next to it has a lower number, it will be stuck if it is a higher number
for example
this is the island
9 9 9
9 5 9
9 9 9
and the position passed is 1,1 the method should return false because there is no way the water gets to the ocean

Secon example:

8 9 8
5 7 8
9 9 9

and the position is 1,1 the water will get to the ocean becase 7 is bigger than 7 and found a way out

 */
public class IslandWater {

    public static void main(String[] args) {
        int[][] island = {{9,9,9},{9,5,9},{9,4,9},{9,7,9}};

        System.out.println(island[0].length);
        System.out.println(waterToOcean(2,1, island));
    }

    public static boolean waterToOcean(int row, int column, int[][] island) {
        int value = island[row][column];
        if(row == 0 || row == island.length -1 || column == 0 || column == island[0].length -1) {
            return true;
        }

        int up = island[row-1][column];
        int down = island[row+1][column];
        int right = island[row][column+1];
        int left = island[row][column-1];

        if(up<value && waterToOcean(row-1, column, island)) {
            return true;
        }
        if(down<value && waterToOcean(row+1, column, island)) {
            return true;
        }
        if(right<value && waterToOcean(row, column+1, island)) {
            return true;
        }
        if(left<value && waterToOcean(row, column-1, island)) {
            return true;
        }

        return false;
    }
}
