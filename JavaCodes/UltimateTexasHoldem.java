import java.util.Scanner;

public class UltimateTexasHoldem {
    static Card[] deck;// deck of cards
    static int count; // number of cards are left in the deck
    public static void main(String[] args) {
        //Declaration
        deck = new Card[52];
        count = 0;
        int result = 0;
        int totalGamesPlayed = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Ultimate Texas Holdem!");
        //Game introduction
        System.out.println("This is a card game between the dealer and the players.");
        System.out.println("The dealer and the player are each given two cards, and the player with the higher card wins.");
        System.out.println("Good Luck!");
        System.out.println();

        //Game start from here
        //Getting the number of players 
        System.out.println("Enter the number of players: ");
        int N = 0;
        boolean validInput = false;
        do {
            N = in.nextInt();
            if(N<1 || N>10){
                System.out.println("Invalid number of players. Enter again.");
                System.out.println("Enter the number of players: ");
            } else {
                validInput = true;
            }
        } while (!validInput);

        //Getting information of players
        player[] p = new player[N];
        for(int i=0; i<N; i++){
            p[i] = new player();
            System.out.println("Enter the player-"+(i+1)+" (name, game money): ");
            p[i].name = in.next();
            validInput = false;
            do {
                p[i].money = in.nextInt();
                if(p[i].money<0 || p[i].money==0){
                    System.out.println("Invalid amount of money. Enter again.");
                    System.out.println("Enter the player-"+(i+1)+" (name, game money): ");
                } else {
                    validInput = true;
                }
            } while (!validInput);
        }

        //Getting bet amount
        int bet = 0;
        System.out.println("Enter the betting amount: ");
        bet = in.nextInt();
        System.out.println();

        //Running the game
        boolean gameStatus = true;
        do {
            //52 different cards are initialized in the deck
            count = 52;
            initialize();   

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

                //Showing 5 random card on the table
                Card[] table = new Card[5];
                for(int i=0; i<5; i++){
                    table[i] = deal();
                    count--;
                }

                //Printing the cards on the table
                System.out.println("Table cards: ");
                for(int i=0; i<5; i++){
                    System.out.println(toString(table[i]));
                }
                System.out.println();

                //Printing the cards of dealer and each players
                for(int i=0; i<N; i++){
                    System.out.println(p[i].name+" : ("+toString(player[i][0])+", "+toString(player[i][1])+")");
                }
                System.out.println("Dealer : ("+toString(dealer[0])+", "+toString(dealer[1])+")");
                System.out.println();

                //Checking dealer's cards are flush or not
                boolean dealerFlush = checkDealerCardFlush(dealer, table);

                //Checking each player's cards are flush or not
                boolean[] playerFlush = new boolean[N];
                for(int i=0; i<N; i++){
                    playerFlush[i] = checkPlayerCardFlush(player[i], table);
                }

                //Comparing dealer's cards with each players' cards and printing the result
                for(int i=0; i<N; i++){
                    result = compare(dealer, player[i]);
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
                }
                //Counting the total games played
                totalGamesPlayed++;
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
        //End part of the game
        System.out.println("Game ended...");
        System.out.println("Total games: "+totalGamesPlayed);
        for(int i=0; i<N; i++){
            System.out.println(p[i].name+": " + p[i].wins+", " + p[i].losses + ", " + p[i].ties+", $"+ p[i].money);
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
    
    //Flush:
    //checking dealer's cards are flush or not
    static boolean checkDealerCardFlush(Card[] dealer, Card[] table){
        int count = 0;
        //checking dealer's cards
        if(dealer[0].suit.equals(dealer[1].suit)){
            for(int j=0; j<5; j++){
                if(dealer[0].suit.equals(table[j].suit)){
                count++;
                }
            }
            if(count==3){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(dealer[i].suit.equals(table[j].suit)){
                        count++;
                    }
                    if(count==4){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //checking player's cards are flush or not
    static boolean checkPlayerCardFlush(Card[] player, Card[] table){
        int count = 0;
        if(player[0].suit.equals(player[1].suit)){
            for(int j=0; j<5; j++){
                if(player[0].suit.equals(table[j].suit)){
                count++;
                }
            }
            if(count==3){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(player[i].suit.equals(table[j].suit)){
                        count++;
                    }
                    if(count==4){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //Four of a kind:
    //checking dealer's cards are 4 of kind or not
    static boolean checkDealerCardFourKind(Card[] dealer, Card[] table){
        int count = 0;
        //checking dealer's cards
        if(dealer[0].rank==dealer[1].rank){
            for(int j=0; j<5; j++){
                if(dealer[0].rank==table[j].rank){
                count++;
                }
            }
            if(count==2){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(dealer[i].rank==table[j].rank){
                        count++;
                    }
                    if(count==3){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //checking player's cards are 4 of kind or not
    static boolean checkPlayerCardFourKind(Card[] player, Card[] table){
        int count = 0;
        if(player[0].rank==player[1].rank){
            for(int j=0; j<5; j++){
                if(player[0].rank==table[j].rank){
                count++;
                }
            }
            if(count==2){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(player[i].rank==table[j].rank){
                        count++;
                    }
                    if(count==3){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //Three of a kind:
    //checking dealer's cards are 3 of kind or not
    static boolean checkDealerCardThreeKind(Card[] dealer, Card[] table){
        int count = 0;
        //checking dealer's cards
        if(dealer[0].rank==dealer[1].rank){
            for(int j=0; j<5; j++){
                if(dealer[0].rank==table[j].rank){
                count++;
                }
            }
            if(count==1){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(dealer[i].rank==table[j].rank){
                        count++;
                    }
                    if(count==2){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //checking player's cards are 3 of kind or not
    static boolean checkPlayerCardThreeKind(Card[] player, Card[] table){
        int count = 0;
        if(player[0].rank==player[1].rank){
            for(int j=0; j<5; j++){
                if(player[0].rank==table[j].rank){
                count++;
                }
            }
            if(count==1){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(player[i].rank==table[j].rank){
                        count++;
                    }
                    if(count==2){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //Two pair:
    //checking dealer's cards are two pair or not
    static boolean checkDealerCardTwoPair(Card[] dealer, Card[] table){
        int count = 0;
        //checking dealer's cards
        if(dealer[0].rank==dealer[1].rank){
            for(int j=0; j<5; j++){
                if(dealer[0].rank==table[j].rank){
                count++;
                }
            }
            if(count==0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            for(int i=0; i<2; i++){
                for(int j=0; j<5; j++){
                    if(dealer[i].rank==table[j].rank){
                        count++;
                    }
                    if(count==1){
                        return true;
                    }
                }
                count = 0;
            }
            return false;
        }
    }

    //High Card:
    //compare() method
    static int compare(Card[] dealer, Card[] player){
        //check the cards are pair or not
        if((dealer[0].rank==dealer[1].rank) && (player[0].rank!=player[1].rank)){
            return -1;
        }
        else if((player[0].rank==player[1].rank) && (dealer[0].rank!=dealer[1].rank)){
            return 1;
        }
        else if((player[0].rank==player[1].rank) && (dealer[0].rank==dealer[1].rank)){
            return 0;
        }
        else {
            //finding higher, lower rank card of dealer and player
            int bigCardDealer = 0, smallCardDealer = 0;
            int bigCardPlayer = 0, smallCardPlayer = 0;
            //Dealer
            if(dealer[0].rank>dealer[1].rank){
                bigCardDealer=dealer[0].rank;
                smallCardDealer=dealer[1].rank;
            }
            else{
                bigCardDealer=dealer[1].rank;
                smallCardDealer=dealer[0].rank;
            }
            //Player
            if(player[0].rank>player[1].rank){
                bigCardPlayer=player[0].rank;
                smallCardPlayer=player[1].rank;
            }
            else{
                bigCardPlayer=player[1].rank;
                smallCardPlayer=player[0].rank;
            }
            //comparing the higher rank cards
            if(bigCardDealer>bigCardPlayer){
                return -1;
            }
            else if(bigCardDealer<bigCardPlayer){
                return 1;
            }
            else{
                //check compare the lower rank cards
                if(smallCardDealer>smallCardPlayer){
                    return -1;
                }
                else if(smallCardDealer<smallCardPlayer){
                    return 1;
                }
                else{
                    //if both card's rank are same, compare each player's suit of big card with dealer's suit of big card
                    if(player[bigCardPlayer].suit.equals("Spade")){
                        return -1;
                    }
                    else if(dealer[bigCardDealer].suit.equals("Spade")){
                        return 1;
                    }
                    else if(player[bigCardPlayer].suit.equals("Diamond")){
                        return -1;
                    }
                    else if(dealer[bigCardDealer].suit.equals("Diamond")){
                        return 1;
                    }
                    else if(player[bigCardPlayer].suit.equals("Heart")){
                        return -1;
                    }
                    else if(dealer[bigCardDealer].suit.equals("Heart")){
                        return 1;
                    }
                    else if(player[bigCardPlayer].suit.equals("Club")){
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            }
        }
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