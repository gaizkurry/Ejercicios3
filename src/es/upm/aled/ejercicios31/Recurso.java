package es.upm.aled.ejercicios31;

public class Recurso {
	
	private Integer entero = null;
	private int numLectoras = 0;
	private boolean escribiendo = false;
	
	public void leer(String nombreLectora) throws Exception{
		this.accesoLectura(nombreLectora);
		Integer valorLeido=this.entero;
		System.out.println("Lectora "+nombreLectora+" está leyendo.");
		Thread.sleep((long)(Math.random()*500));
		this.terminaLectura(nombreLectora);
	}
	
	private void terminaLectura(String nombreLectora) {
		numLectoras--;
		if(numLectoras==0) {
			notifyAll();
		}
		System.out.println("Lectora "+nombreLectora+" ha terminado de leer.");
		
	}

	private synchronized void accesoLectura(String nombreLectora) throws Exception{
		while(escribiendo||this.entero==null) {
			System.out.println("Lectora "+nombreLectora+" espera para leer.");
			wait();
		}
		numLectoras++;
		System.out.println("Lectora "+nombreLectora+" ha empezado a leer.");
	}
	
	public void escribir(String nombreEscritora) throws InterruptedException {
        this.accesoEscritura(nombreEscritora);
        Integer valorAntiguo = this.entero;
        this.entero = (int) (Math.random() * 10001); 
        System.out.println("Escritora " + nombreEscritora + " escribe. Valor antiguo = " + String.valueOf(valorAntiguo) + ", nuevo = " + this.entero);
        Thread.sleep((long) (Math.random() * 500));
        this.terminaEscritura(nombreEscritora);
    }
    
    private synchronized void accesoEscritura(String nombreEscritora) throws InterruptedException {
    	while (numLectoras > 0 || escribiendo) {
    		System.out.println("... Escritor " + nombreEscritora + " espera para escribir.");
            wait();
        }
        escribiendo = true;
        System.out.println(">>> Escritora " + nombreEscritora + " ha empezado a escribir.");
    }
    
    private synchronized void terminaEscritura(String nombreEscritora) throws InterruptedException {
    	escribiendo = false;
    	notifyAll();
    	System.out.println("<<< Escritora " + nombreEscritora + " ha terminado de escribir.");
    }

}
