package hos;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.Vector;

@SpringBootApplication
public class Application {
    public static int get_score(char code){
        int score=0;
        if(code>='2'&&code<='9'){
            score=code-48;
        }
        else if(code=='A')
            score=1;
        else if(code=='J')
            score=20;
        else if(code=='Q')
            score=5;
        else if(code=='K')
            score=10;
        return score;
    }
    public static void resetPlayers(Vector<Player> p){
        for(int i=0;i<p.size();++i){
            p.get(i).setStatus((short)1);
            p.get(i).setScore(0);
        }
    }
    public static void main(String args[]) {
        Scanner s=new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        Deck deck = restTemplate.getForObject("https://deckofcardsapi.com/api//new/", Deck.class);
        int num_players = 0;
        System.out.println("enter number of players .");
        num_players=s.nextInt();
        Vector<Player> players=new Vector();
        for(int i=0;i<num_players;i++){
            int p_id=i+1;String p_name="0";
            System.out.print("please enter your Name");
            p_name=s.next();
            Player p_temp=new Player(p_id,p_name);
            players.add(p_temp);
        }
        while (true) {
            deck = restTemplate.getForObject("https://deckofcardsapi.com/api/deck/" + deck.getDeck_id() + "/shuffle/", Deck.class);
            int l_s=0;
            while (l_s!=num_players){
                for(int i=0;i<num_players;i++){
                    if(players.get(i).getStatus()==1) {
                        System.out.println("its your turn "+players.get(i).getName()+" your score is "+players.get(i).getScore()+"p");
                        System.out.println("want to sleep..? make your choice..(Y,N)");
                        String choice;
                        choice = s.next();
                        if (choice.equals("Y") || choice.equals("y")){
                            l_s++;
                            players.get(i).setStatus((short)2);
                            continue;
                        }
                        deck = restTemplate.getForObject("https://deckofcardsapi.com/api/deck/"+deck.getDeck_id()+"/draw/?count=1", Deck.class);
                        System.out.println("your card is "+deck.getCards()[0].getCode());
                        players.get(i).icrementScore(get_score(deck.getCards()[0].getCode().charAt(0)));
                        if(players.get(i).getScore()>31){
                            l_s++;
                            System.out.println("2lbs ya m3lm :)");
                            players.get(i).setStatus((short)3);
                        }

                    }
                }
            }
            int max_score=-1;int win_id=-1;String win_name="";
            for(int i=0;i<num_players;i++){
                if(players.get(i).getStatus()!=3){
                    if(players.get(i).getScore()==max_score){
                        win_id=-1;
                        break;
                    }else if(players.get(i).getScore()>max_score) {
                        max_score = players.get(i).getScore();
                        win_id = players.get(i).getId();
                        win_name= players.get(i).getName();
                    }
                }
            }
            System.out.println("the winner is ");
            if(win_id==-1)
                System.out.println("m7d4 :p");
            else
                System.out.println(win_name+" \\o/");
                System.out.println("do you to continue (Y/N)");
                String choice;
                choice = s.next();
                if (choice.equals("N") || choice.equals("n"))
                    break;
                resetPlayers(players);
        }
    }
}
