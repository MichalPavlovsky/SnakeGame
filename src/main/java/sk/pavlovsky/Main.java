package sk.pavlovsky;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;


public class Main {
    private static final int COLUMN = 40;
    private static final int ROW = 20;
    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(COLUMN,ROW)).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        Game game = new Game(screen);
        game.start();

    }
}
