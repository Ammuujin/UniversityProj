//Code
import java.util.Scanner;
import java.util.Random;

public class CardGameAssignment {
    static Card[] deck;// Card 20장을 저장하기 위한 배열
    static int count; // deck에 남은 카드의 수
    public static void main(String[] args) {
        deck = new Card[52];
        count = 52;
        int result=0;
        int total = 0;//game counter
        initialize();//52cards를 deck에 저장
        Scanner in = new Scanner(System.in);
        do {
             //한 명의 딜러와 N(1 <= N <= 5)) 명의 플레이어가 카드 게임을 한다. 
            int N = 0;
            System.out.println("Games started...");
            System.out.println("Enter the number of players: ");
            //게임 시작시 N값과 각 플레이어의 이름과 게임머니 초기값을 입력한다.
            N = in.nextInt();
            player[] p = new player[N];
            for(int i=0; i<N; i++){
                p[i] = new player();
                System.out.println("Enter the player-"+(i+1)+" (name, game money): ");
                p[i].name = in.next();
                p[i].money = in.nextInt();
            }
            //카드 덱으로부터 딜러와 각 플레이어들에게 각각 2장씩의 카드(핸드-hand라고 부름)를 나누어 준 후 딜러와 각 플레이어의 핸드를 비교한다(플레이어들 사이에는 서로 비교하지 않음).
            Card[] dealer = new Card[2];
            Card[][] player = new Card[N][2];
            for(int i=0; i<2; i++){
                dealer[i] = deal();
                for(int j=0; j<N; j++){
                    player[j][i] = deal();
                }
            }
            //매 라운드 마다 딜러가 플레이어에게 지면 딜러는 그 플레이어에게 베팅금액을 지불하고 딜러가 플레이어를 이기면 그 플레이어는 딜러에게 베팅금액만큼 지불한다. 비기면 주고 받는 돈은 없다.
            int bet = 0;
            System.out.println("Enter the betting amount: ");
            bet = in.nextInt();
            //카드 덱에 카드가 없거나 플레이어 중 한 명이라도 게임머니가 베팅금액보다 적으면 게임을 종료한다.
            //게임은 대화식으로 진행한다. 즉, 한 라운드가 끝나면 다음 라운드를 진행할 것인지를 묻는다. 
            //매 라운드가 끝난 후 딜러 및 각 플레이어의 핸드를 보여준 후 각 플레이어의 승패 정보 및 게임머니를 보여준다. 
            //게임이 종료되면 게임의 총 라운드 수와 각 플레이어의 정보(이름, 이긴 횟수 및 진 횟수, 비긴 횟수, 최종 게임머니)를 출력한다.
            for(int i=0; i<N; i++){
                System.out.println(p[i].name+" : ("+toString(player[i][0])+", "+toString(player[i][1])+")");
            }
            System.out.println("Dealer : ("+toString(dealer[0])+", "+toString(dealer[1])+")");
            for(int i=0; i<N; i++){
                result = compare(player[i][0], player[i][1]);
                if(result == 1){
                    System.out.println(p[i].name+" won $"+bet+" ($"+(p[i].money+bet)+")");
                    p[i].money += bet;
                }
                else if(result == -1){
                    System.out.println(p[i].name+" lost $"+bet+" ($"+(p[i].money-bet)+")");
                    p[i].money -= bet;
                }
                else if(result == 0){
                    System.out.println(p[i].name+" tied ($"+p[i].money+")");
                }
            }
            //게임이 종료되면 게임의 총 라운드 수와 각 플레이어의 정보(이름, 이긴 횟수 및 진 횟수, 비긴 횟수, 최종 게임머니)를 출력한다.
            System.out.println("Continue? y");
            System.out.println("Games ended...");
            System.out.println("Total games: ");
        } while (result < 1);
    }//end of main

    //Player class
    static class player{
        String name;
        int money;
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
        Card c = new Card();
        int suit;
        int rank;
        suit = (int)(Math.random()*4)+1;
        rank = (int)(Math.random()*13)+1;
        c.suit = suitInString(suit);
        c.rank = rank;
        return c;
    }
}