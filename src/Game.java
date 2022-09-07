import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard();
        System.out.println(gb);

        boolean welcomeScreen = true;
        gb.setWelcomeScreen();
        while(welcomeScreen){
            System.out.print(gb);
            gb.updateWelcomeScreen();
            System.out.println("Press Enter...");
            Thread.sleep(200);

            if(input.hasNextLine()){
                if(input.nextLine().equals("")){
                    welcomeScreen = false;
                }
            }
        }
        gb.clear();

        boolean tutorialScreen = true;
        gb.setTutorialScreen();
        while(tutorialScreen){
            System.out.print(gb);
            System.out.println("Press Enter Again...");
            if(input.hasNextLine()){
                if(input.nextLine().equals("")){
                    tutorialScreen = false;
                }
            }
        }

        input.close();
    }
}
