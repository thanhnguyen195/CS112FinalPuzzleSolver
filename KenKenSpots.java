import java.util.ArrayList;

public class KenKenSpots extends Spots {
    private int positionR;
    private int positionC;
    private int value;
    private KenKenSpots next;
    private boolean label;
    private int size;
    private ArrayList<Integer> posValue = new ArrayList<Integer>();
    private int initValue=0;
    private int cageNum;
    
    public int getInitValue(){
        return initValue;
    }
    
    public void initPossibleValue(int siz){
        size = siz;
        for (int i=1;i<=size;i++)
            posValue.add(i);
    }
    
    public ArrayList<Integer> getPossibleValue(){
        return posValue;
    }

    public KenKenSpots(int x,int y,int v){
        positionR = x;
        positionC = y;
        value = v;
    }
    
    public void setCage(int cage){
        cageNum = cage;
    }
    
    public int getCage(){
        return cageNum;
    }
    
    public void setLabel(boolean labelIn){
        label = labelIn;
    }
    
    public int getR(){
        return positionR;
    }
    
    public int getC(){
        return positionC;
    }
    
    public boolean getLabel(){
        return label;
    }
    
    public int getValue(){
        return value;
    }
    
    public void setValue(int val){
        value = val;
    }
    
    public void setNext(KenKenSpots nextIn){
        next = nextIn;
    }
    
    public KenKenSpots getNext(){
        return next;
    }
    
}