//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 - Matching Game
// Course:   CS 300 Fall 2020
//
// Author:   Matthew Smith
// Email:    mjsmith44@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * A memory matching card game with 12 cards and 6 matches. Press N-key to create a game in game window.
 *
 * @author Matthew Smith
 */
public class MemoryGame {

    private final static String CONGRA_MSG = "CONGRATULATIONS! YOU WON!";
    private final static String NOT_MATCHED = "CARDS NOT MATCHED. Try again!";
    private final static String MATCHED = "CARDS MATCHED! Good Job!";
    private final static float[][] CARDS_COORDINATES =
            new float[][] {{170, 170}, {324, 170}, {478, 170}, {632, 170},
                    {170, 324}, {324, 324}, {478, 324}, {632, 324},
                    {170, 478}, {324, 478}, {478, 478}, {632, 478}};
    private final static String[] CARD_IMAGES_NAMES = new String[] {"ball.png", "redFlower.png",
            "yellowFlower.png", "apple.png", "peach.png", "shark.png"};
    private static PApplet processing;
    private static Card[] cards;
    private static PImage[] images;
    private static Card selectedCard1;
    private static Card selectedCard2;
    private static boolean winner;
    private static int matchedCardsCount;
    private static String message;

    /**
     * Main method. Starts game application. No support for command line arguments.
     *
     * @param args
     */
    public static void main(String[] args) {
        Utility.startApplication();
    }

    /**
     * Defines the initial environment properties of this game and starts game window.
     */
    public static void setup(PApplet processing) {
        MemoryGame.processing = processing;

        MemoryGame.images = new PImage[6];
        MemoryGame.images[0] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[0]);
        MemoryGame.images[1] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[1]);
        MemoryGame.images[2] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[2]);
        MemoryGame.images[3] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[3]);
        MemoryGame.images[4] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[4]);
        MemoryGame.images[5] = MemoryGame.processing.loadImage("images" + File.separator +
                CARD_IMAGES_NAMES[5]);

        startNewGame();
    }

    /**
     * Initializes the game values, card contents, and card order.
     */
    public static void startNewGame() {
        MemoryGame.selectedCard1 = null;
        MemoryGame.selectedCard2 = null;
        MemoryGame.matchedCardsCount = 0;
        MemoryGame.winner = false;
        MemoryGame.message = "";
        MemoryGame.cards = new Card[CARDS_COORDINATES.length];

        // determine order of cards and populate class cards array
        int[] shuffledCardIds = Utility.shuffleCards(MemoryGame.cards.length);
        for (int i = 0; i < MemoryGame.cards.length; i++) {
            MemoryGame.cards[i] = new Card(images[shuffledCardIds[i]], CARDS_COORDINATES[i][0],
                    CARDS_COORDINATES[i][1]);
        }
    }

    /**
     * Callback method called when user presses the N-Key. Creates new game.
     */
    public static void keyPressed(){
        if (MemoryGame.processing.key == 'n' || MemoryGame.processing.key == 'N') {
            startNewGame();
        }
    }

    /**
     * Callback method that continuously draws this games window display and cards.
     */
    public static void draw(){
        MemoryGame.processing.background(245, 255, 250);

        for (Card card: MemoryGame.cards) {
            card.draw();
        }

        displayMessage(MemoryGame.message);
    }

    /**
     * Displays a given message to the the games window.
     *
     * @param message to be displayed to the display window
     */
    public static void displayMessage(String message) {
        MemoryGame.processing.fill(0);
        MemoryGame.processing.textSize(20);
        MemoryGame.processing.text(message, MemoryGame.processing.width / 2, 50);
        MemoryGame.processing.textSize(12);
    }

    /**
     * Checks whether the mouse is over a given Card
     *
     * @return true if the mouse is over the storage list, false otherwise
     */
    public static boolean isMouseOver(Card card) {
        if (MemoryGame.processing.mouseX <= (card.getX() + (card.getWidth() / 2)) &&
                MemoryGame.processing.mouseX >= (card.getX() - (card.getWidth() / 2)) &&
                MemoryGame.processing.mouseY <= (card.getY() + (card.getHeight() / 2)) &&
                MemoryGame.processing.mouseY >= (card.getY() - (card.getHeight() / 2)))
        {
            return true;
        }

        return false;
    }

    /**
     * Callback method called each time the user presses the mouse. Checks for card matches and the win condition.
     */
    public static void mousePressed() {

        // arriving here with both cards not null means there was no match... so nullify them
        if (MemoryGame.selectedCard1 != null && MemoryGame.selectedCard2 != null) {
            MemoryGame.selectedCard1.setVisible(false);
            MemoryGame.selectedCard1.deselect();
            MemoryGame.selectedCard2.setVisible(false);
            MemoryGame.selectedCard2.deselect();
            MemoryGame.selectedCard1 = null;
            MemoryGame.selectedCard2 = null;
            MemoryGame.message = "";
        }

        // normal game logic begins
        for (Card card : MemoryGame.cards) {
            if (isMouseOver(card) && !card.isMatched()) {
                MemoryGame.message = "";
                if (MemoryGame.selectedCard1 == null) {
                    MemoryGame.selectedCard1 = card;
                } else if (!card.equals(MemoryGame.selectedCard1)){
                    MemoryGame.selectedCard2 = card;
                }

                card.setVisible(true);
                card.select();
            }
        }

        if (MemoryGame.selectedCard1 != null && MemoryGame.selectedCard2 != null) {
            if (matchingCards(MemoryGame.selectedCard1, MemoryGame.selectedCard2)) {
                MemoryGame.matchedCardsCount += 2;
                if (MemoryGame.matchedCardsCount == MemoryGame.cards.length) {
                    MemoryGame.winner = true;
                }

                if (winner) {
                    MemoryGame.message = MemoryGame.CONGRA_MSG;
                } else {
                    MemoryGame.message = MemoryGame.MATCHED;
                }

                MemoryGame.selectedCard1.deselect();
                MemoryGame.selectedCard2.deselect();
                MemoryGame.selectedCard1.setMatched(true);
                MemoryGame.selectedCard2.setMatched(true);
                MemoryGame.selectedCard1 = null;
                MemoryGame.selectedCard2 = null;
            } else {
                MemoryGame.message = MemoryGame.NOT_MATCHED;
            }
        }

    }

    /**
     * Checks whether two cards match or not
     *
     * @param card1 reference to the first card
     * @param card2 reference to the second card
     * @return true if card1 and card2 image references are the same, false otherwise
     */
    public static boolean matchingCards(Card card1, Card card2) {
        if (card1.getImage().equals(card2.getImage()))
            return true;

        return false;
    }
}
