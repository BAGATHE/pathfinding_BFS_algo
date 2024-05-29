package ia.aff;

import javax.swing.JFrame;
import ia.panel.*;

public class Window extends JFrame{

	public Window(){
		this.setTitle("PATH FINDING");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.getContentPane().add(new Panel());
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}

}