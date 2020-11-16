import java.util.Arrays;

public class Horses {
    private int num_horses;
    private int[] solution;
    
    public Horses(int num_horses) {
        this.num_horses = num_horses;
        solution = new int[num_horses];
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
    
    public boolean backTracking(int[] solution, int horse) {
        boolean succes = false;
        if(horse < num_horses) {
            do{
                solution[horse]++;
                boolean valid = horseIsValid(solution, horse);
                System.out.println(Arrays.toString(solution)+" "+ (valid ? "Sol Parcial":"")+(valid && (horse == num_horses - 1) ? " -> SOLUCION" : ""));
                if(valid)
                    succes = backTracking(solution, horse+1);
            }while(solution[horse] < num_horses-1 && !succes);
            solution[horse]=-1;
        }
        return succes;
    }

    public boolean horseIsValid(int[] solution, int horse) {
        boolean ok = true;
        for (int i = 0; i < horse; i++) {
            if(i+2 <= horse) {
                if(solution[i+2]+1==solution[horse] || solution[i+1]+2==solution[horse] || solution[i+2]-1==solution[horse] || solution[i+1]-2==solution[horse]) {
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        Horses horses = new Horses(4);
        horses.searchSolution();
    }
}
