import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 1000;
    final int PANEL_HEIGHT = 1000;
    Image ball;
    Timer timer;
    Ball origin = new Ball(new Vector2(PANEL_WIDTH / 2, PANEL_HEIGHT / 2 - 300), Integer.MAX_VALUE, 0);
    Ball ball1 = new Ball(origin, 4, (Math.PI / 2));
    Ball ball2 = new Ball(ball1, 4,  (Math.PI / 4));
    int ballSize = 50;
    float gravity = 1f;


    Panel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.lightGray);
        ball = new ImageIcon("ball.png").getImage();
        timer = new Timer(17, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        ball1.calculatePos();
        ball2.calculatePos();

        // Draw 2 balls
        g2D.drawOval((int) (ball1.pos.x - ballSize / 2), (int) (ball1.pos.y - ballSize / 2), ballSize, ballSize);
        g2D.drawOval((int) (ball2.pos.x - ballSize / 2), (int) (ball2.pos.y - ballSize / 2), ballSize, ballSize);

        // Draw a line connecting the origin to the balls and the balls together
        g2D.drawLine((int) origin.pos.x, (int) origin.pos.y, (int) ball1.pos.x, (int) ball1.pos.y);
        g2D.drawLine((int) ball1.pos.x, (int) ball1.pos.y, (int) ball2.pos.x, (int) ball2.pos.y);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Equations taken from: https://web.mit.edu/jorloff/www/chaosTalk/double-pendulum/double-pendulum-en.html

        ball1.acc = (-gravity * (2 * ball1.mass + ball2.mass) * Math.sin(ball1.angle) - ball2.mass * gravity * Math.sin(ball1.angle - 2 * ball2.angle) -
                        2 * Math.sin(ball1.angle - ball2.angle) * ball2.mass *
                                ((Math.pow(ball2.vel, 2)) * ball2.distance + (Math.pow(ball1.vel, 2)) * ball1.distance * Math.cos(ball1.angle - ball2.angle)) /
                                (ball1.distance * (2 * ball1.mass + ball2.mass - ball2.mass * Math.cos(2 * ball1.angle - 2 * ball2.angle))));

        ball2.acc = (2 * (Math.sin(ball1.angle - ball2.angle) * (Math.pow(ball1.vel, 2) * ball1.distance * (ball1.mass + ball2.mass) * Math.cos(ball1.angle) +
                Math.pow(ball2.vel, 2) * ball2.distance * ball2.mass * Math.cos(ball1.angle - ball2.angle)))) /
                (ball2.distance * (2 * ball1.mass + ball2.mass - ball2.mass * Math.cos(2 * ball1.angle - 2 * ball2.angle)));

        ball1.vel += ball1.acc * 0.0017;
        ball2.vel += ball2.acc;

        ball1.angle += ball1.vel;
        ball2.angle += ball2.vel;


        repaint();
    }


}
