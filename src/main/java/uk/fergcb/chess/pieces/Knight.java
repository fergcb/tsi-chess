package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> moveOptions = Arrays.asList(
                new Point(pos.x + 2, pos.y + 1),
                new Point(pos.x + 2, pos.y - 1),
                new Point(pos.x + 1, pos.y + 2),
                new Point(pos.x + 1, pos.y - 2),
                new Point(pos.x - 1, pos.y + 2),
                new Point(pos.x - 1, pos.y - 2),
                new Point(pos.x - 2, pos.y + 1),
                new Point(pos.x - 2, pos.y - 1)
        );

        return moveOptions
                .stream()
                .filter(point -> point.x >= 0 && point.y >= 0 && point.x < 8 && point.y < 8)
                .collect(Collectors.toList());
    }

    @Override
    public String getSymbol() {
        return "N";
    }
}
