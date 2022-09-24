import static org.alb.util.AnsiEscapes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Objects.*;
import Objects.Enemies.*;
import Other.Directions;

public class GameBoard {

    private String title;
    private String creators;
    
    private int score = 0;

    public static final int ROWS = 5;
    public static final int COLUMNS = 25;
    
    private List<GameObject> objects;
    private int refresRate = 100;
    private Object[][] board;
    private Player player;

    public GameBoard(String title, String creators){
        this.player = new Player();
        this.title = title;
        this.creators = creators;
        this.objects = new ArrayList<>();
        this.board = new Object[ROWS][COLUMNS];
        
    }

    private GameObject generateDove(){
        Dove d = new Dove(true, board[0].length - 1, board.length - 1);
        board[d.getRow()][d.getCol()] = d;
        return d;
    }

    private boolean isOnTheSameSpot(GameObject obj1, GameObject obj2){
        if(obj1.getCol() == obj2.getCol() && obj1.getRow() == obj2.getRow()){
            return true;
        }
        return false;
    }

    private GameObject mooveObjects(List<GameObject> objects){
        List<GameObject> toRemove = new ArrayList<>();
        for (GameObject gameObject : objects) {
            if(gameObject.getCol() == 0){
                toRemove.add(gameObject);
            }else{
                moveObject(gameObject, Directions.LEFT);
                if(isOnTheSameSpot(gameObject, player)){
                    return gameObject;
                }
            }
        }
        for (GameObject gameObject : toRemove) {
            board[gameObject.getRow()][gameObject.getCol()] = null;
            objects.remove(gameObject);
            if(gameObject instanceof Enemy){
                ((Enemy)gameObject).respawn();
                board[gameObject.getRow()][gameObject.getCol()] = gameObject;
                objects.add(gameObject);
            }
        }
        return null;
    }

    public void play(Scanner input) throws IOException, InterruptedException{
        clear();
        player.resetPosition();
        board[player.getRow()][player.getCol()] = player;

        objects.add(generateDove());

        boolean isAlive = true;
        boolean refresh = true;
        char in = ' ';

        long start = System.currentTimeMillis();
        long now = 0l;

        GameObject contact = null;

        System.out.print(this);
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
                default:
                    refresh = false;
                    break;
            }

            if(now - start > refresRate){
                score++;
                start = now;
                contact = mooveObjects(objects);
                if(contact instanceof Dove){
                    isAlive = false;
                }
                refresh = true;
            }
            in = ' ';
            if(refresh){
                System.out.print(this);
            }
            refresh = true;
            now = System.currentTimeMillis();
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
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
            case TOP:
                newRow--;
                break;
            case BOTTOM:
                newRow++;
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
        System.out.print("\r");
        String ris = "";
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
        ris += "Score: " + score;
        return ris;
    }
}
