package rockets.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import rockets.domain.Propeller;
import rockets.domain.Rocket;


class RocketScreen extends JPanel {		
	
	public JLabel speed; 	
	JLabel nameLabel;
	Rocket rocket;
	
		public RocketScreen( Rocket rocket) {
			
			this.rocket = rocket;
			setBounds(600, 0, 500, 500);			
			
			Font miFuente = new Font("Arial", Font.BOLD, 20);
			setLayout(null);				

			nameLabel = new JLabel( "ROCKET "+rocket.getCodigo(), SwingConstants.CENTER);
			nameLabel.setFont(miFuente);
			nameLabel.setForeground(Color.RED);
			nameLabel.setBounds(0, 0, 500, 100);				
			this.add(nameLabel);
			
			JLabel speedLabel = new JLabel("Velocidad: ", SwingConstants.RIGHT);
			speedLabel.setFont(miFuente);
			speedLabel.setForeground(Color.BLACK);
			speedLabel.setBounds(0, 100, 250, 50);			
			this.add(speedLabel);
			
			speed = new JLabel("0 Km/h", SwingConstants.LEFT);
			speed.setFont(miFuente);
			speed.setForeground(Color.BLACK);
			speed.setBounds(250, 100, 250, 50);		
			this.add(speed);
			
			JLabel titPropulsor= new JLabel("Propulsor " , SwingConstants.LEFT);
			titPropulsor.setFont(miFuente);
			titPropulsor.setForeground(Color.BLACK);
			titPropulsor.setBounds(10, 150 , 140, 50);
			this.add(titPropulsor);
			
			JLabel titPotencia= new JLabel("<html>Potencia<br> actual</html>" , SwingConstants.LEFT);
			titPotencia.setFont(miFuente);
			titPotencia.setForeground(Color.BLACK);
			titPotencia.setBounds(180, 150 , 150, 50);
			this.add(titPotencia);
			
			JLabel titMaxPotencia= new JLabel("<html>Potencia <br>maxima</html>" , SwingConstants.LEFT);
			titMaxPotencia.setFont(miFuente);
			titMaxPotencia.setForeground(Color.BLACK);
			titMaxPotencia.setBounds(330, 150 , 150, 50);
			this.add(titMaxPotencia);
			
			int x=0;  // 
			for( Propeller propeller:rocket.getPropulsores()) {		//para cada propulsor de cada rocket y instancio 3 JLabel para su numero,  
																	//su actual potencia y su maxima potencia
				x++;
				JLabel  nroPropulsor= new JLabel(x + " " , SwingConstants.CENTER);
				 nroPropulsor.setFont(miFuente);
				 nroPropulsor.setForeground(Color.BLACK);
				 nroPropulsor.setBounds(0, 150 + x*50, 150, 50);				
				this.add( nroPropulsor);
				
				JLabel potPropulsor= new JLabel(propeller.getPotenciaActual() + " " , SwingConstants.CENTER);
				potPropulsor.setFont(miFuente);
				potPropulsor.setForeground(Color.BLACK);
				potPropulsor.setBounds(150, 150 + x*50, 150, 50);				
				this.add( potPropulsor);
				
				JLabel maxPotencia= new JLabel(propeller.getPotenciaMax() + " " , SwingConstants.CENTER);
				maxPotencia.setFont(miFuente);
				maxPotencia.setForeground(Color.BLACK);
				maxPotencia.setBounds(300, 150 + x*50, 150, 50);				
				this.add( maxPotencia);
				
				propeller.setLabel(potPropulsor);					
			}
			Runnable r = new ActualizarVelocidad(); 
			Thread threadActVel = new Thread(r);  // instancio un hilo exclusivo que controlara(velocidad) el JLabel de "speed"
			threadActVel.start();								
			
			setVisible(true);       											  
  	}
		
		class ActualizarVelocidad implements Runnable{		
			
			public void run() {
				int  potTotal,  potTotal2;			
				
				try {
					while(true) {
						
						potTotal=0;
						potTotal2=0 ;	
						 
						Thread.sleep(1000);	 //actualiza la velocidad cada  segundo
						
						for (Propeller p:rocket.getPropulsores())  potTotal += p.getPotenciaActual();					
						rocket.setSpeed(potTotal);										
						speed.setText(rocket.getSpeed()+"Km/h");						
					}	
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.out.println(" Fallo interrumpido la actualizacion");
					
				}
				
			}
		}		
	
}  


