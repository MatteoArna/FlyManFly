package Objects;

public class Dove extends GameObject{

    private int nRow;

    public Dove(boolean randomPosition, int lastCol, int nRow){
        setIcon("🕊️");
        setCol(lastCol);
        this.nRow = nRow;
        if(randomPosition){
            spawnInRandomPosition();
        }
    }

    public Dove(){
        this(false, 0, 0);
    }

    private void spawnInRandomPosition(){
        int row = (int) (Math.random() * nRow);
        setRow(row);
    }
}
