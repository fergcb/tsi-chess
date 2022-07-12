package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.List;

public abstract class Piece {
    public final Color col;
    public Point pos;

    public Piece(Color col, Point pos) {
        this.col = col;
        this.pos = pos;
    }

    public abstract List<Point> getMoveOptions(Board board);
    public abstract String getSymbol();
}
