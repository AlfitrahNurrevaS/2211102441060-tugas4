import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Apple extends Block
{
    public void act()
    {
        
    }
    public void collision(SnakeWorld world) {
        setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()-2) +1,
            Greenfoot.getRandomNumber(getWorld().getHeight()-2)+1);
        world.grow(2);
        world.increaseScore(1);
        
    }
}
