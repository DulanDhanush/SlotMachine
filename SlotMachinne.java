import java.util.Scanner;
import java.util.Random;

public class SlotMachinne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int balance = 1000;
        int bet;
        int payout;
        String[] raw;
        String playAgain;

        System.out.println("**********************************");
        System.out.println("Welcome to the java slot machine");
        System.out.println("**********************************");

        System.out.println("Symbol:🍉🍒🔔🍋⭐");

        while (balance > 0) {

            System.out.println("Current balance is : LKR" + balance);

            System.out.print("Enter your bet: LKR");
            bet = scanner.nextInt();
            scanner.nextLine();

            if (bet > balance) {
                System.out.println("Your bet can NOT be more than your balance!");
                continue;
            } else if (bet < 0) {
                System.out.println("Your bet can NOT be zero or negative!");
                continue;
            } else {
                balance -= bet;
            }

            System.out.println("Spining...");
            raw = spinRow();
            printRaw(raw);
            payout = getpayout(raw, bet);

            if (payout > 0) {
                System.out.println("You Won!");
                balance += payout;
            } else {
                System.out.println("Sorry! You Lost this round!");
            }

            System.out.print("You want to play again(Y/N):");
            playAgain = scanner.nextLine().toUpperCase();

            if (!playAgain.equals("Y")) {
                break;
            }
        }

        System.out.println("Game Over! \n Your final balance is: LKR" + balance);

        scanner.close();
    }

    static String[] spinRow() {

        String[] symbol = { "🍉", "🍒", "🔔", "🍋", "⭐" };
        String[] row = new String[3];

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            row[i] = symbol[random.nextInt(symbol.length)];
        }
        return row;
    }

    static void printRaw(String[] row) {

        System.out.println("**************");
        System.out.println(" " + String.join(" | ", row));
        System.out.println("**************");
    }

    static int getpayout(String[] raw, int bet) {
        if (raw[0].equals(raw[1]) && raw[1].equals(raw[2])) {

            return switch (raw[0]) {

                case "🍒" -> bet * 3;
                case "⭐" -> bet * 4;
                case "🍋" -> bet * 5;
                case "🔔" -> bet * 10;
                case "🍉" -> bet * 20;
                default -> 0;

            };
        } else if (raw[0].equals(raw[1])) {

            return switch (raw[0]) {

                case "🍒" -> bet * 2;
                case "⭐" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "🍉" -> bet * 10;
                default -> 0;

            };

        } else if (raw[1].equals(raw[2])) {

            return switch (raw[1]) {

                case "🍒" -> bet * 2;
                case "⭐" -> bet * 3;
                case "🍋" -> bet * 4;
                case "🔔" -> bet * 5;
                case "🍉" -> bet * 10;
                default -> 0;

            };

        }
        return 0;
    }
}