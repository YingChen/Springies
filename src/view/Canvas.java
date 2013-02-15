package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.Timer;
import forces.ForceNames;
import forces.Gravity;
import simulation.Factory;
import simulation.Model;


/**
 * Creates an area of the screen in which the game will be drawn that supports:
 * <UL>
 * <LI>animation via the Timer
 * <LI>mouse input via the MouseListener and MouseMotionListener
 * <LI>keyboard input via the KeyListener
 * </UL>
 * 
 * @author Robert C Duvall
 */
public class Canvas extends JComponent {
    // default serialization ID
    private static final long serialVersionUID = 1L;
    // animate 25 times per second if possible
    public static final int FRAMES_PER_SECOND = 25;
    // better way to think about timed events (in milliseconds)
    public static final int ONE_SECOND = 1000;
    public static final int STEP_SIZE = 10;
    public static final int DEFAULT_DELAY = ONE_SECOND / FRAMES_PER_SECOND;
    // only one so that it maintains user's preferences
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    // input state
    public static final int NO_KEY_PRESSED = -1;
    public static final Point NO_MOUSE_PRESSED = null;

    // drives the animation
    private Timer myTimer;
    // game to be animated
    private Model mySimulation;
    // input state
    private int myLastKeyPressed;
    private Point myLastMousePosition;
    private Set<Integer> myKeys;

    private Factory myFactory;

    /**
     * Create a panel so that it knows its size
     */
    public Canvas (Dimension size) {
        // set size (a bit of a pain)
        setPreferredSize(size);
        setSize(size);
        // prepare to receive input
        setFocusable(true);
        requestFocus();
        setInputListeners();
        myFactory = new Factory();
    }

    /**
     * Paint the contents of the canvas.
     * 
     * Never called by you directly, instead called by Java runtime
     * when area of screen covered by this container needs to be
     * displayed (i.e., creation, uncovering, change in status)
     * 
     * @param pen used to paint shape on the screen
     */
    @Override
    public void paintComponent (Graphics pen) {
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, getSize().width, getSize().height);
        // first time needs to be special cased :(
        if (mySimulation != null) {
            mySimulation.paint((Graphics2D) pen);
        }
    }

    /**
     * Returns last key pressed by the user or -1 if nothing is pressed.
     */
    public int getLastKeyPressed () {
        return myLastKeyPressed;
    }

    /**
     * Returns all keys currently pressed by the user.
     */
    public Collection<Integer> getKeysPressed () {
        return Collections.unmodifiableSet(myKeys);
    }

    /**
     * Returns last position of the mouse in the canvas.
     */
    public Point getLastMousePosition () {
        return myLastMousePosition;
    }

    /**
     * Start the animation.
     */
    public void start () {
        // create a timer to animate the canvas
        myTimer = new Timer(DEFAULT_DELAY,
                            new ActionListener() {
                                public void actionPerformed (ActionEvent e) {
                                    step();
                                }
                            });
        // start animation
        mySimulation = new Model(this);
        loadModel();
        myTimer.start();
    }

    /**
     * Stop the animation.
     */
    public void stop () {
        myTimer.stop();
    }

    /**
     * Take one step in the animation.
     */
    public void step () {
        mySimulation.update((double) FRAMES_PER_SECOND / ONE_SECOND);
        // indirectly causes paint to be called
        repaint();
    }

    /**
     * Create listeners that will update state based on user input.
     */
    private void setInputListeners () {
        // initialize input state
        myLastKeyPressed = NO_KEY_PRESSED;
        myKeys = new TreeSet<Integer>();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed (KeyEvent e) {
                myLastKeyPressed = e.getKeyCode();
                if (myLastKeyPressed == KeyEvent.VK_N) {
                    addObjects();
                } else if (myLastKeyPressed == KeyEvent.VK_C) {
                    clearAll();
                } else if (myLastKeyPressed == KeyEvent.VK_G) {
                    mySimulation.toggleForceByName(ForceNames.GRAVITY);
                } else if (myLastKeyPressed == KeyEvent.VK_V) {
                    mySimulation.toggleForceByName(ForceNames.VISCOSITY);
                } else if (myLastKeyPressed == KeyEvent.VK_M) {
                    mySimulation.toggleForceByName(ForceNames.CENTER_OF_MASS);
                } else if (myLastKeyPressed == KeyEvent.VK_1) {
                    mySimulation.toggleForceByName("1");
                } else if (myLastKeyPressed == KeyEvent.VK_2) {
                    mySimulation.toggleForceByName("2");
                } else if (myLastKeyPressed == KeyEvent.VK_3) {
                    mySimulation.toggleForceByName("3");
                } else if (myLastKeyPressed == KeyEvent.VK_4) {
                    mySimulation.toggleForceByName("4");
                } else if (myLastKeyPressed == KeyEvent.VK_UP) {
                    mySimulation.incrementSize(STEP_SIZE);
                } else if (myLastKeyPressed == KeyEvent.VK_DOWN) {
                    mySimulation.decrementSize(STEP_SIZE);
                }
                    myKeys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased (KeyEvent e) {
                myLastKeyPressed = NO_KEY_PRESSED;
                myKeys.remove((Integer) e.getKeyCode());
            }
        });
        myLastMousePosition = NO_MOUSE_PRESSED;
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged (MouseEvent e) {
                myLastMousePosition = e.getPoint();
                mySimulation.dragTempSpring(myLastMousePosition);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed (MouseEvent e) {
            	System.out.println("mouse pressed");
                myLastMousePosition = e.getPoint();
                mySimulation.createTempSpring(myLastMousePosition);
            }

            @Override
            public void mouseReleased (MouseEvent e) {
            	System.out.println("mouse released");
                myLastMousePosition = NO_MOUSE_PRESSED;
                mySimulation.removeTempObjects();
            }
        });
    }

    // load model from file chosen by user
    private void loadModel () {
        addObjects();
        addObjects();
    }

    private void addObjects () {
        int response = INPUT_CHOOSER.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            myFactory.loadModel(mySimulation, INPUT_CHOOSER.getSelectedFile());
        }
    }

    private void clearAll () {
        mySimulation.clearAll();
    }

}
