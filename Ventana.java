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

public class Ventana extends JFrame{
    private JPanel panelCenter;
    private JButton[][] btnCells;
    private int SIZE = 500;
    private int num_Solutions;
    private List<int[]> solutionList;
    private int num_Pieces;

    public Ventana() {
        panelCenter = new JPanel();
        num_Solutions=0;
        solutionList = new LinkedList<int[]>();
        num_Pieces = 0;
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
        add(BorderLayout.CENTER, getBoard());
        setVisible(true);
    }

    public JPanel getBoard() {
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
            }
        }
        return panelCenter;
    }

    public JPanel getSolution() {
        JPanel panelSouth = new JPanel();
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
        JPanel buttons = new JPanel();
        GridLayout gestorButtons = new GridLayout(2, 1);
        JButton btnQueens = new JButton("Reinas");
        JButton btnHorses = new JButton("Caballos");
        btnHorses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        return panelNorth;
    }
    
}
