import java.util.Random;

/**
 * <h1>Minesweeper algorithm assignment</h1>
 * This class contains the board creation methods with mines and calculates the hints based on the mines.
 * <p>
 * <b>Note:</b> The board's size is adjustable through the parameters of the generateBoard() method.
 *
 * @author  Gabor Koncz
 * @version 1.0
 * @since   2017-10-11
 */

public class Board {

    private static Board board;
    static int column;
    static int row;
    static int mines;
    static char[][] mineField;

    private Board() {
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    /** Generates the Minefield */
    public void generateBoard(int row, int column, int mines) {
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
        if (mines == 0) mines = random.nextInt(row*column / 2);

        while (minesPlaced < mines) {
            int y = random.nextInt(row);
            int x = random.nextInt(column);
            if (mineField[y][x] != '*') {
                mineField[y][x] = '*';
                minesPlaced++;
            }
        }
    }

    /** Calculates the adjacent mines for every cell which is not a mine. */
    public void countMines() {
        // check every cell
        for (int rowCell = 0; rowCell < mineField.length; rowCell++) {
            for (int columnCell = 0; columnCell < mineField[row].length; columnCell++) {
                if(mineField[rowCell][columnCell] != '*') {
                    mineField[rowCell][columnCell] = minesNear(rowCell, columnCell);
                }
            }
        }
    }

    /** Check the neighbour cells that is a mine or not, returns the number of adjacent mines, or 0 if there isn't any. */
    public char minesNear(int y, int x) {
        int mines = 0;
        // check mines in all directions
        mines += mineAt(y - 1, x - 1);      // NW
        mines += mineAt(y - 1, x);             // N
        mines += mineAt(y - 1, x + 1);      // NE
        mines += mineAt(y, x - 1);             // W
        mines += mineAt(y, x + 1);             // E
        mines += mineAt(y + 1, x - 1);      // SW
        mines += mineAt(y + 1, x);             // S
        mines += mineAt(y + 1, x + 1);      // SE
        if(mines > 0) {
            return (char)(mines + '0');
        } else {
            return '0';
        }
    }

    /** Checks one cell that is a mine or not, returns 1 if found, 0 if not. */
    public int mineAt(int y, int x) {
        // check boundaries and search for mines
        if(y >= 0 && y < mineField.length && x >= 0 && x < mineField[row].length && mineField[y][x] == '*') {
            return 1;
        } else {
            return 0;
        }
    }

    /** Prints out the minefield to the console */
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
