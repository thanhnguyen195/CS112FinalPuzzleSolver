import java.util.ArrayList;

public class Cage{
    private int cageNum;
    private int Goal;
    private String Operation;
    private ArrayList<KenKenSpots> kenspots = new ArrayList<KenKenSpots>();
    
    public Cage(int num){
        cageNum = num;
    }
    
    public void setGoal(int goal){
        Goal = goal;
    }
    
    public void setOp(String op){
        Operation = op;
    }
    
    public int getCageNum(){
        return cageNum;
    }
    
    public int getGoal(){
        return Goal;
    }
    
    public String getOp(){
        return Operation;
    }
    
    public void addSpots(KenKenSpots spot){
        kenspots.add(spot);
    }
    
    public ArrayList<KenKenSpots> getSpots(){
        return kenspots;
    }
}