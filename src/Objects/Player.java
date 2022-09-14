package Objects;

public class Player extends GameObject{
    public Player(){
        setIcon("🕴️");
        resetPosition();
    }

    public void resetPosition(){
        move(3, 0);
    }
}
