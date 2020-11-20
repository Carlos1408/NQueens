import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class Window extends JFrame{
    private JPanel panelCenter;
    private JButton[][] btnCells;
    private int SIZE = 500;
    private int num_Solutions;
    private List<int[]> solutionList;
    private int num_Pieces;
    private int solPosition;
    private JLabel lblQuantity;
    private JLabel lblNumsolCurrent;
    private ImageIcon pieceIcon;

    public Window() {
        panelCenter = new JPanel();
        num_Solutions=0;
        lblQuantity = new JLabel("0");
        lblNumsolCurrent = new JLabel("0");
        solutionList = new LinkedList<int[]>();
        num_Pieces = 0;
        solPosition = 0;
        pieceIcon = null;
        initComponents();
    }

    public void initComponents() {
        setTitle("Usando backtracking");
        setSize(SIZE, SIZE);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout gestorPrincipal = new BorderLayout();
        setLayout(gestorPrincipal);
        
        add(BorderLayout.NORTH, getOptions());
        add(BorderLayout.SOUTH, getSolution());
        add(BorderLayout.CENTER, panelCenter);
        setVisible(true);
    }

    public void getBoard() {
        panelCenter.removeAll();
        panelCenter.validate();
        panelCenter.repaint();
        btnCells = new JButton[num_Pieces][num_Pieces];
        GridLayout gestor = new GridLayout(num_Pieces, num_Pieces);
        panelCenter.setLayout(gestor);
        for (int i = 0; i < num_Pieces; i++) {
            for (int j = 0; j < num_Pieces; j++) {
                JButton cell = new JButton();
                cell.setEnabled(false);
                if((i+j)%2==0)
                    cell.setBackground(Color.BLACK);
                else
                    cell.setBackground(Color.WHITE);
                btnCells[i][j] = cell;
                panelCenter.add(cell);
            }
        }
    }

    public JPanel getSolution() {
        JPanel panelSouth = new JPanel();
        JLabel lblMsQuantity = new JLabel("Cantidad de soluciones: ");
        JButton btnPrevious = new JButton("<<");
        JButton btnNext = new JButton(">>");
        btnPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(solPosition>0) {
                    solPosition--;
                    getBoard();
                    paintSolution();
                }
            }
        });
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(solPosition<num_Solutions-1) {
                    solPosition++;
                    getBoard();
                    paintSolution();
                }
            }
        });
        panelSouth.add(lblMsQuantity);
        panelSouth.add(lblQuantity);
        panelSouth.add(btnPrevious);
        panelSouth.add(lblNumsolCurrent);
        panelSouth.add(btnNext);
        return panelSouth;
    }

    public JPanel getOptions() {
        JPanel panelNorth = new JPanel();
        FlowLayout gestor = new FlowLayout();
        panelNorth.setLayout(gestor);
        JLabel lblNumPieces = new JLabel("Numero de piesas: ");
        panelNorth.add(lblNumPieces);
        JTextField txtNumPieces = new JTextField(10);
        panelNorth.add(txtNumPieces);
        JPanel panelButtons = new JPanel();
        GridLayout gestorButtons = new GridLayout(2, 1);
        panelButtons.setLayout(gestorButtons);
        JButton btnQueens = new JButton("Reinas");
        JButton btnHorses = new JButton("Caballos");
        btnHorses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num_Pieces = Integer.parseInt(txtNumPieces.getText());
                Horses horse = new Horses(num_Pieces);
                horse.searchSolution();
                solutionList = horse.getSolutionList();
                num_Solutions = horse.getNumberOfSolutions();
                solPosition = 0;
                lblQuantity.setText(String.valueOf(num_Solutions));
                pieceIcon = new ImageIcon("icons/horse.png");
                getBoard();
                paintSolution();
            }
        });
        btnQueens.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num_Pieces = Integer.parseInt(txtNumPieces.getText());
                Queens queen = new Queens(num_Pieces);
                queen.searchSolution();
                solutionList = queen.getSolutionList();
                num_Solutions = queen.getNumberOfSolutions();
                solPosition = 0;
                lblQuantity.setText(String.valueOf(num_Solutions));
                pieceIcon = new ImageIcon("icons/quuen.png");
                getBoard();
                paintSolution();
            }
        });
        panelButtons.add(btnQueens);
        panelButtons.add(btnHorses);
        panelNorth.add(panelButtons);
        return panelNorth;
    }
    public void paintSolution() {
        lblNumsolCurrent.setText(String.valueOf(solPosition+1));
        int[] s = solutionList.get(solPosition);
        for(int i = 0; i<s.length; i++) {
            btnCells[s[i]][i].setText("X");
            //btnCells[s[i]][i].setIcon(pieceIcon);
        }
    }
    
    public static void main(String[] args) {
        Window window = new Window();
    }
}
