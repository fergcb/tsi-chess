package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> moveOptions = new ArrayList<>();
        int dir = this.col == Color.BLACK ? 1 : -1;
        int ny = this.pos.y + dir;
        if (ny >= 0 && ny < 8)
            moveOptions.add(new Point(this.pos.x, ny));
        return moveOptions;
    }

    @Override
    public String getSymbol() {
        return "p";
    }
}
