package Objects.Enemies;

import Objects.GameObject;

public abstract class Enemy extends GameObject{
    private int lastCol;
    protected int nRow;
    public Enemy(int nRow, int lastCol, boolean randomPosition){
        setCol(lastCol);
        this.lastCol = lastCol;
        this.nRow = nRow;
        if(randomPosition){
            spawnInRandomPosition();
        }
    }
    private void spawnInRandomPosition(){
        int row = (int) (Math.random() * nRow);
        setRow(row);
    }
    public void respawn(){
        setCol(lastCol);
        spawnInRandomPosition();
    }
}