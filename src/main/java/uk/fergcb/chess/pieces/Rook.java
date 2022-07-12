package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> moveOptions = new ArrayList<>();

        for (int x = 1; pos.x + x < 8; x++) {
            Point point = new Point(pos.x + x, pos.y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int x = -1; pos.x + x >= 0; x--) {
            Point point = new Point(pos.x + x, pos.y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int y = 1; pos.y + y < 8; y++) {
            Point point = new Point(pos.x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int y = -1; pos.y + y >= 0; y--) {
            Point point = new Point(pos.x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        return moveOptions;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
