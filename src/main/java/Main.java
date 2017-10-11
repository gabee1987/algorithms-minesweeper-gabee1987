

public class Main {

    public static void main(String[] args) {
        Board board = null;
        board = board.getInstance();
        board.generateBoard(9,9);
        System.out.println(board.toString());
        board.countMines();
        System.out.println(board.toString());
    }
}
