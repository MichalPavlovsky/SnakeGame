package sk.pavlovsky;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import sk.pavlovsky.objects.Snake;

import java.io.IOException;

public class Game {
    private Screen screen;
    private boolean running= false;
    private Snake snake;

    public Game() {
        this.snake = new Snake(2,2);
    }

    public void start(Screen screen) {
        this.running = true;
        this.screen = screen;
        loop();

    }

    private void loop() {
        while(running){
            render();
//            input();
//            update(i)
        }
    }

    private void render() {
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
