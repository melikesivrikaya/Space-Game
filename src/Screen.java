import javax.swing.*;

public class Screen extends JFrame {
    public Screen(){
    }

    public static void main(String[] args) {
        Screen screen = new Screen();


        screen.setResizable(false);
        screen.setFocusable(false);
        screen.setSize(1000,600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);


        screen.add(game);
        screen.setVisible(true);
    }
}
