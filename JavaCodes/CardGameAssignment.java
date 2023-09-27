import java.util.Scanner;

public class CardGameAssignment {
    static Card[] deck;// Card 20장을 저장하기 위한 배열
    static int count; // deck에 남은 카드의 수
    public static void main(String[] args) {
        //Declaration
        deck = new Card[52];
        count = 52;
        int result = 0;
        int totalGamesPlayed = 0;
        Scanner in = new Scanner(System.in);
        initialize();//52cards를 deck에 저장
        System.out.println("Games started...");
        //Game introduction
        System.out.println("This card is a game that plays against the dealer.");
        System.out.println("The dealer and the player are each given two cards, and the player with the higher card wins.");
        System.out.println("Good Luck!");
        System.out.println();

        //Game start from here
        //Getting the number of players 
        System.out.println("Enter the number of players: ");
        int N = 0;
        N = in.nextInt();
        if(N<1 || N>5){
            System.out.println("Invalid number of players. Enter again.");
            System.out.println("Enter the number of players: ");
            N = in.nextInt();
        }

        //Getting information of players
        player[] p = new player[N];
        for(int i=0; i<N; i++){
            p[i] = new player();
            System.out.println("Enter the player-"+(i+1)+" (name, game money): ");
            p[i].name = in.next();
            p[i].money = in.nextInt();
            if(p[i].money<0 || p[i].money==0){
                System.out.println("Invalid amount of money. Enter again.");
                System.out.println("Enter the player-"+(i+1)+" (name, game money): ");
                p[i].name = in.next();
                p[i].money = in.nextInt();
            }
        }

        //Getting bet amount
        int bet = 0;
        System.out.println("Enter the betting amount: ");
        bet = in.nextInt();
        System.out.println();

        //Running the game
        boolean gameStatus = true;
        do {
            //Checking if there are enough cards to play
            if(count<=((N+1)*2)){
                System.out.println("Not enough cards. ");
                gameStatus = false;
                break;
            }

            //Checking each player has enough money to play
            for(int i=0; i<N; i++){
                if(p[i].money<bet){
                    System.out.println(p[i].name+" has no enough money!\n");
                    gameStatus = false;
                }
            }
            
            //Checking if the game can be continued or not    
            if(gameStatus==false){
                break;
            }
            else{
                //Giving 2 random cards to dealer and each players
                Card[] dealer = new Card[2];
                Card[][] player = new Card[N][2];
                for(int i=0; i<2; i++){
                    dealer[i] = deal();
                    count--;
                    for(int j=0; j<N; j++){
                        player[j][i] = deal();
                        count--;
                    }
                }

                //Printing the cards of dealer and each players
                for(int i=0; i<N; i++){
                    System.out.println(p[i].name+" : ("+toString(player[i][0])+", "+toString(player[i][1])+")");
                }
                System.out.println("Dealer : ("+toString(dealer[0])+", "+toString(dealer[1])+")");
                System.out.println();

                //Comparing dealer's card with each players' card and printing the result
                for(int i=0; i<N; i++){
                    result = compare(dealer[0], player[i][0]);
                    if(result == 1){
                        p[i].money += bet;
                        System.out.println(p[i].name+" won $"+bet+" ($"+p[i].money+")");
                        p[i].wins++;
                    }
                    else if(result == -1){
                        p[i].money -= bet;
                        System.out.println(p[i].name+" lost $"+bet+" ($"+(p[i].money)+")");
                        p[i].losses++;
                    }
                    else if(result == 0){
                        System.out.println(p[i].name+" tied ($"+p[i].money+")");
                        p[i].ties++;
                    }
                    totalGamesPlayed++;
                }
                System.out.println();

                //Asking if the player wants to continue or not
                while(gameStatus){
                    System.out.println("Do you want to continue? Yes or No:");
                    String s = in.next().toLowerCase(); // convert input to lowercase
                    if(s.equals("yes") || s.equals("y")){ 
                        System.out.println();
                        result = 0;
                        break;
                    }else if(s.equals("no") || s.equals("n")){
                        System.out.println();
                        result = 1;
                        gameStatus = false;
                    }else {
                        System.out.println("Invalid input! Enter again.\n");
                    }
                }
            }
        } while (result < 1);
        //Game end
        System.out.println("Game ended...");
        System.out.println("Total games: "+totalGamesPlayed);
        for(int i=0; i<N; i++){
            System.out.println(p[i].name+": " + p[i].wins+", " + p[i].losses + ", " + p[i].ties);
        }
    }//end of main

    //Classes:
    //Player class
    static class player{
        String name;
        int money;
        int wins;
        int losses;
        int ties;
    }
    //Card class
    static class Card{
        String suit;
        int rank;
    }

    //Methods:
    // initialize() method
    static void initialize() {
        int suit;
        int rank;
        for (int i = 0; i < 20; i++) {
            Card c;
            do {
                suit = (int)(Math.random() * 4) + 1;
                rank = (int)(Math.random() * 13) + 1;
                c = new Card();
                c.suit = suitInString(suit);
                c.rank = rank;
            } while (isDuplicate(c));
            deck[i] = c;
        }
    }
    
    // isDuplicate() method
    static boolean isDuplicate(Card card) {
        for (int i = 0; i < count; i++) {
            if (deck[i] != null && deck[i].suit.equals(card.suit) && deck[i].rank == card.rank) {
                return true; // Found a duplicate
            }
        }
        return false; // Not a duplicate
    }
    
    // compare() method 
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

    //deal() method
    static Card deal(){
        Card c = new Card();
        int suit;
        int rank;
        suit = (int)(Math.random()*4)+1;
        rank = (int)(Math.random()*13)+1;
        c.suit = suitInString(suit);
        c.rank = rank;
        return c;
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
}//end of code