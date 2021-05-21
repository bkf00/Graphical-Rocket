import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public int planet = 0;
    public double yDouble = 1;
    public double xDouble = 1;
    public int destinationCheck = 0;
    public int x = 0;
    public int y = 0;

    public Main() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("ROCKET LAUNCH PROTOTYPE PROJECT :)");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JButton moon = new JButton("Moon trajectory");
                JButton mars = new JButton("Mars trajectory");
                JButton pluto = new JButton("Pluto trajectory");
                JButton restartButton = new JButton("Return to Earth");

                moon.setBounds(0, 0, 110, 30);
                mars.setBounds(0, 30, 110, 30);
                pluto.setBounds(0, 60, 110, 30);
                restartButton.setBounds(1180, 0, 100, 30);

                frame.add(moon);
                frame.add(mars);
                frame.add(pluto);
                frame.add(restartButton);

                moon.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        planet = 1;
                    }
                });
                mars.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        planet = 2;
                    }
                });
                pluto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        planet = 3;
                    }
                });
                restartButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        destinationCheck = 0;
                        planet = 0;
                        xDouble = 1;
                        yDouble = 1;
                        x = 0;
                        y = 0;
                    }
                });
                frame.add(new RocketLaunch());
                frame.pack();
                frame.setLayout(new FlowLayout(FlowLayout.CENTER));
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class RocketLaunch extends JPanel {
        public RocketLaunch() {
            Timer timer = new Timer(34, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                    if (destinationCheck != 1 && planet != 0) {
                        if (planet == 1) {
                            moveRocketEarthMoon();
                        } else if (planet == 2) {
                            moveRocketEarthMars();
                        } else if (planet == 3) {
                            moveRocketEarthPluto();
                        } else {
                            error();
                        }
                        checkDest();
                    }
                }
            });
            timer.start();
        }

        protected void error() {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "There was an error! Please try again!", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
        }

        protected void destinationMessage(String planet) {
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Destination " + planet + " reached!", "Notification", JOptionPane.PLAIN_MESSAGE);
            destinationCheck = 1;
        }

        protected void checkDest() {
            if (x == 1 && y == 351) {
                destinationMessage("Moon");
            }
            if (x == 401 && y == 401) {
                destinationMessage("Mars");
            }
            if (x == 0 && y == 0) {
                destinationMessage("Mars");
            }
            if (x == 957 && y == 141) {
                destinationMessage("Pluto");
            }

            if (x > 1280 || y > 720) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, " ! OUT OF ORBIT ! ", "Warning!", JOptionPane.WARNING_MESSAGE);
                destinationCheck = 1;
            }
        }

        protected void moveRocketEarthPluto() {
            xDouble = xDouble * 1.04;
            yDouble = yDouble + 0.8;
            y = (int) Math.round(yDouble);
            x = (int) Math.round(xDouble);
        }

        protected void moveRocketEarthMoon() {
            yDouble = yDouble + 2;
            y = (int) Math.round(yDouble);
            x = (int) Math.round(xDouble);
        }

        protected void moveRocketEarthMars() {
            xDouble = xDouble + 2;
            yDouble = yDouble + 2;
            y = (int) Math.round(yDouble);
            x = (int) Math.round(xDouble);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1280, 720);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D space = (Graphics2D) g.create();
            space.setColor(Color.BLACK);
            space.fillPolygon(new int[]{0, 1280, 1280, 0}, new int[]{0, 0, 720, 720}, 4);
            for (double i = 0; i < 7; i++) {
                int xStar = (int) (1280 * Math.random());
                int yStar = (int) (720 * Math.random());
                int starWidth = (int) (7 * Math.random());
                int starHeight = (int) (7 * Math.random());
                g.setColor(Color.white);
                g.fillOval(xStar + 1, yStar + 1, starWidth, starHeight);
                g.setColor(Color.gray);
                g.drawOval(xStar + 1, yStar + 1, starWidth, starHeight);
            }

            Graphics2D mars = (Graphics2D) g.create();
            mars.setColor(new Color(189, 79, 0));
            mars.fillOval(400, 50, 200, 200);

            Graphics2D moon = (Graphics2D) g.create();
            moon.setColor(Color.WHITE);
            moon.fillOval(50, 200, 100, 100);
            moon.setColor(Color.GRAY);
            moon.fillOval(80, 220, 15, 15);
            moon.fillOval(100, 240, 30, 30);

            Graphics2D pluto = (Graphics2D) g.create();
            pluto.setColor(new Color(98, 145, 222));
            pluto.fillOval(1000, 350, 150, 150);

            Graphics2D terra = (Graphics2D) g.create();
            terra.setColor(Color.BLUE);
            terra.fillOval(-700, 500, 1310, 800);

            Graphics2D rocket = (Graphics2D) g.create();
            int xPlanet = 0; //not used anymore
            int yPlanet = -50; //not used anymore

            int[] xRocket = {100 + x + xPlanet, 120 + x + xPlanet, 120 + x + xPlanet, 130 + x + xPlanet, 130 + x + xPlanet, 70 + x + xPlanet, 70 + x + xPlanet, 80 + x + xPlanet, 80 + x + xPlanet};
            int[] yRocket = {515 - y + yPlanet, 540 - y + yPlanet, 615 - y + yPlanet, 625 - y + yPlanet, 650 - y + yPlanet, 650 - y + yPlanet, 625 - y + yPlanet, 615 - y + yPlanet, 540 - y + yPlanet};

            int[] xWindow = {95 + x + xPlanet, 105 + x + xPlanet, 110 + x + xPlanet, 90 + x + xPlanet};
            int[] yWindow = {545 - y + yPlanet, 545 - y + yPlanet, 570 - y + yPlanet, 570 - y + yPlanet};

            int[] xFlame = {80 + x + xPlanet, 70 + x + xPlanet, 75 + x + xPlanet, 80 + x + xPlanet, 90 + x + xPlanet, 100 + x + xPlanet, 110 + x + xPlanet, 115 + x + xPlanet, 120 + x + xPlanet, 130 + x + xPlanet, 120 + x + xPlanet};
            int[] yFlame = {655 - y + yPlanet, 670 - y + yPlanet, 665 - y + yPlanet, 690 - y + yPlanet, 670 - y + yPlanet, 675 - y + yPlanet, 660 - y + yPlanet, 685 - y + yPlanet, 660 - y + yPlanet, 675 - y + yPlanet, 655 - y + yPlanet};

            int[] xName = {96 + x, 101 + x, 101 + x, 95 + x, 95 + x, 104 + x, 104 + x, 96 + x};
            int[] yName = {596 - y + yPlanet, 596 - y + yPlanet, 590 - y + yPlanet, 590 - y + yPlanet, 604 - y + yPlanet, 604 - y + yPlanet, 597 - y + yPlanet, 597 - y + yPlanet};
            rocket.setColor(new Color(64, 64, 64));
            rocket.fillPolygon(xRocket, yRocket, xRocket.length);
            rocket.setColor(new Color(255, 196, 0));
            rocket.drawPolygon(xRocket, yRocket, xRocket.length);

            rocket.setColor(new Color(168, 177, 98));
            rocket.fillPolygon(xWindow, yWindow, xWindow.length);

            rocket.setColor(new Color(128, 234, 255));
            rocket.fillPolygon(xFlame, yFlame, xFlame.length);
            rocket.setColor(new Color(67, 126, 138));
            rocket.drawPolyline(xFlame, yFlame, xFlame.length);

            for (double i = 0; i < 3; i++) {
                int xStar = (int) (70 * Math.random());
                int yStar = (int) (60 * Math.random());
                int starWidth = (int) (5 * Math.random());
                int starHeight = (int) (5 * Math.random());
                g.setColor(Color.BLUE);
                g.fillOval(xStar + x + 65, yStar - y + 610, starWidth, starHeight);
                g.setColor(Color.YELLOW);
                g.drawOval(xStar + x + 65, yStar - y + 610, starWidth, starHeight);
            }

            rocket.setColor(Color.YELLOW);
            rocket.drawPolyline(xName, yName, xName.length);

            rocket.dispose();
        }
    }
}