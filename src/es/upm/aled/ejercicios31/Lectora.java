package es.upm.aled.ejercicios31;

public class Lectora extends Thread{
	
	private final Recurso recurso;
	private final String nombre;
	
	public Lectora(Recurso recurso, String nombre) {
		this.recurso=recurso;
		this.nombre=nombre;
	}
	
	@Override
	public void run() {
		try {
			recurso.leer(nombre);
		}catch(Exception e) {
			
		}
	}

}
