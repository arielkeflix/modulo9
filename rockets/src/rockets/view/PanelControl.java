package rockets.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rockets.domain.Propeller;
import rockets.domain.Rocket;

class PanelControl extends JPanel {	
	
	int marcha = 1;
	JButton acelerar = new JButton("acelerar");
	JButton frenar = new JButton("frenar");
	JButton stop = new JButton("stop");
	Rocket rocket;

	public PanelControl(Rocket rocket) {
		
		this.rocket = rocket;
		setLayout(null);
		frenar.setEnabled(false);
		setBackground(Color.WHITE);
		Font miFuente = new Font("Arial", Font.BOLD, 16);			

		acelerar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acelerar.setEnabled(false);
				frenar.setEnabled(true);
				accionBotones(e);
			}
		});
		acelerar.setBounds(100, 40, 100, 40);

		frenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frenar.setEnabled(false);
				acelerar.setEnabled(true);
				accionBotones(e);
			}
		});
		frenar.setBounds(300, 40, 100, 40);
		
		JLabel marchas = new JLabel("Marchas ", SwingConstants.CENTER);
		marchas.setFont(miFuente);
		marchas.setForeground(Color.BLACK);
		marchas.setBounds(20, 10, 70, 20);
		this.add(marchas);
        
		/* box de eleccion de marcha ******************************/
		String[] mach = { "X 1", "X 2", "X 3", "X 4", "X 5" };

		JList<String> listMarchas = new JList<>(mach);
		listMarchas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMarchas.setSelectedIndex(0);
		listMarchas.setFixedCellHeight(15);
		listMarchas.setFixedCellWidth(50);
		listMarchas.setVisibleRowCount(5);
		listMarchas.setBounds(30, 30, 50, 80);
		
		listMarchas.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
				marcha = list.getSelectedIndex() + 1;  // sumo 1 porque el array comienza con 0
			}
		});
		/*******************************************************************************/

		add(listMarchas); 
		add(acelerar);
		add(frenar);

		setVisible(true);
	}  // fin constructor class PanelControl

	protected void accionBotones(ActionEvent e) {
		
		/*** cada propulsor tiene su propia variable del tipo Thread  que puede encargarse tanto de frenar como de acelerar ***/
		
		if (e.getSource() == acelerar) {   //cuando el acelerar viene desde el "rocket 1

			for (Propeller p : rocket.getPropulsores()) {
				if (p.getThread() != null)
					p.getThread().interrupt();
				Runnable r = new threadPropAcelerar(p);
				p.setThread(r);
				p.getThread().start();
			}
		}
		else if (e.getSource() == frenar) {

			for (Propeller p : rocket.getPropulsores()) {
				if (p.getThread() != null)
					p.getThread().interrupt();
				Runnable r = new threadPropFrenar(p);
				p.setThread(r);
				p.getThread().start();
			}
		}		
	}
	
	class threadPropAcelerar implements Runnable{
		Propeller propulsor;
		
		public threadPropAcelerar(Propeller p) {   
			propulsor = p;
			
		}

		public void run() {
			int x;				
			
			for (x = propulsor.getPotenciaActual(); x <= propulsor.getPotenciaMax(); x= x+marcha ) {														
				
				try {
					Thread.sleep(1000);
					propulsor.setPotenciaActual(x );
					propulsor.setTextLabel();						
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}
	
	class threadPropFrenar implements Runnable{
		Propeller propulsor;
		
		public threadPropFrenar(Propeller p) {   
			propulsor = p;
		}

		public void run() {
			int x;				
			
			for (x =propulsor.getPotenciaActual(); x >= 0; x--) {					
				
				try {
					Thread.sleep(500);
					propulsor.setPotenciaActual(x);
					propulsor.setTextLabel();					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
	}
}


