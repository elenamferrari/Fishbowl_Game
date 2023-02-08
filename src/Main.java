//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class Main implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 2000;
    final int HEIGHT = 1500;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image storkPic;
    public Image axolotlPic;
    public Image wormPic;
    public Image background;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Astronaut axolotl1;
    private Astronaut axolotl2;
    private Astronaut worm1;
    private Astronaut worm2;
    private Astronaut worm3;
    private Astronaut worm4;
    private Astronaut worm5;
    private Astronaut stork;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        Main ex = new Main();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Main() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up

        axolotlPic = Toolkit.getDefaultToolkit().getImage("axolotl.png"); //load the picture
        axolotl1 = new Astronaut(((int)(Math.random()*1500+15)),((int)(Math.random()*1500+15)), 2, 3);
        axolotl1.dx=3;
        axolotl1.dy=4;

        axolotlPic = Toolkit.getDefaultToolkit().getImage("axolotl.png"); //load the picture
        axolotl2 = new Astronaut(((int)(Math.random()*1500+15)),((int)(Math.random()*1500+15)), 3, 2);
        axolotl2.dx=4;
        axolotl2.dy=3;

        wormPic = Toolkit.getDefaultToolkit().getImage("worm.jpg");
        worm1 = new Astronaut (((int)(Math.random()*1500+15)), ((int)(Math.random()*1500+15)), 5, 2);
        worm1.dx=6;
        worm1.dy=6;

        worm2 = new Astronaut (((int)(Math.random()*1500+15)), ((int)(Math.random()*1500+15)), 6, 4);
        worm2.dx=6;
        worm2.dy=8;

        worm3 = new Astronaut (((int)(Math.random()*1500+15)), ((int)(Math.random()*1500+15)), 10, 7);
        worm3.dx=10;
        worm3.dy=7;

        worm4 = new Astronaut (((int)(Math.random()*1500+15)), ((int)(Math.random()*1500+15)), 3, 8);
        worm4.dx=6;
        worm4.dy=8;

        worm5 = new Astronaut (((int)(Math.random()*1500+15)), ((int)(Math.random()*1500+15)), 4, 11);
        worm5.dx=5;
        worm5.dy=11;


        background = Toolkit.getDefaultToolkit().getImage("ocean.png");

        storkPic = Toolkit.getDefaultToolkit().getImage("stork.png");
            stork = new Astronaut(20,1000,20,0);
            stork.dy=30;


    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }


    public void moveThings() {
        worm1.bounce();
        worm2.bounce();
        worm3.bounce();
        worm4.bounce();
        worm5.bounce();
        axolotl1.bounce();
        axolotl2.bounce();
        crash();
        stork.wrap();
        stork.dy=30;
        stork.xpos= 400;

    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ){
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the image of the Astronaut
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        if(worm1.isAlive==false && worm2.isAlive==false && worm3.isAlive==false && worm4.isAlive==false && worm5.isAlive==false) {
            g.drawImage(storkPic, stork.xpos, stork.ypos, 300, 400, null);
        }

        if(axolotl1.isAlive==true) {
            g.drawImage(axolotlPic, axolotl1.xpos, axolotl1.ypos, 170, 120, null);
        }
        if(axolotl2.isAlive==true) {
            g.drawImage(axolotlPic, axolotl2.xpos, axolotl2.ypos, 170, 120, null);
        }
        if(worm1.isAlive==true) {
            g.drawImage(wormPic, worm1.xpos, worm1.ypos, worm1.width, worm1.height, null);
        }
        if(worm2.isAlive==true) {
            g.drawImage(wormPic, worm2.xpos, worm2.ypos, worm2.width, worm2.height, null);
        }
        if(worm3.isAlive==true) {
            g.drawImage(wormPic, worm3.xpos, worm3.ypos, worm3.width, worm3.height, null);
        }
        if(worm4.isAlive==true) {
            g.drawImage(wormPic, worm4.xpos, worm4.ypos, worm4.width, worm4.height, null);
        }
       if(worm5.isAlive==true) {
           g.drawImage(wormPic, worm5.xpos, worm5.ypos, worm5.width, worm5.height, null);
       }

        g.dispose();

        bufferStrategy.show();
    }

    public void crash() {
        if (worm1.rec.intersects(axolotl1.rec) && worm1.isAlive == true) {
            worm1.isAlive = false;
            System.out.println("yum");
        }
        if (worm2.rec.intersects(axolotl1.rec) && worm2.isAlive == true) {
            worm2.isAlive = false;
            System.out.println("yum");
        }
        if (worm3.rec.intersects(axolotl1.rec) && worm3.isAlive == true) {
            worm3.isAlive = false;
            System.out.println("yum");
        }
        if (worm4.rec.intersects(axolotl1.rec) && worm4.isAlive == true) {
            worm4.isAlive = false;
            System.out.println("yum");
        }
        if (worm5.rec.intersects(axolotl1.rec) && worm5.isAlive == true) {
            worm5.isAlive = false;
            System.out.println("yum");
        }
        if (worm1.rec.intersects(axolotl2.rec) && worm1.isAlive == true) {
            worm1.isAlive = false;
            System.out.println("yum");
        }
        if (worm2.rec.intersects(axolotl2.rec) && worm2.isAlive == true) {
            worm2.isAlive = false;
            System.out.println("yum");
        }
        if (worm3.rec.intersects(axolotl2.rec) && worm3.isAlive == true) {
            worm3.isAlive = false;
            System.out.println("yum");
        }
        if (worm4.rec.intersects(axolotl2.rec) && worm4.isAlive == true) {
            worm4.isAlive = false;
            System.out.println("yum");
        }
        if (worm5.rec.intersects(axolotl2.rec) && worm5.isAlive == true) {
            worm5.isAlive = false;
            System.out.println("yum");
        }
        if (stork.rec.intersects(axolotl1.rec) && axolotl1.isAlive == true && worm1.isAlive==false && worm2.isAlive==false && worm3.isAlive==false && worm4.isAlive==false && worm5.isAlive==false) {
            axolotl1.isAlive = false;
            System.out.println("yum");
        }
        if (stork.rec.intersects(axolotl2.rec) && axolotl2.isAlive == true && worm1.isAlive==false && worm2.isAlive==false && worm3.isAlive==false && worm4.isAlive==false && worm5.isAlive==false) {
            axolotl2.isAlive = false;
            System.out.println("yum");
        }

        if(axolotl1.isAlive==false && axolotl2.isAlive==false) {
            stork.dy=0;
            background = Toolkit.getDefaultToolkit().getImage("oceanphoto.jpg");
            System.out.println("The food chain is complete!");
        }
    }
}



//bounce, if axolotl hits worm then xyz, if worm is dead then stork appears