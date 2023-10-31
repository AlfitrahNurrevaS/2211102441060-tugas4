import greenfoot.*;
import java.util.*;

public class SnakeWorld extends World {
    private int score = 0;
    private LinkedList<SnakeBody> snake = new LinkedList<SnakeBody>();
    private int dx=1;
    private int dy=0;
    private int tailCounter = 1;
    private boolean dead = false;
    
    public SnakeWorld() {    
        super(25, 20, 32);
        SnakeBody body = new SnakeBody();
        snake.add(body);
        addObject(body, 2, 2);
        
        Apple apple = new Apple();
        addObject(apple,
            Greenfoot.getRandomNumber(getWidth()-2) +1,
            Greenfoot.getRandomNumber(getHeight()-2)+1);
            
        for (int x = 0; x < getWidth(); x++) {
            addObject(new Border(), x, 0);
            addObject(new Border(), x, getHeight() - 1);
        }
        for (int y = 0; y < getHeight(); y++) {
            addObject(new Border(), 0, y);
            addObject(new Border(), getWidth() - 1, y);
        }
    }
    public void increaseScore(int points) 
        {
        score += points;
        }

    
    public void act() {
        if (dead) return;
        changeDirection();
        SnakeBody head = snake.getLast();
        head.setImage("button-purple.png");
        SnakeBody newHead = new SnakeBody();
        int newHeadX = head.getX() + dx;
        int newHeadY = head.getY() + dy;
        
        List<Block> blocks = getObjectsAt(newHeadX, newHeadY, Block.class);
        for(Block block : blocks) {
            block.collision(this);
        }
        
        GreenfootImage scoreImage = new GreenfootImage("Score: " + score, 24, Color.WHITE, Color.BLACK);
        getBackground().drawImage(scoreImage, 10, 10);
        
        addObject(newHead, newHeadX, newHeadY);
        snake.add(newHead);
        if(tailCounter ==0){
            SnakeBody tail = snake.removeFirst();
            removeObject(tail);
        } else {
            tailCounter--;
        }
        
    }

    private void changeDirection() {
        if(Greenfoot.isKeyDown("left") && dx ==0) {
            dx = -1;
            dy = 0;
        }
        if(Greenfoot.isKeyDown("right") && dx ==0) {
            dx = 1;
            dy = 0;
        }
        if(Greenfoot.isKeyDown("down") && dy ==0) {
            dx = 0;
            dy = 1;
        }
        if(Greenfoot.isKeyDown("up") && dy ==0) {
            dx = 0;
            dy = -1;
        }
    }
    public void dead() {
        dead = true;
    }
    public void grow(int size) {
        tailCounter+=size;
    }
}
