import java.util.Random;

public class Board {

    private static Board board;
    static int column;
    static int row;
    static int mines;
    static char[][] mineField;

    public void generateBoard(int row, int column) {
        // Generate empty board
        mineField = new char[row][column];
        Random random = new Random();
        for (int rowCell = 0; rowCell < row; rowCell++) {
            for (int columnCell = 0; columnCell < column; columnCell++) {
                mineField[rowCell][columnCell] = '.';
            }
        }
        // Place mines to mineField
        int minesPlaced = 0;
        int mines = random.nextInt(row*column);
        while (minesPlaced < mines) {
            int y = random.nextInt(row);
            int x = random.nextInt(column);
            if (mineField[y][x] != '*') {
                mineField[y][x] = '*';
                minesPlaced++;
            }
        }
    }

    public void populateBoard() {
        /*for (int rowCell = 0; rowCell < mineField.length; rowCell++) {
            for (int columnCell = 0; columnCell < mineField[row].length; columnCell++) {
                if(mineField[rowCell][columnCell] != '*') {
                    mineField[rowCell][columnCell] = '0';
                }
            }
        }*/

        for(int row=1 ; row < 9 ; row++)
            for(int column=1 ; column < 9 ; column++){

                for(int i=-1 ; i<=1 ; i++)
                    for(int j=-1 ; j<=1 ; j++)
                        if(mineField[row][column] != -1)
                            if(mineField[row+i][column+j] == -1)
                                mineField[row][column]++;

            }
    }

    private Board() {
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    @Override
    public String toString() {
        String mineFieldString = "";
        for(int row = 0; row < mineField.length; row++) {
            for(int col = 0; col < mineField[row].length; col++) {
                mineFieldString += mineField[row][col] + " ";
            }
            mineFieldString += "\n";
        }
        return mineFieldString;
    }
}
