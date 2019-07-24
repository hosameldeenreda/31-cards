package hos;

public class Player {
    private String name;
    private short status;//1 normal 2 sleep 3 loser
    private int score;
    private int id;

    public Player(int id,String name){
       this.id=id;
       this.score=0;
       this.status=1;
       this.name= name;
    }
    void icrementScore(int sc){
        this.score += sc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }
}
