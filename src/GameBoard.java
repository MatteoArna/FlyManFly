import static org.alb.util.AnsiEscapes.*;

public class GameBoard {
    private char[][] board;

    public GameBoard(){
        this.board = new char[5][30];
        fillBoard();
    }

    public void fillBoard(){
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = 'a';
			}
		}
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
                    System.out.print(board[i][j]);
				}else{
					setBackgroundColor(COLOR_CYAN);
				}
				reset();
			}
			System.out.print("\n" + "\r");
		}
        return ris;
    }
}
