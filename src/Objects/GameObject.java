package Objects;

public abstract class GameObject {
    private int row;
    private int col;
    private String icon = " ";
    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public String toString() {
        return icon + "";
    }

    public void move(int row, int col){
        this.row = row;
        this.col = col;
    }
}
