package sk.pavlovsky;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import sk.pavlovsky.input.Direction;
import sk.pavlovsky.input.DirectionValue;
import sk.pavlovsky.objects.Food;
import sk.pavlovsky.objects.Snake;


import java.util.LinkedList;
import java.util.Random;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;



import static sk.pavlovsky.input.Direction.*;


public class Game {
    private static final int FPS = 10;
    private static final int REFRESH = 1000/FPS;
    private Food food;
    private Screen screen;
    private boolean running= false;
    private boolean noInput=true;
    private Snake snake;
    private DirectionValue directionValue;
    private Instant lastloop;
    private boolean firstApple = true;
    private LinkedList<Integer> xtail;
    private LinkedList<Integer> ytail;
    private Direction valueOfDirection;



    public static void setFirstDirection(boolean firstDirection) {
        Game.firstDirection = firstDirection;
    }

    private static boolean firstDirection =true;


    public int getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(int bodyParts) {
        this.bodyParts = bodyParts+getBodyParts();
    }

    private int bodyParts = 0;

    public Game(Screen screen) {
        this.screen = screen;
        this.snake = new Snake(2,2);
        this.directionValue= new DirectionValue();
        this.lastloop = Instant.now();
        this.food = new Food();
        this.xtail = new LinkedList<Integer>();
        this.xtail.add(1);
        this.xtail.add(0);
        this.ytail = new LinkedList<Integer>();
        this.ytail.add(2);
        this.ytail.add(2);
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
                update();
                this.lastloop = now;
            }
        }
    }
    private Direction input() throws IOException {
        return this.directionValue.getInput(this.screen.pollInput());
    }


    private void update() throws IOException {
        Direction direction = input();
        if (direction== QUIT) {
            this.running= false;
            this.noInput=false;
        }
        if (direction!=Direction.NONE){
            valueOfDirection= direction;
        }
        if (firstDirection) {
            valueOfDirection = RIGHT;
        }
        switch (valueOfDirection) {
            case RIGHT: move(1, 0);break;
            case LEFT: move(-1, 0); break;
            case UP : move(0,-1); break;
            case DOWN: move(0,1); break;
        }}

    public void newFood(){
        Random random = new Random();
        int foodX = random.nextInt(Main.getCOLUMN());
        int foodY = random.nextInt(Main.getROW());
        this.food.setFoodX(foodX);
        this.food.setFoodY(foodY);

    }
    public void move(Integer X, Integer Y) {
            this.xtail.add(this.snake.getX());
            this.ytail.add(this.snake.getY());
            this.xtail.remove(xtail.get(0));
            this.ytail.remove(ytail.get(0));
            Integer xSnake = this.snake.getX() + X;
            Integer ySnake = this.snake.getY() + Y;
            this.snake.setX(xSnake);
            this.snake.setY(ySnake);}

    private void render() throws IOException {
        if (firstApple) {
            newFood();
            setFood();
            firstApple= false;}
        while(noInput){
//            checkColisions();
            checkBorder();
            setFood();
            checkFood();
            Instant now = Instant.now();
            long delta = Duration.between(lastloop, now).toMillis();
            if (delta > REFRESH) {
                setFood();
                setSnake();
                update();
                try {
                    this.screen.refresh();
                }catch (IOException e) {
                    throw new RuntimeException("It is not possible to render");
                }
                this.screen.clear();
                this.lastloop = now;
            }}}

//    public void checkColisions() {
//        if (this.snake.getX()>Main.getCOLUMN() || this.snake.getY()>Main.getROW()
//                ||this.snake.getX()<0 || this.snake.getY()<0 ){
//            this.running = false;
//            this.noInput = false;
//        }
//    }
    public void checkBorder(){
        if (this.snake.getX()>Main.getCOLUMN()) {
            this.snake.setX(0);
        }else if(this.snake.getX()<0) {
            this.snake.setX(Main.getCOLUMN());
        }else if(this.snake.getY()<0) {
            this.snake.setY(Main.getROW());
        }else if (this.snake.getY()>Main.getROW()) {
            this.snake.setY(0);
    }
        for (int i = 0; i < 2+getBodyParts(); i++) {
            if (getBodyParts()>=4){
                if (i >= (getBodyParts())){
                    break;
                }else if (this.snake.getX() == this.xtail.get(i) && this.snake.getY()==this.ytail.get(i)) {
                    this.noInput = false;
                    this.running= false;
            }
    }}}

    public void checkFood(){
        if(this.food.getFoodX()== this.snake.getX()&& this.food.getFoodY() ==this.snake.getY()){
            setBodyParts(1);
            newFood();
            this.xtail.add(this.snake.getX());
            this.ytail.add(this.snake.getY());
        }
    }
    public void setFood() {
        this.screen.setCharacter(this.food.getFoodX(), this.food.getFoodY(), new TextCharacter('o'));
    }
    public void setSnake() {
        this.screen.setCharacter(this.snake.getX(), this.snake.getY(), new TextCharacter('@'));

        for (int i = 0; i < 2+getBodyParts(); i++) {
            this.screen.setCharacter(this.xtail.get(i), this.ytail.get(i), new TextCharacter('@'));
             }
         }

}

