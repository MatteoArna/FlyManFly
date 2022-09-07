public class Game {
    public static void main(String[] args) throws InterruptedException {
        GameBoard gb = new GameBoard();
        System.out.println(gb);

        boolean welcomeScreen = true;
        gb.setWelcomeScreen();
        while(welcomeScreen){
            System.out.println(gb);
            gb.updateWelcomeScreen();
            Thread.sleep(300);
        }
    }
}
