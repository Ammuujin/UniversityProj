public class CardGameAssignment {
    static Card[] deck;// Card 20장을 저장하기 위한 배열
    static int count; // deck에 남은 카드의 수
    public static void main(String[] args) {
        deck = new Card[20];
        count = 20;
        Card dealer;
        Card player;
        int result;
        initialize();// Card 20장을 생성하여 CardStack에 저장하는 method
        // 게임을 반복적으로 실행함
        do {
            player = deal(); // Card의 배열에서 한장을 뽑는다.
            dealer = deal(); // Card의 배열에서 한장을 뽑는다.
            if(player==null || dealer==null){
                System.out.println("No more cards...");
                break;
            }
            System.out.println(toString(player));
            System.out.println(toString(dealer));
            result = compare(player, dealer);
            if(result > 0){
                System.out.println("Winner Player: "+toString(player));
            }
            else if(result < 0){
                System.out.println("Winner Dealer: "+toString(dealer));
            }
            else{
                System.out.println("Tie!");
            }
        } while (result < 1);
    }
    static void initialize(){
        int suit;
        int rank;
        for(int i=0; i<20; i++){
            Card c = new Card();
            suit = (int)(Math.random()*4)+1;
            rank = (int)(Math.random()*13)+1;
            c.suit = suitInString(suit);
            c.rank = rank;
            deck[i] = c;
        }
    }
    static Card deal(){
        Card c = null;
        if(count>0){
            c = deck[count-1];
            deck[count-1] = null;
            count--;
        }
        return c;
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