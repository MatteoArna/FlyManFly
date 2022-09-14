import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard("FLY MAN, FLY!", "Matteo & Nathan");
        
        //gb.welcomeScreen(5000);
        //gb.tutorialScreen(input);

        try {
            gb.play(input);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        input.close();
    }
}
