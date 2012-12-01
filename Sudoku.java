import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Sudoku extends Game{

    private int size = 9;
    
    public Sudoku(){

    }
    
    public ArrayList<Spots> InitData(ArrayList<Spots> spots){

        Scanner sc = null;
        try{
            sc = new Scanner(new FileReader("puzzleInput.dat"));
        }catch (FileNotFoundException e){
            System.out.println(e);
            System.exit(1);
        }
        
        int i = 0;
        while (sc.hasNext()){
            Spots token = new sudokuSpots(i/size,i%size,sc.nextInt());
            token.initPossibleValue(size);
            if (token.getValue()==0)
                token.setLabel(false);
            else
                token.setLabel(true);
            spots.add(token);
            if (i>0){
                token = spots.get(i-1);
                token.setNext((sudokuSpots)spots.get(i));
                spots.set(i-1,token);
                }
            i++;
            }
        return spots;
    }
    
    private boolean contrainstR(ArrayList<Spots> spots,Spots spot){
        for (int i=0;i<spots.size();i++){
            if (spots.get(i).getR()==spot.getR()&&spots.get(i).getC()!=spot.getC()&&spots.get(i).getValue()==spot.getValue())
                return false;
        }
        return true;
    }
    
    private boolean contrainstC(ArrayList<Spots> spots,Spots spot){
        for (int i=0;i<spots.size();i++){
            if (spots.get(i).getC()==spot.getC()&&spots.get(i).getR()!=spot.getR()&&spots.get(i).getValue()==spot.getValue())
                return false;
        }
        return true;
    }
    
    private boolean contrainstS(ArrayList<Spots> spots,Spots spot){
        int startr = (spot.getR()/3)*3;
        int startc = (spot.getC()/3)*3;
        for (int i=0;i<spots.size();i++){
            if (spots.get(i).getR()>=startr&&spots.get(i).getR()<(startr+3)&&spots.get(i).getC()>=startc&&spots.get(i).getC()<(startc+3)&&spots.get(i).getValue()==spot.getValue()){
                if (spots.get(i).getC()==spot.getC()&&spots.get(i).getR()==spot.getR()){
                
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean contrainst(ArrayList<Spots> spots, Spots spot){
        if (contrainstR(spots,spot)&&contrainstC(spots,spot)&&contrainstS(spots,spot))
            return true;
        return false;
    }
}