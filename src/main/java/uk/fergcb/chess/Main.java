package uk.fergcb.chess;

public class Main {

    private static final UserScanner us = new UserScanner();

    public static void main(String[] args) {
        do {
            startGame();
        } while(us.query("Play another game? "));

        us.close();
    }

    private static void startGame() {
        Board board = new Board();
        int turn = 0;
        Color currentColor;
        boolean gameOver = false;

        while (!gameOver) {
            currentColor = turn++ % 2 == 0 ? Color.WHITE : Color.BLACK;
            board.draw();
            System.out.println(
                    switch(currentColor) {
                        case BLACK -> Text.bgBlack(Text.white(" Black to move. "));
                        case WHITE -> Text.bgWhite(Text.black(" White to move. "));
                    }
            );
            boolean moveSuccess;
            do {
                String move = us.takeMove();
                moveSuccess = board.move(currentColor, move);
            } while (!moveSuccess);

            gameOver = board.isComplete();
        }

        board.draw();

        System.out.println(
                switch(board.getWinner()) {
                    case BLACK -> Text.bgBlack(Text.white(" Black wins! "));
                    case WHITE -> Text.bgWhite(Text.black(" White wins! "));
                }
        );
    }



}
