import javax.swing.JFrame;
import javax.swing.JLabel;

public class testGraphics extends JFrame{
	public testGraphics(){
		 //Create and set up the window.
        JFrame frame = new JFrame("test Graphics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the label
        JLabel label = new JLabel("test Graphics");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
}
