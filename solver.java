import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class solver {
    private ArrayList<Spots> spots = new ArrayList<Spots>();
    private Game game;
    private String gameType;
    
    private void initData(){
        gameType = "KenKen";
        GameFactory gf = new GameFactory();
        game = gf.selectGame(gameType);
        spots = game.InitData(spots);
        game.printSpotsRe(spots.get(0));
    }

    public boolean backTrackSolver(Spots spot){
        if (spot.getLabel()==false){//if this spot has not yet labeled
            spot.setLabel(true);
            for (int i=0;i<spot.getPossibleValue().size();i++){
                spot.setValue(spot.getPossibleValue().get(i));
                if (game.contrainst(spots,spot)){
                    System.out.println(spot.getR()+" "+spot.getC()+" "+spot.getValue());
                    if (spot.getNext()!=null){
                        if (backTrackSolver(spot.getNext()))
                            return true;
                        } else {
                    return true;
                    }
                }
            }
            spot.setValue(spot.getInitValue());
            spot.setLabel(false);
            return false;
        } else {
        if (spot.getNext()!=null)
            return backTrackSolver(spot.getNext());
        return true;
        }
    }

    private void printResult(){
        int i = 0;
        while (spots.get(i).getNext()!=null){
            System.out.print(spots.get(i).getValue()+" ");
            i++;
        }
        System.out.print(spots.get(i).getValue()+" ");
        System.out.println("end");
    }

    public static void main(String[] args){
        solver thisOne = new solver();
        thisOne.initData();
        thisOne.printResult();
        if (thisOne.backTrackSolver(thisOne.spots.get(0)))
            thisOne.printResult();
    }
}