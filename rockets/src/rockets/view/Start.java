package rockets.view;

import javax.swing.JFrame;

import rockets.domain.Rocket;


public class Start {

	public static void main(String[] args) {				
		
		int [] propulsores1 = { 10,30,80};
		int [] propulsores2 = { 30, 40, 50, 50, 30, 10};		
		
		Rocket rocketUno = new Rocket("32WESSDS", propulsores1); 
		Rocket rocketDos = new Rocket("LDSFJA32", propulsores2);				

		JFrame marco = new MainScreen(rocketUno, rocketDos);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setVisible(true);
	}

}
