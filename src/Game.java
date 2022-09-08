import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        GameBoard gb = new GameBoard();
        System.out.println(gb);

        gb.setWelcomeScreen();
        long start = System.currentTimeMillis();
        while(System.currentTimeMillis() - start <= 5000){
            System.out.print(gb);
            gb.updateWelcomeScreen();
            Thread.sleep(200);
        }
        gb.clear();

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
