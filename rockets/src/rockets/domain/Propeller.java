package rockets.domain;

import javax.swing.JLabel;

public class Propeller {
	
	private	JLabel label ;        
	private Thread thread;
	
	private	int    potenciaActual = 0;
	private	int    potenciaMax;
		
	public Propeller ( int potencia) {		
		potenciaMax = potencia;	
		
	}
	
	public Thread getThread() {
		return thread;
	}

	public void setThread(Runnable r) {
		thread = new Thread(r);
	}
	
	public void setTextLabel () {
		label.setText(potenciaActual+"");
	}

	public JLabel getLabel() {
		return label;
	}


	public void setLabel(JLabel label) {
		this.label = label;
	}


	public int getPotenciaActual() {
		return potenciaActual;
	}


	public void setPotenciaActual(int potenciaActual) {
		this.potenciaActual = potenciaActual;
	}


	public int getPotenciaMax() {
		return potenciaMax;
	}
	
	
}
