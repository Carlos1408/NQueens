import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;

public class Queens extends JFrame{

    private int num_queens;
    private int[] solution;
    private static final long serialVertsionUID = 1L;
    private static final int SIZE = 800;
    private static JPanel panelBoard = new JPanel();
    private List<int[]> listSolution;
    private int qSol;
    private JButton[][] btnCells;
    
    public Queens(int num_queens) {
        this.num_queens = num_queens;
        solution = new int[num_queens];
        initializeComponents();
        init();
        listSolution = new LinkedList<>();
        qSol = 0;
        
    }

    public void initializeComponents() {
        setTitle("N Reinas");
        setSize(SIZE, SIZE);
        setLocationRelativeTo(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout gestorLayout = new BorderLayout();

        //GridLayout gestor = new GridLayout(1, 4);
        setLayout(gestorLayout);

        add(BorderLayout.NORTH, getOptions());
        add(BorderLayout.SOUTH, getResult());
        
        //add(BorderLayout.EAST, btn3);
        //add(BorderLayout.WEST, btn4);
        add(BorderLayout.CENTER, panelBoard);
    }

    public void getBoard() {
        panelBoard.removeAll();
        panelBoard.validate();
        panelBoard.repaint();
        GridLayout gestor = new GridLayout(num_queens, num_queens);
        panelBoard.setLayout(gestor);
        for(int i = 0; i<num_queens; i++) {
            for(int j = 0; j<num_queens; j++) {
                JButton cell = new JButton(i+" "+j);
                if((i+j)%2==0)
                    cell.setBackground(Color.BLACK);
                else
                    cell.setBackground(Color.WHITE);
                cell.setEnabled(false);
                panelBoard.add(cell);
            }
        }
    }

    public void paintSolution(int[] s) {
        for (int i = 0; i < s.length; i++) {
            btnCells[s[i]][i].setText("X");
        }
    }

    /*public JPanel getOptions() {
        JPanel panel = new JPanel();
        GridLayout gestor = new GridLayout(1, 2);
        panel.setLayout(gestor);
        //JTextField textField = new JTextField("Ingrese el numero de reinas");
        //textField.setBounds(0, 0, gestor.getHgap(), gestor.getVgap());
        //panel.add(textField);
        JButton btnSolution = new JButton("Buscar solucion");
        btnSolution.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnSolution.setText("Pulsado");
            }
        });
        panel.add(new JLabel("Numero de Reinas "+num_queens));
        panel.add(btnSolution);
        return panel;
    }*/

    public JPanel getOptions() {
        JPanel panelNorth = new JPanel();
        FlowLayout gestor = new FlowLayout();
        panelNorth.setLayout(gestor);
        JLabel lbNumQueens = new JLabel("Numero de reinas: ");
        panelNorth.add(lbNumQueens);
        JTextField txtNumQueens = new JTextField(10);
        panelNorth.add(txtNumQueens);
        JButton btnGo = new JButton("Resolver");
        btnGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num_queens = Integer.parseInt(txtNumQueens.getText());
                qSol = 0;
                searchSolution();
                btnCells = new JButton[num_queens][num_queens];
                getBoard();
                paintSolution(listSolution.get(0));
            }
        });
        panelNorth.add(btnGo);
        return panelNorth;
    }

    /*public JPanel getSolutions() {
        JPanel panel = new JPanel();
        GridLayout gestor = new GridLayout(1, 2);
        panel.setLayout(gestor);
        JButton btnPrevious = new JButton("Anterior solucion");
        JButton btnNext = new JButton("Siguiente solucion");
        panel.add(btnPrevious);
        panel.add(btnNext);
        return panel;
    }*/

    public JPanel getResult() {
        JPanel panelSouth = new JPanel();
        JLabel lbnmsCantidad = new JLabel("Cantidad solucion: ");
        JLabel lbnCantidad = new JLabel("0");
        JButton btnPreview = new JButton("<<");
        JLabel lbnNumsolCurrent = new JLabel("0");
        JButton btnNext = new JButton(">>");
        panelSouth.add(lbnmsCantidad);
        panelSouth.add(lbnCantidad);
        panelSouth.add(btnPreview);
        panelSouth.add(lbnNumsolCurrent);
        panelSouth.add(btnNext);
        return panelSouth;
    }

    public void init() {
        for(int i = 0; i<solution.length; i++)
            solution[i] = -1;
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
                        listSolution.add(solution.clone());
                        qSol++;
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
        //queen.searchSolution();
    }
}
