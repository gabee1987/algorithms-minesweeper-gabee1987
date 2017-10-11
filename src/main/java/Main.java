

public class Main {

    public static void main(String[] args) {
        Board board = null;
        board = board.getInstance();
        board.generateBoard(7,5);
        System.out.println(board.toString());
        board.populateBoard();
        System.out.println(board.toString());
    }
}
