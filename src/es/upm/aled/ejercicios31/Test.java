package es.upm.aled.ejercicios31;

/**
 * Ejercicio 1: Main
 */
public class Test {
	// Configuración del número de hebras
	private static final int NUM_LECTORAS = 300;
	private static final int RATIO_LECTORAS_ESCRITORAS = 10;

	public static void main(String[] args) {
		// Recurso compartido (monitor)
		Recurso recurso = new Recurso();

		System.out.println("Iniciando: " + NUM_LECTORAS + " lectoras y " +
							NUM_LECTORAS / RATIO_LECTORAS_ESCRITORAS + " escritoras.");
		try {
			// Crear y arrancar hebras
			for (int i = 0; i < NUM_LECTORAS; i++) {
				Thread t = new Lectora(recurso, "L" + i);
				t.start();
				// 1 escritora por cada N lectoras
				if (i % RATIO_LECTORAS_ESCRITORAS == 0) {
					Thread t2 = new Escritora(recurso, "E" + i);
					t2.start();
				}
				// Esperar hasta 500 ms entre creación de hebras.
				Thread.sleep((long) (Math.random() * 500));
			}
		} catch (InterruptedException e) {}
	}
}