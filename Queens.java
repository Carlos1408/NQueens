import java.util.Arrays;

public class Queens {

    private int num_queens;
    private int[] solution;
    
    public Queens(int num_queens) {
        this.num_queens = num_queens;
        solution = new int[num_queens];
        init();
        String strArray = Arrays.toString(solution);
        System.out.println(strArray);
    }

    public void init() {
        for(int i = 0; i<solution.length; i++)
            solution[i] = -1;
    }

    public void searchSolution() {
        backTracking(solution, 0);
    }
    
    public void backTracking(int[] solution, int queen) {
        if(queen < num_queens) {
            do{
                solution[queen]++;
                boolean valid = isValid(solution, queen);
                if(valid)
                    backTracking(solution, queen++);
            }while(solution[queen] < num_queens-1);
        }
    }

    public boolean isValid(int[] solution, int queen) {
        return true;
    }

    public static void main(String[] args) {
        Queens queen = new Queens(4);
        queen.searchSolution();
    }
}
