import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer((int) 0.9,this);
    private double time;
    private int fire = 0;
    private BufferedImage img;
    private ArrayList<Fire> fires = new ArrayList<Fire>();
    private int fireDirY = 1;
    private int ballX = 0;
    private int ballDirX = 2;
    private int shipX = 0;
    private int shipDirX = 20;

    public Game(){
        try {
            img = ImageIO.read(new FileImageInputStream(new File("ship.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(Color.BLACK);
        timer.start();
    }
    public boolean control(){
        for (Fire f : fires){
            if (new Rectangle(f.getX(),f.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        time += 0.9;
        g.setColor(Color.red);
        g.fillOval(ballX,0,20,20);
        g.drawImage(img,shipX,420,img.getWidth() / 3,img.getHeight() / 3,this);
        for(int i = 0 ; i < fires.size() ; i++){
            if(fires.get(i).getY() < 0){
                fires.remove(i);
            }
        }
        g.setColor(Color.WHITE);
        for (Fire f : fires){
            g.fillRect(f.getX(),f.getY(),10,20);
        }
        if (control()){
            timer.stop();
            JOptionPane.showMessageDialog(null,"Win.. ! \nTime : " +(int) time / 1000 + " sn\nFire : " +  fire,"!",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int x = e.getKeyCode();
        if(x == KeyEvent.VK_LEFT){
            if(shipX <= 0){
                shipX = 0;
            }
            else{
                shipX -= shipDirX;
            }
        }
        else if(x == KeyEvent.VK_RIGHT){
           if(shipX >= 920){
                shipX = 920;
            }
            else{
                shipX += shipDirX;
            }
        }
        else if(x == KeyEvent.VK_CONTROL){
            fires.add(new Fire(shipX + 28 ,400));
            fire++;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (Fire f : fires){
             f.setY(f.getY() - fireDirY);
        }

        ballX += ballDirX;
        if (ballX >= 960){
            ballDirX = - ballDirX;
        }
        if (ballX <= 0){
            ballDirX = - ballDirX;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
