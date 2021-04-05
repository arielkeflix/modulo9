package rockets.domain;

import java.util.ArrayList;
import java.util.List;

public class Rocket {

	private String codigo;
	private List<Propeller> propulsores = new  ArrayList<Propeller>();
	private double speed=0;
	
	public Rocket(String cod, int[] prop ) {
		
		codigo = cod;				
		for(Integer potencia:prop) {
			propulsores.add(new Propeller( potencia));
			
		}				
	}		

	public void setSpeed(int potenciaTotal) {
		speed =  100 *Math.sqrt(potenciaTotal);
	}


	public String getCodigo() {
		return codigo;
	}


	public List<Propeller> getPropulsores() {
		return propulsores;
	}


	public int getSpeed() {
		return (int)speed;
	}	
	
}
