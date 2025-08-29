/*

 Name          : Gobal Krishnan V
 Date of Birth : 18/06/1995
 Compilation   : javac
 Execution     : java
* */
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ExpCurve extends JPanel implements  ChangeListener{
    ExpCurve(){
        slider = new JSlider(1,3,3);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
    }
    public static double xb = 3.0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 =  (Graphics2D)g;


        // Example: draw exponential curve y = e^x
        double step = 0.01;
        int w = getWidth();
        int h = getHeight();

        // scale to panel
        double xScale = 100; // pixels per unit in x
        double yScale = 20; // pixels per unit in y

        for (double x = -1; x <= 5; x += step) {
            double y1 = Math.exp(x);
            double y2 = Math.exp(x + step);

            int px1 = (int) (xScale + x * xScale);
            int py1 = h - (int) (y1 * yScale); // invert y
            int px2 = (int) (xScale + (x + step) * xScale);
            int py2 = h - (int) (y2 * yScale);

            g2.setColor(Color.BLUE);
            g2.drawLine(px1, py1, px2, py2);
        }


        //tangent line: y = slope*(x - x0) + y0
        double x0 = 1;
        double y0 = Math.exp(x0);
        double slope = y0;

        for (double x = -1; x <= 5; x += step) {
            double y1 = slope * (x - x0) + y0;
            double y2 = slope * ((x+step) - x0) + y0;

            int px1 = (int) (xScale + x * xScale);
            int py1 = h - (int) (y1 * yScale); // invert y
            int px2 = (int) (xScale + (x + step) * xScale);
            int py2 = h - (int) (y2 * yScale);

            g2.setColor(Color.RED);
            g2.drawLine(px1, py1, px2, py2);
        }


       double yb = Math.exp(xb);

        int px1 = (int) (xScale + x0 * xScale);
        int py1 = h - (int) (y0 * yScale); // invert y
        int px2 = (int) (xScale + (xb+ step) * xScale);
        int py2 = h - (int) (yb * yScale);

        g2.setColor(new Color(2,60,2,255));
        // Another stroke (dashed line)
        float[] dashPattern = {10, 5}; // 10px line, 5px gap
        g2.setStroke(new BasicStroke(
                3,                      // line thickness
                BasicStroke.CAP_ROUND,  // end cap style
                BasicStroke.JOIN_MITER, // join style
                10,                     // miter limit
                dashPattern,            // dash pattern
                0                       // dash phase
        ));
        g2.drawLine(px1, py1, px2, py2);


        g2.setColor(Color.BLUE);


        g2.drawString("Q",px2-10,py2);
        g2.drawString("P",px1-10,py1);

        g2.setColor(new Color(2,60,2,255));
        g2.drawString("Greed Dash Color line\n is Secant Line",px1-150,py1-100);
        g2.setColor(Color.RED);

        g2.drawString("Red Color line\n is Tanget Line",px1-150,py1-60);







    }
    static JSlider slider;
    public static void main(String[] args) {
      JFrame f = new JFrame("Geometric Viewpoint on Derivatives");
      f.setLayout(new BorderLayout());
      f.add(new ExpCurve(),BorderLayout.CENTER);
      f.setSize(500,500);


      f.add(slider,BorderLayout.SOUTH);
      f.setResizable(false);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        xb = slider.getValue();
        repaint();
    }
}
