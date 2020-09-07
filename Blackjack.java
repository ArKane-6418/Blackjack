import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Blackjack implements ActionListener {
    public JFrame frame;
    public JFrame frame2;
    public JButton play;
    public JButton hit;
    public JButton stay;
    public JButton yes;
    public JButton no;
    public JLabel blackjack;
    public JLabel hitorstay;
    public JLabel current;
    public JLabel drawn_card;
    public JLabel result;
    public JLabel playagain;
    public JPanel panel;
    public JPanel panel2;
    public Timer delay;

    int count;
    int randomNumber;

    public Blackjack() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setLayout(null);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(800, 600));
        panel2.setLayout(null);

        blackjack = new JLabel("Welcome to Blackjack!", JLabel.CENTER);
        blackjack.setSize(500, 80);
        blackjack.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
        blackjack.setLocation(150, 50);
        panel.add(blackjack);

        play = new JButton("Play");
        play.setSize(400, 100);
        play.setLocation(200, 300);
        play.addActionListener(this);
        play.setVisible(true);
        panel.add(play);

        hit = new JButton("Hit");
        hit.setSize(200, 100);
        hit.setLocation(200, 300);
        hit.addActionListener(this);
        hit.setVisible(false);
        panel.add(hit);

        stay = new JButton("Stay");
        stay.setSize(200, 100);
        stay.setLocation(400, 300);
        stay.addActionListener(this);
        stay.setVisible(false);
        panel.add(stay);

        yes = new JButton("Yes");
        yes.setSize(200, 100);
        yes.setLocation(200, 300);
        yes.addActionListener(this);
        yes.setVisible(false);
        panel2.add(yes);

        no = new JButton("No");
        no.setSize(200, 100);
        no.setLocation(400, 300);
        no.addActionListener(this);
        no.setVisible(false);
        panel2.add(no);

        playagain = new JLabel("Would you like to play again?", JLabel.CENTER);
        playagain.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        playagain.setSize(300, 70);
        playagain.setLocation(250, 100);
        playagain.setVisible(false);
        panel2.add(playagain);

        current = new JLabel("", JLabel.CENTER);
        current.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        current.setSize(300, 70);
        current.setLocation(250, 400);
        panel.add(current);

        hitorstay = new JLabel("", JLabel.CENTER);
        hitorstay.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        hitorstay.setSize(300, 70);
        hitorstay.setLocation(250, 100);
        panel.add(hitorstay);

        drawn_card = new JLabel("", JLabel.CENTER);
        drawn_card.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        drawn_card.setSize(200, 70);
        drawn_card.setLocation(300, 200);
        panel.add(drawn_card);

        result = new JLabel("", JLabel.CENTER);
        result.setFont(new Font("SANS_SERIF", Font.PLAIN, 18));
        result.setSize(300, 70);
        result.setLocation(250, 500);
        panel.add(result);

        frame = new JFrame("Blackjack");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame2 = new JFrame("Blackjack");
        frame2.setContentPane(panel2);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(false);

    }


    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == play) {
            blackjack.setVisible(false);
            start();
        }
        else if (evt.getSource() == hit) {
            try {
                game(true);
            } catch (InterruptedException e) {
            }
        }
        else if (evt.getSource() == stay) {
            try {
                game(false);
            } catch (InterruptedException e) {
            }
        }
        else if (evt.getSource() == yes) {
            yes.setVisible(false);
            no.setVisible(false);
            playagain.setVisible(false);
            frame2.setVisible(false);
            frame.setVisible(true);
            start();
        }
        else if (evt.getSource() == no) {
            yes.setVisible(false);
            no.setVisible(false);
            playagain.setVisible(false);
            frame2.setVisible(false);
            frame.setVisible(true);
            blackjack.setVisible(true);
            play.setVisible(true);
        }
    }

    public void start(){
        play.setVisible(false);
        hit.setVisible(true);
        stay.setVisible(true);

        count = 0;
        randomNumber = (int) (Math.random()*10 + 1);
        hitorstay.setText("Would you like to hit or stay?");
        current.setText("Your current count is now "+count);


    }

    public void game(boolean res) throws InterruptedException {
        if(res) {
            drawn_card.setText("You drew the number " + randomNumber);
            count += randomNumber;
            current.setText("Your current count is now " + count);
            if(count > 21){
                result.setText("You went over 21. You Lost!");
                delay = new Timer(2000, e -> restart());
                delay.start();
            }
            else if(count == 21) {
                result.setText("You hit Blackjack! Congratulations!");
                delay = new Timer(2000, e -> restart());
                delay.start();
            }

            randomNumber = (int) (Math.random()*10 + 1);

        }
        else {
            drawn_card.setText("You chose to stay.");
            current.setText("Your current count is now " + count);
        }

    }

    public void restart() {
        hit.setVisible(false);
        stay.setVisible(false);
        current.setText("");
        hitorstay.setText("");
        drawn_card.setText("");
        result.setText("");
        frame.setVisible(false);

        frame2.setVisible(true);
        yes.setVisible(true);
        no.setVisible(true);
        playagain.setVisible(true);
        delay.stop();
    }

    public static void main(String[] args) {
        Blackjack gui = new Blackjack();
    }
}
