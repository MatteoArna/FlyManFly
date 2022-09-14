import static org.alb.util.AnsiEscapes.*;

import java.io.IOException;
import java.util.Scanner;

import Objects.*;
import Other.Directions;

public class GameBoard {

    private String title;
    private String creators;

    public static final int ROWS = 5;
    public static final int COLUMNS = 25;

    private Object[][] board;
    private Player player;

    public GameBoard(String title, String creators){
        this.player = new Player();
        this.title = title;
        this.creators = creators;
        
        this.board = new Object[ROWS][COLUMNS];
        
    }

    public void play(Scanner input) throws IOException, InterruptedException{
        clear();
        player.resetPosition();
        board[player.getRow()][player.getCol()] = player;

        boolean isAlive = true;
        char in = ' ';
        while(isAlive){
            
            if(System.in.available() > 0){
                in = (char)System.in.read();
            }
            switch(in){
                case 'w':
                    moveObject(player, Directions.TOP);
                    break;
                case 'a':
                    moveObject(player, Directions.LEFT);
                    break;
                case 's':
                    moveObject(player, Directions.BOTTOM);
                    break;
                case 'd':
                    moveObject(player, Directions.RIGHT);
                    break;
                case 'q':
                    System.out.println(Integer.parseInt("ciao"));
            }
            in = ' ';
            System.out.print(this);
            Thread.sleep(300);
        }
    }


    public void tutorialScreen(Scanner input){
        String controls = "CONTROLS";

        String[] descriptions = {"OBSTACLE", "SLOW MOTION", "SCORE BOOST", "SHIELD"};
        Object[] infos = {new Dove(), new SlowMotion(), new ScoreBoost(), new Shield()};

        for(int i = 0; i < board[0].length; i++){
            if(i < controls.length()){
                board[1][i + 1] = controls.substring(i, i+1);
            }
            if(i == 2){
                board[2][i+2] = "w";
                board[3][i+1] = "a";
                board[3][i+2] = "s";
                board[3][i+3] = "d";
            }

            if(i >= 12){
                for(int j = 0; j < infos.length; j++){
                    if(i == 12){
                        board[j][i] = infos[j];
                    }else if (i > 13){
                        if(descriptions[j].length() > i - 14){
                            board[j][i] = descriptions[j].substring(i - 14, i - 13);
                        }
                    }
                }
            }
        }

        boolean tutorialScreen = true;
        while(tutorialScreen){
            System.out.print(this);
            System.out.println("Press Enter...");
            if(input.hasNextLine()){
                if(input.nextLine().equals("")){
                    tutorialScreen = false;
                }
            }
        }

    }

    public void welcomeScreen(int time) throws InterruptedException{
        long start = System.currentTimeMillis();
        board[player.getRow()][player.getCol()] = player;
        for(int i = 0; i < creators.length(); i ++){
            board[4][i + 3] = creators.substring(i, i+1);
        }
        for(int i = 0; i < title.length(); i ++){
            board[1][i + 6] = title.substring(i, i+1);
        }
        while(System.currentTimeMillis() - start < time){
            System.out.print(this);
            moveObject(player, Directions.RIGHT);
            Thread.sleep(300);
        }
        clear();
    }

    private boolean moveObject(GameObject obj, Directions direction){
        int oldRow = obj.getRow();
        int oldCol = obj.getCol();
        int newRow = oldRow;
        int newCol = oldCol;
        switch(direction){
            case LEFT:
                newCol = (newCol - 1);
                break;
            case RIGHT:
                newCol = (newCol + 1);
                break;
            case TOP:
                newRow = (newRow - 1);
                break;
            case BOTTOM:
                newRow = (newRow + 1);
                break;
            default:
                return false;
        }

        if(newCol < 0 || newCol >= board[0].length - 1){
            return false;
        }
        if(newRow < 0 || newRow >= board.length - 1){
            return false;
        }
        if(board[newRow][newCol] != null){
            return false;
        }

        board[oldRow][oldCol] = null;
        board[newRow][newCol] = obj;
        obj.move(newRow, newCol);
        return true;
    }

    public void clear(){
        board = new Object[ROWS][COLUMNS];
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
