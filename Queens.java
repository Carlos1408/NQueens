import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Queens{

    private int num_queens;
    private int[] solution;
    private List<int[]> solutionList;
    private int num_Solutions;
    
    public Queens(int num_queens) {
        this.num_queens = num_queens;
        solution = new int[num_queens];
        num_Solutions = 0;
        solutionList = new LinkedList<>();
        init();
    }

    public void init() {
        num_Solutions = 0;
        solutionList.clear();
        for(int i = 0; i<solution.length; i++)
            solution[i] = -1;
    }

    public List<int[]> getSolutionList() {
        return solutionList;
    }

    public int getNumberOfSolutions() {
        return num_Solutions;
    }

    public void searchSolution() {
        init();
        backTracking(solution, 0);
    }
    
    public boolean backTracking(int[] solution, int queen) {
        boolean succes = false;
        if(queen < num_queens) {
            do{
                solution[queen]++;
                boolean valid = queenIsValid(solution, queen);
                System.out.println(Arrays.toString(solution)+" "+ (valid ? "Sol Parcial":"")+(valid && (queen == num_queens - 1) ? "SOLUTION" : ""));
                if(valid) {
                    succes = backTracking(solution, queen+1);
                    if(queen == num_queens-1) {
                        solutionList.add(solution.clone());
                        num_Solutions++;
                    }
                }
            }while(solution[queen] < num_queens-1 && !succes);
            solution[queen]=-1;
        }
        return succes;
    }

    public boolean queenIsValid(int[] solution, int queen) {
        boolean ok = true;
        for (int i = 0; i < queen; i++) {
            if ((solution[i] == solution[queen]) || (Math.abs(solution[i] - solution[queen]) == Math.abs(i - queen))) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        Queens queen = new Queens(4);
        queen.searchSolution();
    }
}
