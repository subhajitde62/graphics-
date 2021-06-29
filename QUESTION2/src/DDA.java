import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.lang.*;
import static java.awt.BorderLayout.*;
import javax.swing.*;
import javax.swing.table.*;

public class DDA extends JFrame implements ActionListener {
    JTextField x0=new JTextField(5);
    JTextField y0=new JTextField(5);
    JTextField x1=new JTextField(5);
    JTextField y1=new JTextField(5);
    JPanel drawPanel=new JPanel();
    JPanel tablePanel=new JPanel();
    DefaultTableModel model;
    public DDA() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100,510);
        setTitle("DDA Algo Implement");
        Initialization();
    }

    public void Initialization(){
        JPanel topPanel=new JPanel();
        topPanel.add(new JLabel("x0"));
        topPanel.add(x0);
        topPanel.add(new JLabel("y0"));
        topPanel.add(y0);
        topPanel.add(new JLabel("x1"));
        topPanel.add(x1);
        topPanel.add(new JLabel("y1"));
        topPanel.add(y1);
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        drawPanel.setBackground(Color.cyan);
        JButton draw = new JButton("DRAW");
        topPanel.add(draw);
        draw.addActionListener(this);
        add(topPanel, NORTH);
        model=new DefaultTableModel();
        JTable table=new JTable();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        table.setModel(model);
        model.addColumn("Point(x,y)");
        tablePanel.add(new JScrollPane(table));
        tablePanel.setPreferredSize(new Dimension(100, 510));
        add(tablePanel, WEST);
        add(drawPanel);
    }
    public void DDAAlgorithm(){
        int stapes;
        double X0=Double.parseDouble(x0.getText());
        double Y0=Double.parseDouble(y0.getText());
        double X1=Double.parseDouble(x1.getText());
        double Y1=Double.parseDouble(y1.getText());
        double dx=X1-X0;
        double dy=Y1-Y0;
        int steps = (int) ((Math.abs(dx) > Math.abs(dy)) ? Math.abs(dx) : Math.abs(dy));
        double xincrement=dx/steps;
        double yincrement=dy/steps;
        double xx=X0;
        double yy=Y0;
        drawPanel.getGraphics().drawLine(500,0, 500, 500);
        drawPanel.getGraphics().drawLine(0,218, 1000, 218);
        model.addRow(new Object[]{"("+xx+", "+yy+")"});
        for(int i=0;i<steps;i++){
            double xTemp=xx;
            double yTemp=yy;
            xx+=xincrement;
            yy+=yincrement;
            drawPanel.getGraphics().drawLine((int)(xTemp*10)+500,218-(int)(yTemp*10),(int)(xx*10)+500,218-(int)(yy*10));
            model.addRow(new Object[]{"("+String.format("%.1f",xx)+", "+String.format("%.1f",yy)+")"});
        }
        String[] header = {"Point(x,y)"};
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DDAAlgorithm();
    }
    public static void main(String[]args){
        DDA obj=new DDA();
        obj.setVisible(true);
    }
}
