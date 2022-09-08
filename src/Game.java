import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard("FLY MAN, FLY!", "Matteo & Nathan");
        
        gb.welcomeScreen(5000);

        boolean tutorialScreen = true;
        gb.setTutorialScreen();
        while(tutorialScreen){
            System.out.print(gb);
            System.out.println("Press Enter...");
            if(input.hasNextLine()){
                if(input.nextLine().equals("")){
                    tutorialScreen = false;
                }
            }
        }

        input.close();
    }
}
