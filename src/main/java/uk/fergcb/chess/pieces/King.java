package uk.fergcb.chess.pieces;

import uk.fergcb.chess.Board;
import uk.fergcb.chess.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Color col, Point pos) {
        super(col, pos);
    }

    @Override
    public List<Point> getMoveOptions(Board board) {
        List<Point> moveOptions = new ArrayList<>();
        for (int oy = -1; oy < 2; oy++) {
            for (int ox = -1; ox < 2; ox++) {
                if (ox == 0 && oy == 0) continue;
                int xx = pos.x + ox;
                int yy = pos.y + oy;
                if (xx >= 0 && xx < 8 && yy >= 0 && yy < 8) continue;
                Point point = new Point(xx, yy);
                moveOptions.add(point);
            }
        }
        return moveOptions;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
