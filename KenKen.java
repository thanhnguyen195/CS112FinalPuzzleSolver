import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class KenKen extends Game{

    private int size;
    ArrayList<Cage> cages = new ArrayList<Cage>();
    
    public KenKen(){

    }
    
    public ArrayList<Spots> InitData(ArrayList<Spots> spots){

        Scanner sc = null;
        try{
            sc = new Scanner(new FileReader("KenKenInput.dat"));
        }catch (FileNotFoundException e){
            System.out.println(e);
            System.exit(1);
        }
        
        int i = 0;
        size = sc.nextInt();
        String nothing = sc.nextLine();
        while (sc.hasNextLine()){
            Scanner line = new Scanner(sc.nextLine());
            Spots token = new KenKenSpots(i/size,i%size,0);
            token.initPossibleValue(size);
            if (token.getValue()==0)
                token.setLabel(false);
            else
                token.setLabel(true);
            int cageNum = line.nextInt();
            token.setCage(cageNum);
            if (line.hasNext()){
                Cage c = new Cage(cageNum);
                c.setGoal(line.nextInt());
                c.setOp(line.next());
                cages.add(c);
            }
            for (int j = 0;j<cages.size();j++){
                if (cages.get(j).getCageNum()==cageNum)
                    cages.get(j).addSpots((KenKenSpots)token);
            }
            //System.out.print(token.getCage()+" ");
            spots.add(token);
            //System.out.println(spots.get(i).getCage());
            //if (i>0){
            //  spots.get(i-1).setNext((KenKenSpots)spots.get(i));
            //    }
            i++;
            }
        i = 0;
        while (i<cages.size()){
            Cage cage = cages.get(i);
            ArrayList<KenKenSpots> cageSpots = cage.getSpots();
            for (int j=0;j<cageSpots.size()-1;j++){
                cageSpots.get(j).setNext((KenKenSpots)cageSpots.get(j+1));
            }
            if (i+1<cages.size())
                cageSpots.get(cageSpots.size()-1).setNext((KenKenSpots)cages.get(i+1).getSpots().get(0));
            i++;
        }
        return spots;
    }
    
    public void printSpots(ArrayList<Spots> spots){
        int i = 0;
        //System.out.println(spots.size()+" ");
        while (spots.get(i).getNext()!=null){
            System.out.print(spots.get(i).getCage()+" ");
            i++;
        }
        System.out.println();
        /*
        for (i=0;i<spots.size();i++){
            System.out.print(spots.get(i).getCage()+" ");
        }
        */
        for (i=0;i<cages.size();i++){
            System.out.println(cages.get(i).getCageNum()+" "+cages.get(i).getGoal()+" "+cages.get(i).getOp());
        }
    }
    
    public void printSpotsRe(Spots spot){
        System.out.print(spot.getCage()+" ");
        if (spot.getNext()!=null)
            printSpotsRe(spot.getNext());
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
    
    private boolean checkFullCage(ArrayList<Spots> spots,Spots spot,Cage cage){
        ArrayList<KenKenSpots> cageSpots = cage.getSpots();
        KenKenSpots max = cageSpots.get(0);
        for (int i=0;i<cageSpots.size();i++){
            if (cageSpots.get(i).getValue()>max.getValue())
                max = cageSpots.get(i);
        }
        int result=max.getValue();
        if (cage.getOp().equals("+")){
            for (int i=0;i<cageSpots.size();i++){
                if (cageSpots.get(i)!=max)
                    result = result + cageSpots.get(i).getValue();
                }
        }
        if (cage.getOp().equals("-")){
            for (int i=0;i<cageSpots.size();i++){
                if (cageSpots.get(i)!=max)
                    result = result - cageSpots.get(i).getValue();
                }
        }
        if (cage.getOp().equals("*")){
            for (int i=0;i<cageSpots.size();i++){
                if (cageSpots.get(i)!=max)
                    result = result * cageSpots.get(i).getValue();
                }
        }
        if (cage.getOp().equals("/")){
            for (int i=0;i<cageSpots.size();i++){
                if (cageSpots.get(i)!=max){
                    if (result%cageSpots.get(i).getValue()!=0)
                        return false;
                    result = result / cageSpots.get(i).getValue();
                    }
                }
        }
        if (result==cage.getGoal())
            return true;
        return false;
    }
    
    private boolean contrainstCage(ArrayList<Spots> spots,Spots spot){
        boolean cageCheck = true;
        for (int i=0;i<spots.size();i++){
            if (spots.get(i).getCage()==spot.getCage()){
                if (spots.get(i).getLabel()==false)
                    cageCheck = false;
            }
        }
        if (cageCheck==false)
            return true;
        for (int i=0;i<cages.size();i++)
            if (cages.get(i).getCageNum()==spot.getCage())
                return checkFullCage(spots,spot,cages.get(i));
        return false;
    }

    public boolean contrainst(ArrayList<Spots> spots, Spots spot){
        if (contrainstR(spots,spot)&&contrainstC(spots,spot)&&contrainstCage(spots,spot))
            return true;
        return false;
    }
    
    
}