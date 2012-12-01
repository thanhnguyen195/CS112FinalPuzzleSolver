public class GameFactory{
    public Game selectGame(String st){
        if (st.equals("Sudoku"))
            return new Sudoku();
        if (st.equals("KenKen"))
            return new KenKen();
        return null;
    }
}