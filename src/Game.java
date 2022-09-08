import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard("FLY MAN, FLY!", "Matteo & Nathan");
        
        gb.welcomeScreen(5000);
        gb.tutorialScreen(input);

        input.close();
    }
}
