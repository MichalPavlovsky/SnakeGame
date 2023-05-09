package sk.pavlovsky;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import sk.pavlovsky.objects.Snake;

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

    }
    public void setSnake() {
        this.screen.setCharacter(this.snake.getX(), this.snake.getY(), new TextCharacter('@'));
    }
}
