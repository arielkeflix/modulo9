package rockets.view;

import java.awt.Color;

import javax.swing.JFrame;

import rockets.domain.Rocket;

public class MainScreen extends JFrame{		
	
	RocketScreen rocketScreen1, rocketScreen2;     
	Rocket rocketUno, rocketDos;
	PanelControl panelControl1,panelControl2;
	
	public MainScreen(Rocket rocketUno, Rocket rocketDos) {
		
		this.rocketUno = rocketUno;
		this.rocketDos = rocketDos;
		
		this.setBounds(100, 100, 1070, 660);		

		setTitle("Rocket launch platform");		
		
        /***** instancio para cada rocket una clase screen y otra de control ******************** */
		rocketScreen1 = new RocketScreen(rocketUno);
		rocketScreen1.setBounds(0, 0, 500, 500);
		rocketScreen1.setBackground(Color.BLUE);
		add(rocketScreen1);
		

		rocketScreen2 = new RocketScreen(rocketDos);
		rocketScreen2.setBounds(555, 0, 500, 500);
		rocketScreen2.setBackground(Color.BLUE);
		add(rocketScreen2);
		
		panelControl1 = new PanelControl(rocketUno);
		panelControl1.setBounds(0,500, 500, 120);
		this.add( panelControl1);
		
		panelControl2 = new PanelControl(rocketDos);
		panelControl2.setBounds(555,500, 500, 120);
		this.add( panelControl2);		
		
        /*********************************************************************************/
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
	}	
}


