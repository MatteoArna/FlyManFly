import static org.alb.util.AnsiEscapes.*;
import Objects.Player;

public class GameBoard {

    private String creators = "by Matteo & Nathan";
    private String title = "FLY MAN, FLY!";

    public static final int ROWS = 5;
    public static final int COLUMNS = 25;

    private Object[][] board;
    private Player player;

    public GameBoard(){
        this.player = new Player();
        
        this.board = new Object[ROWS][COLUMNS];
        
    }

    public void setWelcomeScreen(){
        this.board[player.getRow()][player.getCol()] = player;

        for(int i = 0; i < creators.length(); i ++){
            board[4][i + 3] = creators.substring(i, i+1);
        }

        for(int i = 0; i < title.length(); i ++){
            board[1][i + 6] = title.substring(i, i+1);
        }
    }

    public void updateWelcomeScreen(){
        int row = player.getRow();
        int col = player.getCol();
        this.board[row][col] = null;
        col = (col + 1) % board[0].length;
        this.board[row][col] = player;
        player.setCol(col);

    }

    @Override
    public String toString() {
        clearScreen();
        reset();
        String ris = "";
        System.out.print("\r");
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				if(i == 4){
					setBackgroundColor(COLOR_GREEN);
				}else{
					setBackgroundColor(COLOR_CYAN);
				}
                System.out.print(board[i][j] == null ? " " : board[i][j]);
				reset();
			}
			System.out.print("\n" + "\r");
		}
        return ris;
    }
}
