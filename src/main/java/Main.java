import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            clearScreen();
            printMenu();
            int selection = getSelection();
            switch (selection) {
                case 0:
                    generateMineField();
                    break;
                case 1:
                    running = false;
                    break;
            }
        }
    }

    private static void generateMineField() {
        int width = 0;
        int height = 0;
        int mines = 0;
        try {
            System.out.print("Please set the width of the board:");
            Scanner widthInput = new Scanner(System.in);
            width = widthInput.nextInt();

            System.out.print("Please set the height of the board:");
            Scanner heightInput = new Scanner(System.in);
            height = heightInput.nextInt();

            boolean correctMinesNumber = false;
            while (correctMinesNumber != true) {
                System.out.print("\nPlease set the number of mines, or enter 0 to random:");
                Scanner minestInput = new Scanner(System.in);
                mines = minestInput.nextInt();
                if (mines < width * height -1) {
                    correctMinesNumber = true;
                } else {
                    System.out.print(String.format("Too many mines! The maximum number of mines is %d ", width * height -1));
                }
            }
        } catch (InputMismatchException inputmistmatch) {
            System.out.print(inputmistmatch.getMessage());
        } catch (IllegalArgumentException illegalarg) {
            System.out.println(illegalarg.getMessage());
        }

        Board board = null;
        board = board.getInstance();
        board.generateBoard(width, height, mines);
        System.out.println(board.toString());
        board.countMines();
        System.out.println(board.toString());
    }

    /** <h2> Handles the selection in the menu, return an integer. </h2> */
    private static int getSelection() {
        System.out.print("Please select an option:");
        Scanner input = new Scanner(System.in);
        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Please select an option:");
        }
        return input.nextInt();
    }

    /** <h2> Prints out the menu options in the terminal </h2> */
    private static void printMenu() {
        String[] logo = FileReader.readFile("src/main/resources/logo.txt");
        String[] options = {
                "Generate minefield",
                "Exit"
        };
        for (String line: logo) {
            System.out.println(line);
        }
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + ". " + options[i]);
        }
    }

    /**
     * <h2> Clears the screen in the command line </h2>
     * <b> Note: </b> Not working in the IntelliJ terminal.
     */
    private static void clearScreen() {
        System.out.print(String.format("%c[2J", 0x1B));  // clear screen
        System.out.print(String.format("%c[3J", 0x1B));  // clear scrollback buffer
        System.out.print(String.format("%c[%d;%df", 0x1B, 1, 1));  // position cursor to 1,1
    }
}
