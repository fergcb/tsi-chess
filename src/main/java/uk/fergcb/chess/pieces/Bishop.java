package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> moveOptions = new ArrayList<>();
        for (int x = 1, y = 1; pos.x + x < 8 && pos.y + y < 8; x++, y++) {
            Point point = new Point(pos.x + x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int x = 1, y = -1; pos.x + x < 8 && pos.y + y >= 0; x++, y--) {
            Point point = new Point(pos.x + x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int x = -1, y = 1; pos.x + x >= 0 && pos.y + y < 8; x--, y++) {
            Point point = new Point(pos.x + x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }

        for (int x = -1, y = -1; pos.x + x >= 0 && pos.y + y >= 0; x--, y--) {
            Point point = new Point(pos.x + x, pos.y + y);
            moveOptions.add(point);
            Piece piece = board.getPieceAt(point);
            if (piece != null) break;
        }
        return moveOptions;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
