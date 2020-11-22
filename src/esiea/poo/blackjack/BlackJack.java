package esiea.poo.blackjack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class BlackJack {
    public static final String SCORE_FILENAME = "C:\\blackjack\\score.txt";
    private Deck deck;
    private Hand playerHand;
    private Hand bankHand;
    private boolean gameFinished;
    private int playerScore;
    private int bankScore;


    public BlackJack() {
        playerHand = new Hand();
        bankHand = new Hand();
        gameFinished = false;
        deck = new Deck(4);
        try {
            this.reset();
        } catch(EmptyDeckException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void reset() throws  EmptyDeckException {
        this.playerHand.clear();
        this.bankHand.clear();
        bankHand.add(deck.draw());
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        this.readScore();
        System.out.println(String.format("Previous score [player : %1$d, bank: %2$d]", playerScore, bankScore));
    }

    public String getPlayerHandString() {
        return this.playerHand != null ? this.playerHand.toString() : "null hand!";
    }

    public String getBankHandString() {
        return this.bankHand != null ? this.bankHand.toString() : "null hand !";
    }

    public int getPlayerBest() {
        return this.playerHand != null ? this.playerHand.best() : 0;
    }

    public int getBankBest() {
        return this.bankHand != null ? this.bankHand.best() : 0;
    }

    public boolean isPlayerWinner() {
        boolean cond1 = this.getBankBest() > 21 && this.getPlayerBest() <= 21
                || (this.getPlayerBest() <= 21 && this.getPlayerBest() > this.getBankBest());
        return cond1 && gameFinished;
    }

    public boolean isBankWinner() {
        boolean cond1 =  this.getPlayerBest() > 21 && this.getBankBest() <= 21
                || (this.getBankBest() <= 21 && this.getBankBest() > this.getPlayerBest());
        return cond1 && gameFinished;
    }

    public boolean isGameFinished() {
        return this.gameFinished;
    }

    public void playerDrawAnotherCard() throws EmptyDeckException {
        this.playerHand.add(deck.draw());


        if (this.playerHand.best() > 21) {
            this.gameFinished = true;
        }
    }
    public void bankLastTour() throws EmptyDeckException {
        while(!this.gameFinished ) {
            this.bankHand.add(deck.draw());
            System.out.println("The bank draw card. New hand : "+this.getBankHandString() );
            if (bankHand.best() > 21 || this.bankHand.best() > this.playerHand.best()) {
                this.gameFinished = true;
            }
        }
    }

    public void readScore() {
        File file = new File(SCORE_FILENAME);
        File dir = new File("C:\\blackjack");
        try {
            if (!file.exists()) {
                playerScore = 0;
                bankScore = 0;
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdir();
                }
                return;
            } else {
               List<String> lines = Files.readAllLines(Paths.get(SCORE_FILENAME), StandardCharsets.UTF_8);
               if (lines.size() != 2) {
                   throw new RuntimeException("Expected only two line !");
               }
               for(int i = 0; i < 2; i++) {
                   String [] tmp = lines.get(i).split(" ");
                   if(tmp.length != 2) {
                       throw new RuntimeException("Unexpected file format");

                   }
                   if (i == 0) {
                       playerScore = Integer.parseInt(tmp[1]);
                   } else {
                       bankScore = Integer.parseInt(tmp[1]);
                   }
               }
            }


        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }
    public void writeScore() {
        File file = new File(SCORE_FILENAME);
        try {
            FileWriter w = new FileWriter(file, false);
            w.write("player "+playerScore);
            w.write("\n");
            w.write("bank "+bankScore);
            w.flush();
            w.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    public void updateScores() {
        if (this.isPlayerWinner()) {
            playerScore ++;
        } else if (this.isBankWinner()) {
            bankScore++;
        }
    }
}
