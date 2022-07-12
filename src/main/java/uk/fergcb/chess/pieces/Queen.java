package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {
    public Queen(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> diagonals = new Bishop(this.col, this.pos).getMoveOptions(board);
        List<Point> straights = new Rook(this.col, this.pos).getMoveOptions(board);

        return Stream
                .concat(diagonals.stream(), straights.stream())
                .collect(Collectors.toList());
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
