package uk.fergcb.chess;

import uk.fergcb.chess.pieces.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {
    public static final int SIZE = 8;

    final Piece[][] board;
    final Map<Color, List<Piece>> takenPieces;

    private Color winner;

    public Board() {
        this.board = new Piece[SIZE][SIZE];
        this.takenPieces = new HashMap<>();

        takenPieces.put(Color.BLACK, new ArrayList<>());
        takenPieces.put(Color.WHITE, new ArrayList<>());

        this.init();
    }

    private void init() {
        // Place pawns
        for (int x = 0; x < SIZE; x++) {
            board[1][x] = new Pawn(Color.BLACK, new Point(x, 1));
            board[6][x] = new Pawn(Color.WHITE, new Point(x, 6));
        }

        Piece[] white = new Piece[] {
                new Rook    (Color.WHITE, new Point(0, 7)),
                new Knight  (Color.WHITE, new Point(1, 7)),
                new Bishop  (Color.WHITE, new Point(2, 7)),
                new Queen   (Color.WHITE, new Point(3, 7)),
                new King    (Color.WHITE, new Point(4, 7)),
                new Bishop  (Color.WHITE, new Point(5, 7)),
                new Knight  (Color.WHITE, new Point(6, 7)),
                new Rook    (Color.WHITE, new Point(7, 7)),
        };

        System.arraycopy(white, 0, board[7], 0, SIZE);

        Piece[] black = new Piece[] {
                new Rook    (Color.BLACK, new Point(0, 0)),
                new Knight  (Color.BLACK, new Point(1, 0)),
                new Bishop  (Color.BLACK, new Point(2, 0)),
                new Queen   (Color.BLACK, new Point(3, 0)),
                new King    (Color.BLACK, new Point(4, 0)),
                new Bishop  (Color.BLACK, new Point(5, 0)),
                new Knight  (Color.BLACK, new Point(6, 0)),
                new Rook    (Color.BLACK, new Point(7, 0)),
        };

        System.arraycopy(black, 0, board[0], 0, SIZE);
    }

    private Point parseCell(String cell) {
        int col = cell.charAt(0) - 'a';
        int row = cell.charAt(1) - '1';
        return new Point(col, row);
    }

    public boolean isComplete() {
        return this.winner != null;
    }

    public Color getWinner() {
        return this.winner;
    }

    public Piece getPieceAt(Point pos) {
        return this.board[pos.y][pos.x];
    }

    public boolean move(Color playingColor, String move) {
        String srcString = move.substring(0, 2);
        Point source = parseCell(srcString);
        String tgtString = move.substring(2, 4);
        Point target = parseCell(tgtString);

        Piece piece = board[source.y][source.x];

        if (piece == null) {
            System.out.printf("There is no piece to move at %s.\n", srcString);
            return false;
        }

        if (piece.col != playingColor) {
            System.out.println("That isn't your piece.");
            return false;
        }

        if (!piece.getMoveOptions(this).contains(target)) {
            System.out.println("I can't move there!");
            return false;
        }

        Piece takenPiece = board[target.y][target.x];
        if (takenPiece != null) {
            if (takenPiece.col == piece.col) {
                System.out.println("You can't take your own piece!");
                return false;
            }

            takenPiece.pos = new Point(-1, -1);
            takenPieces.get(playingColor).add(takenPiece);
            System.out.printf(
                    "%s took %s's %s.\n",
                    playingColor.name(),
                    takenPiece.col.name(),
                    takenPiece.getClass().getSimpleName()
            );

            if (takenPiece instanceof King) {
                winner = playingColor;
            }
        }

        piece.pos = target;
        board[target.y][target.x] = piece;
        board[source.y][source.x] = null;

        return true;
    }

    public void draw() {
        StringBuilder sb = new StringBuilder();
        sb.append(Text.yellow("   a  b  c  d  e  f  g  h \n"));
        for (int y = 0; y < SIZE; y++) {
            sb.append(Text.yellow(y + 1 + " "));
            for (int x = 0; x < SIZE; x++) {
                Piece piece = board[y][x];

                String contents;
                if (piece == null) contents = "   ";
                else {
                    contents = String.format("\u001b[1m %s ", piece.getSymbol());
                    contents = switch(piece.col) {
                        case BLACK -> Text.blue(contents);
                        case WHITE -> Text.white(contents);
                    };
                }

                boolean a = x % 2 == 0;
                boolean b = y % 2 == 0;
                boolean isWhite = (a && b) || !(a || b);
                if (isWhite)
                    sb.append(Text.bgWhite(contents));
                else
                    sb.append(Text.bgBlack(contents));
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
