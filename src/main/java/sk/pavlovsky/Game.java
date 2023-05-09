package sk.pavlovsky;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import sk.pavlovsky.input.Direction;
import sk.pavlovsky.input.DirectionValue;
import sk.pavlovsky.objects.Snake;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Game {
    private static final int FPS = 60;
    private static final int REFRESH = 1000/FPS;
    private Screen screen;
    private boolean running= false;
    private Snake snake;
    private DirectionValue directionValue;
    private Instant lastloop;

    public Game(Screen screen) {
        this.screen = screen;
        this.snake = new Snake(2,2);
        this.directionValue= new DirectionValue();
        this.lastloop = Instant.now();
    }

    public void start() throws IOException {
        this.running = true;
        loop();

    }

    private void loop() throws IOException {
        while(running) {
            Instant now = Instant.now();
            long delta = Duration.between(lastloop, now).toMillis();
            if (delta > REFRESH) {
                render();
                Direction d = input();
                update(d);
                this.lastloop = now;
            }
        }
    }
    private Direction input() throws IOException {
        return this.directionValue.getInput(this.screen.pollInput());
    }


    private void update(Direction direction) {
        switch (direction) {
            case RIGHT: move(1, 0);break;
            case LEFT: move(-1, 0); break;
            case UP : move(0,-1); break;
            case DOWN: move(0,1); break;
        }}
    public void move(int X, int Y) {
        int xSnake = this.snake.getX() + X;
        int ySnake = this.snake.getY() + Y;
        this.snake.setX(xSnake);
        this.snake.setY(ySnake);}



    private void render() {
        this.screen.clear();
        setSnake();
        try {
            this.screen.refresh();
        }catch (IOException e) {
            throw new RuntimeException("It is not possible to render");
        }

    }
    public void setSnake() {
        this.screen.setCharacter(this.snake.getX(), this.snake.getY(), new TextCharacter('@'));
    }
}
