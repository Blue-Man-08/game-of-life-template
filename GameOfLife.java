import java.util.Arrays;

public class GameOfLife implements Board {
    private int[][] board;

    public GameOfLife(int x, int y)
    {
        board = new int[x][y];
    }

    public void set(int x, int y, int[][] data) {
        // Set values on the board
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                board[i + x][j + y] = data[i][j];
            }
        }
    }

    public void run(int turns) {
        for(int i = 0; i <= turns; i++){
            step();
        }
    }
    public void step()
    {
        int[][] newBoard = new int[board.length][board[0].length];
        int numNeighbors = 0;
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                numNeighbors = countNeighbors(row, col);
                if(numNeighbors < 2 || numNeighbors > 3){
                    newBoard[row][col] = 0;
                }
                if(numNeighbors == 3){
                    newBoard[row][col] = 1;
                }
            }
        }
        board = newBoard;
        print();
    }


    public int countNeighbors(int x, int y) {
        int count = 0;
        int row;
        int col;
        for(int i = 0; i < 9; i++){
            row = (i % 3) - 1;
            col = (i / 3) - 1;
            row += x;
            col += y;
            if(get(row,col) == 1){
                if(row != x || col != y){
                    count++;
                }
            }
        }
        return count;
    }

    public int get(int x, int y) {
        int xLimit = board.length;
        int yLimit= board[0].length;
        return board[(x + xLimit) % xLimit][(y + yLimit) % yLimit];
    }


    public int[][] get()
    {
        return board;
    }

    public void print(){
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y%10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x%10);
            for (int y=0; y<board[x].length; y++)
            {
                if (board[x][y] == 1)
                {
                    System.out.print("⬛");
                }
                else
                {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
