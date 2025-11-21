package es.upm.aled.ejercicios31;

public class Escritora extends Thread{

	
	private final Recurso recurso;
	private final String nombre;
	
	public Escritora(Recurso recurso, String nombre) {
		this.recurso = recurso;
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
		try {
			recurso.escribir(nombre);
		}catch(Exception e) {
			
		}
	}
}
