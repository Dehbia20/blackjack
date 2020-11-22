package esiea.poo.blackjack;

import java.util.Scanner;

public class BlackJackConsole {

    public BlackJackConsole() {


        System.out.println("Welcome to the BlackJack table. Let's play !");
        BlackJack blackJack = new BlackJack();
        System.out.println(format(false, blackJack.getBankHandString()));
        System.out.println(format(true, blackJack.getPlayerHandString()));
        Scanner sc = new Scanner(System.in);
        boolean askPlayer = true;
        try {
            while(askPlayer && !blackJack.isGameFinished()) {
                System.out.println("Do you want another card ? [y,n]");
                String response = sc.next();
                if (!"y".equalsIgnoreCase(response) && !"n".equalsIgnoreCase(response)) {
                    System.out.println("You can only choose between y,n ! give it another try");
                } else if ("y".equalsIgnoreCase(response)) {
                    blackJack.playerDrawAnotherCard();
                    System.out.println(String.format("Your new hand %1$s", blackJack.getPlayerHandString()));
                    System.out.println("game finished " + blackJack.isGameFinished());
                } else {
                    blackJack.bankLastTour();
                }
            }
            handleRes(blackJack);

        } catch (EmptyDeckException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

    }

    private void handleRes(BlackJack bj) {
        System.out.println("Player best : " + bj.getPlayerBest());
        System.out.println("Bank best : " + bj.getBankBest());
        if (bj.isPlayerWinner()) {
            System.out.println("Player wins !");
        } else if (bj.isBankWinner()) {
            System.out.println("bank wins !");
        } else {
            System.out.println("draw !");
        }
        bj.updateScores();
        bj.writeScore();
    }
    private String format(boolean player, String h) {
       String role =  player ? "You" : "The bank";
       return String.format("%1$s draw : %2$s", role, h);
    }
    public static void main(String args []) {
        new BlackJackConsole();
    }

}
