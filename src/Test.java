import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        testEvaluateXWins();
        testEvaluateOWins();
        testImmediateWinX();
        testImmediateWinO();
        testMustBlockX();
        testMustBlockO();
        testMultipleBestMoves();
        testEmptyBoard();
    }

    // -------------------------------------------------------
    // TEST 1
    // X already won
    // -------------------------------------------------------
    public static void testEvaluateXWins() {

        System.out.println("\n==============================");
        System.out.println("TEST 1 - evaluate(): X wins");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.X);

        printBoard(board);

        System.out.println("Score from X perspective: "
                + board.evaluate(Mark.X));

        System.out.println("Score from O perspective: "
                + board.evaluate(Mark.O));

        System.out.println("Expected:");
        System.out.println("X = 100");
        System.out.println("O = -100");
    }

    // -------------------------------------------------------
    // TEST 2
    // O already won vertically
    // -------------------------------------------------------
    public static void testEvaluateOWins() {

        System.out.println("\n==============================");
        System.out.println("TEST 2 - evaluate(): O wins");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 1), Mark.O);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(2, 1), Mark.O);

        printBoard(board);

        System.out.println("Score from X perspective: "
                + board.evaluate(Mark.X));

        System.out.println("Score from O perspective: "
                + board.evaluate(Mark.O));

        System.out.println("Expected:");
        System.out.println("X = -100");
        System.out.println("O = 100");
    }

    // -------------------------------------------------------
    // TEST 3
    // X can win immediately
    // -------------------------------------------------------
    public static void testImmediateWinX() {

        System.out.println("\n==============================");
        System.out.println("TEST 3 - X has immediate win");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);

        board.play(new Move(1, 0), Mark.O);
        board.play(new Move(1, 1), Mark.O);

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.X);

        System.out.println("Expected best move:");
        System.out.println("(0, 2)");

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // TEST 4
    // O can win immediately
    // -------------------------------------------------------
    public static void testImmediateWinO() {

        System.out.println("\n==============================");
        System.out.println("TEST 4 - O has immediate win");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(0, 1), Mark.O);

        board.play(new Move(1, 0), Mark.X);
        board.play(new Move(1, 1), Mark.X);

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.O);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.O);

        System.out.println("Expected best move:");
        System.out.println("(0, 2)");

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // TEST 5
    // X must block O
    // -------------------------------------------------------
    public static void testMustBlockX() {

        System.out.println("\n==============================");
        System.out.println("TEST 5 - X must block O");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.O);
        board.play(new Move(0, 1), Mark.O);

        board.play(new Move(1, 0), Mark.X);
        board.play(new Move(2, 1), Mark.X);

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.X);

        System.out.println("Expected:");
        System.out.println("X should block at (0, 2)");

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // TEST 6
    // O must block X
    // -------------------------------------------------------
    public static void testMustBlockO() {

        System.out.println("\n==============================");
        System.out.println("TEST 6 - O must block X");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);

        board.play(new Move(1, 1), Mark.O);

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.O);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.O);

        System.out.println("Expected:");
        System.out.println("O should block at (0, 2)");

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // TEST 7
    // Multiple moves may have the same score
    // -------------------------------------------------------
    public static void testMultipleBestMoves() {

        System.out.println("\n==============================");
        System.out.println("TEST 7 - multiple best moves");
        System.out.println("==============================");

        Board board = new Board();

        board.play(new Move(1, 1), Mark.X);

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.O);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.O);

        System.out.println("Number of best moves returned: "
                + moves.size());

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // TEST 8
    // Empty board
    // -------------------------------------------------------
    public static void testEmptyBoard() {

        System.out.println("\n==============================");
        System.out.println("TEST 8 - empty board");
        System.out.println("==============================");

        Board board = new Board();

        printBoard(board);

        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> moves =
                cpu.getNextMoveMinMax(board);

        printMovesAndScores(board, cpu, moves, Mark.X);

        System.out.println("Number of best moves returned: "
                + moves.size());

        System.out.println("Explored nodes: "
                + cpu.getNumOfExploredNodes());
    }

    // -------------------------------------------------------
    // Print all returned best moves and show board after move
    // -------------------------------------------------------
    public static void printMovesAndScores(
            Board board,
            CPUPlayer cpu,
            ArrayList<Move> moves,
            Mark mark) {

        System.out.println("\nBest move(s):");

        for (Move move : moves) {

            System.out.println(
                    "Move: (" +
                    move.getRow() + ", " +
                    move.getCol() + ")"
            );

            board.play(move, mark);

            printBoard(board);

            System.out.println(
                    "Board evaluation after move: "
                    + board.evaluate(mark)
            );

            board.play(move, Mark.EMPTY);
        }
    }

    // -------------------------------------------------------
    // Print board
    // -------------------------------------------------------
    public static void printBoard(Board board) {

        System.out.println();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                Mark mark = board.getMark(i, j);

                if (mark == Mark.EMPTY) {
                    System.out.print(" ");
                } else {
                    System.out.print(mark);
                }

                if (j < 2) {
                    System.out.print(" | ");
                }
            }

            System.out.println();

            if (i < 2) {
                System.out.println("---------");
            }
        }

        System.out.println();
    }
}