package Objects.Enemies;

public class Dove extends Enemy{


    public Dove(boolean randomPosition, int lastCol, int nRow){
        super(nRow, lastCol, randomPosition);
        setIcon("🕊️");
    }

    public Dove(){
        this(false, 0, 0);
    }
}
