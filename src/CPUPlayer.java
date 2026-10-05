import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    private Mark cpuMark;
    private Mark opponentMark;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){

        this.cpuMark = cpu;
        this.opponentMark = (cpu == Mark.X) ? Mark.O : Mark.X;
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    private int minMax(Board board, boolean isMax) {
        numExploredNodes++;
        int score = board.evaluate(cpuMark);

        if (score == 100 || score == -100) {
            return score;
        }

        ArrayList<Move> availableMoves = board.getAvailableMoves();

        if (availableMoves.isEmpty()) {
            return 0; // Draw
        }

        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            for (Move move : availableMoves) {
                board.play(move, cpuMark);
                bestScore = Math.max(bestScore, minMax(board, false));
                board.play(move, Mark.EMPTY); // Undo move
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (Move move : availableMoves) {
                board.play(move, opponentMark);
                bestScore = Math.min(bestScore, minMax(board, true));
                board.play(move, Mark.EMPTY); // Undo move
            }
            return bestScore;
        }
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;
        
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        ArrayList<Move> availableMoves = board.getAvailableMoves();
        
        for(Move move : availableMoves) {

            board.play(move, cpuMark);
            int score = minMax(board, false);
            board.play(move, Mark.EMPTY); // Undo move

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        return null; // TODO: Implement Alpha-Beta pruning algorithm

    }

    public int AlphaBeta(Board board, boolean isMaxTurn, int alpha, int beta) {
        numExploredNodes++;

        int score = board.evaluate(cpuMark);

        if (score == 100 || score == -100 || board.getAvailableMoves().isEmpty()) {
            return score;
        }

        if (isMaxTurn) {
            int bestScore = Integer.MIN_VALUE;
            for (Move move : board.getAvailableMoves()) {
                board.play(move, cpuMark);
                int scoreCPU = AlphaBeta(board, false, alpha, beta);
                board.play(move, Mark.EMPTY);


                bestScore = Math.max(bestScore, scoreCPU);
                alpha = Math.max(alpha, bestScore);

                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;
        }

        else {
            int bestScore = Integer.MAX_VALUE;
            for (Move move : board.getAvailableMoves()) {
                board.play(move, opponentMark);
                int scoreOpponent = AlphaBeta(board, true, alpha, beta);
                board.play(move, Mark.EMPTY);

                bestScore = Math.min(bestScore, scoreOpponent);
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }
            return bestScore;


        }


    }

}
