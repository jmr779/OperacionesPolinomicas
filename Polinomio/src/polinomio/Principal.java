package polinomio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static Polinomio p1 = new Polinomio();
	public static Polinomio p2 = new Polinomio();
	static File archivo = new File("src/polinomio/archivo.txt");

	public static void main(String[] args) {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";

		leerArchivo();

		System.out.println("Elige Operación: 1.SUMA 2.MULTIPLICAR 3.DIVIDIR");
		try {
			linea = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		elegirOperacion(linea);

		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIN EJECUCIÓN");
	}

	private static void elegirOperacion(String linea) {
		// TODO Auto-generated method stub
		switch (linea) {
		case "1":
			ArrayList<Monomio> resultado = p1.sumar(p2.getPolinomio());
			System.out.println("Resultado de Sumar --> " + resultado.toString());
			break;
		case "2":
			System.out.println("Resultado de Multiplicar --> " + p1.multiplicar(p2.getPolinomio()).toString());
			break;
		case "3":
			if (p2.getPolinomio().size() > 2 || p2.getPolinomio().get(0).getExponente() > 1) {
				System.out.println(
						"No es posible realizar la división debido al segundo polinomio. Debe ser del tipo x-a");
				break;
			}
			System.out.println("Resultado de Dividir --> " + p1.dividir(p2).toString());
			break;
		default:
			break;
		}
	}

	public static void leerArchivo() {

		String[] items = null;
		String linea;
		Scanner scan = null;
		Monomio aux = null;
		int coef;
		try {
			scan = new Scanner(archivo);
		} catch (IOException e) {
			System.exit(-1);
		}
		
		while (scan.hasNextLine()) {
			linea = scan.nextLine();
			if (linea.isEmpty() || linea.startsWith("@"))
				continue;
			if (linea.startsWith("#")) {
				p1.setPolinomio(p2.getPolinomio());
				p2.setPolinomio(new ArrayList<Monomio>());
				continue;
			}

			items = linea.split(" ");
			// Si cociente = 0, exponente negativo o sobra información, salta la línea
			if (items.length == 1 || items.length > 2 || items[0].equals("0") || Integer.parseInt(items[1]) < 0)
				continue;
			if (items.length == 2) {
				aux = new Monomio(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
				if(p2.find(aux) != null) {
					coef = p2.find(aux).getCoeficiente();
					p2.find(aux).setCoeficiente(coef + Integer.parseInt(items[0]));
					continue;
				}
				p2.getPolinomio().add(new Monomio(Integer.parseInt(items[0]), Integer.parseInt(items[1])));

			}
		}
		System.out.println("Polinomio1 -> " + p1.toString());
		System.out.println("Polinomio2 -> " + p2.toString());
		scan.close();
	}

}
