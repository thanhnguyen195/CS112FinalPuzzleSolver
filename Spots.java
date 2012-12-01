import java.util.ArrayList;

public class Spots {
    
    private int positionR;
    private int positionC;
    private int value;
    private Spots next;
    private boolean label;
    private int size;
    private ArrayList<Integer> posValue = new ArrayList<Integer>();
    private int initValue;
    private int cageNum;
    
    public int getInitValue(){
        return initValue;
    }
    
    public void initPossibleValue(int in){
        for (int i=1;i<=size;i++)
            posValue.add(i);
    }
    
    public ArrayList<Integer> getPossibleValue(){
        return posValue;
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
    
    public void setNext(sudokuSpots nextIn){
        next = nextIn;
    }

    public void setNext(KenKenSpots nextIn){
        next = nextIn;
    }

    
    public Spots getNext(){
        return next;
    }

}