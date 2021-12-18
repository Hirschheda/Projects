import java.util.Scanner;
import java.util.ArrayList;
//Play the computer in a game of BlackJack
class Main
{   public static int cardTotal( ArrayList<Integer> cards)
    {
        int total=0;
        for(int i=0;i<cards.size();i++)
        {
            if(cards.get(i)%13>9)
                total+=10;
            else
                total+=cards.get(i)%13+1;
        }
        return total;
    }
    public static void print(ArrayList<Integer> cards)
    {
        String[] cardName=assign(cards);
        for(int i=0;i<cardName.length;i++)
            System.out.println(cardName[i]);
    }
    public static String[] assign (ArrayList<Integer> cards)
    {
        //find the cards
        String[] CardName=new String[cards.size()];
        int[] Suit=new int[cards.size()];
        int[] cardNumber=new int[cards.size()];
        for(int i=0;i<cards.size();i++)
        {
            Suit[i]=cards.get(i)/13;
            cardNumber[i]=cards.get(i)%13;
            if(cardNumber[i]>0 && cardNumber[i]<10)
                CardName[i]=(cardNumber[i]+1)+" of ";
            else if(cardNumber[i]==0)
                CardName[i]="Ace of ";
            else if(cardNumber[i]==10)
                CardName[i]="Jack of ";
            else if(cardNumber[i]==11)
                CardName[i]="Queen of ";
            else 
                CardName[i]="King of ";   
            
            if(Suit[i]==0)
                CardName[i]=CardName[i]+"Hearts";
            else if(Suit[i]==1)
                CardName[i]=CardName[i]+"Clubs";
            else if(Suit[i]==2)
                CardName[i]=CardName[i]+"Diamonds";
            else
                CardName[i]=CardName[i]+"Spades";
            }
            return CardName;
    }

    public static ArrayList<Integer> shuffle ()
    {
        ArrayList <Integer> cards = new ArrayList<Integer>();
        int card1=(int)(Math.random()*52);
        int card2=(int)(Math.random()*52);
        while(card1==card2)
        {
            card2=(int)(Math.random()*52);
        }
        int card3=(int)(Math.random()*52);
        while(card2==card3 && card3==card1)
        {
            card3=(int)(Math.random()*52);
        }
        int card4=(int)(Math.random()*52);
        while(card3==card4 && card2==card4 && card1==card4)
        {
            card4=(int)(Math.random()*52);
        }   
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        return cards;
    }
    public static int Hit (ArrayList<Integer> cards)
    {
        int card1=(int)(Math.random()*52);
        boolean bunmatched=false;
        while(bunmatched==false)
        {
            bunmatched=true;
            for(int i=0;i<cards.size();i++)
            {
                if(cards.get(i)==card1)
                {
                    bunmatched=false;
                    card1=(int)(Math.random()*52);
                    break;
                }
            }
            
        }
        return card1;
    }
public static void BlackJack (String[] args)
{
    System.out.println("Welcome to BlackJack\n");
    Scanner scan = new Scanner(System.in);
    Boolean busted=false;
    Boolean dealerbust=false;
    ArrayList<Integer> player=new ArrayList<Integer>();
    ArrayList<Integer> dealer=new ArrayList<Integer>();
    ArrayList <Integer> cards=shuffle();
    player.add(cards.get(0));
    player.add(cards.get(2));
    dealer.add(cards.get(1));
    dealer.add(cards.get(3));
    String[] arr1=assign(cards);
    System.out.println("Player:");
    print(player);
    System.out.println();
    System.out.println("Dealer:");
    System.out.println(arr1[1]);
    System.out.println();
    String play="Hit";
    while(play.equals("Hit"))
    {
        System.out.println("Hit or Stay");
        play=scan.nextLine();
        if(play.equals("Hit"))
        {   
            int x=Hit(cards);
            player.add(x);
            cards.add(x);
            System.out.println("Player: ");
            print(player);
            System.out.println();
        }
        
        if(cardTotal(dealer)>21)
        {
            System.out.println("BUSTED!");
            busted=true;
            break;
        }

    }
    if(busted==false)
        while(cardTotal(dealer)<17 || cardTotal(player)>cardTotal(dealer))
        {
            if(cardTotal(dealer)<17 || cardTotal(player)>cardTotal(dealer))
            {
                int x=Hit(cards);
                dealer.add(x);
                cards.add(x);
            }
            if(cardTotal(dealer)>21)
            {
                dealerbust=true;
            }

        }
    System.out.println("Player: ");
    print(player);
    System.out.println();
    System.out.println("Dealer: ");
    print(dealer);
        System.out.println();
    if(busted==false)
    {
        if(cardTotal(player)>cardTotal(dealer))
            System.out.println("PLAYER WINS!!");
        else if(dealerbust=true)
            System.out.println("PLAYER WINS!!");
        else if(cardTotal(dealer)==cardTotal(player))
            System.out.print("TIE");
        else 
            System.out.println("Player Loses");
    }
}
}
