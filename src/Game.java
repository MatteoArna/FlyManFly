import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard("FLY MAN, FLY!", "Matteo & Nathan");
        
        try {
            gb.welcomeScreen(5000);
            gb.tutorialScreen(input);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        try {
            gb.play(input);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error during the moovement");
            e.printStackTrace();
        }
        System.out.println("Hai perso");

        input.close();
    }
}
