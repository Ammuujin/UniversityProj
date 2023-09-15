package JavaCodes;
// 파일명: CardGame.java import java.util.Scanner;
import java.util.Scanner;
// Class 이름과 파일명을 일치시킴.
// main()을 포함한 class는 class name 앞에 public을 반드시 붙인다.
public class CardGame {
    public static void main(String[] args) {
        Card c1 = new Card(); // Card for Player
        Card c2 = new Card(); // Card for Computer
        int rank, suit;
        int result = 0;
        Scanner in = new Scanner(System.in);
        // 게임을 반복적으로 실행함
        do {
            System.out.print("Input your card, suit(1-4) and rank(1-13)! : ");
            suit = in.nextInt();
            rank = in.nextInt();
            if (suit < 1 || suit > 4) {
                System.out.println("Enter suit and rank again!");
                continue;
            }
            c1.suit = suitInString(suit);
            c2.suit = suitInString(suit);
            if (rank < 1 || rank > 13) {
                System.out.println("Enter suit and rank again!");
                continue;
            }
            c1.rank = rank;
            suit = (int) (Math.random() * 4) + 1; // generate a random number (1~4)
            rank = (int) (Math.random() * 13) + 1; // generate a rendom number (1~13)
            c2.rank = rank;
            System.out.println(toString(c1));
            System.out.println(toString(c2));
            result = compare(c1, c2);
            printResult(result);
        } while (result < 1);
        in.close();
    }
    // print results
    static void printResult(int result) {
        if (result > 0) {
            System.out.print("Winner: ");
            System.out.println("Player");
        } else if (result < 0) {
            System.out.print("Winner: ");
            System.out.println("Computer");
        } else {
            System.out.println("Tie");
        }
    }
    //Card class
    static class Card{
        String suit;
        int rank;
    }
    // suitInString() method
    static String suitInString(int suit){
        String s = "";
        switch(suit){
            case 1: s = "Spade"; break;
            case 2: s = "Diamond"; break;
            case 3: s = "Heart"; break;
            case 4: s = "Club"; break;
        }
        return s;
    }
    // toString() method
    static String toString(Card c)
    {
        String s = "";
        switch(c.rank){
            case 1: s = "Ace"; break;
            case 11: s = "Jack"; break;
            case 12: s = "Queen"; break;
            case 13: s = "King"; break;
            default: s = Integer.toString(c.rank); break;
        }
        return s + " of " + c.suit;
    }
    // compare() method 
    //Spade > Diamond > Heart > Club
    static int compare(Card c1, Card c2){
        int result = 0;
        if(c1.rank > c2.rank){
            result = 1;
        }
        else if(c1.rank < c2.rank){
            result = -1;
        }
        else if (c1.rank == c2.rank && c1.suit!=c2.suit){
            if(c1.suit=="Spade" && (c2.suit=="Diamond" || c2.suit=="Heart" || c2.suit=="Club")){
                result = 1;
            }
            else if(c2.suit=="Spade" && (c1.suit=="Diamond" || c1.suit=="Heart" || c1.suit=="Club")){
                result = -1;
            }
            else if(c1.suit=="Diamond" && (c2.suit=="Heart" || c2.suit=="Club")){
                result = 1;
            }
            else if(c2.suit=="Diamond" && (c1.suit=="Heart" || c1.suit=="Club")){
                result = -1;
            }
            else if(c1.suit=="Heart" && c2.suit=="Club"){
                result = 1;
            }
            else if(c2.suit=="Heart" && c1.suit=="Club"){
                result = -1;
            }
        }
        else if(c1.rank == c2.rank && c1.suit==c2.suit){
            result = 0;
        }
        return result;
    }
} // end of class CardGame