package polinomio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Polinomio {

	private ArrayList<Monomio> polinomio;

	public Polinomio() {
		polinomio = new ArrayList<>();
	}

	public String toString() {
		return polinomio.toString();
	}

	public ArrayList<Monomio> sumar(ArrayList<Monomio> p2) {

		Polinomio polResult = new Polinomio();
		polResult.setPolinomio(polinomio);
		Monomio aux = null;
		for (Monomio monomio : p2) {
			aux = polResult.find(monomio);
			if (aux != null) {
				polResult.find(monomio).setCoeficiente(aux.getCoeficiente() + monomio.getCoeficiente());
			} else
				polResult.getPolinomio().add(monomio);
		}
		return polResult.getPolinomio();

	}

	public ArrayList<Monomio> multiplicar(ArrayList<Monomio> p2) {
		Polinomio resultado = new Polinomio();
		Monomio mult = null, aux = null;
		for (Monomio m1 : polinomio) {
			for (Monomio m2 : p2) {
				mult = new Monomio(m1.getCoeficiente() * m2.getCoeficiente(), m1.getExponente() + m2.getExponente());
				aux = resultado.find(mult);
				if (aux != null)
					resultado.find(mult).setCoeficiente(aux.getCoeficiente() + mult.getCoeficiente());
				else
					resultado.getPolinomio().add(mult);
			}
		}
		return resultado.getPolinomio();

	}

	public ArrayList<Monomio> dividir(Polinomio p2) {

		Polinomio remainder = new Polinomio();
		Polinomio quotient = new Polinomio();

		quotient.getPolinomio()
				.add(new Monomio(polinomio.get(0).getCoeficiente() / p2.getPolinomio().get(0).getCoeficiente(),
						polinomio.get(0).getExponente() - p2.getPolinomio().get(0).getExponente()));
		remainder.setPolinomio(sustraccion(polinomio, p2.multiplicar(quotient.getPolinomio())));
		if (remainder.getPolinomio().isEmpty())
			return quotient.getPolinomio();
		remainder.deleteZeros();
		remainder.getPolinomio().sort(new CustomComparator());
		do {
			quotient.getPolinomio()
					.add(new Monomio(
							remainder.getPolinomio().get(0).getCoeficiente()
									/ p2.getPolinomio().get(0).getCoeficiente(),
							remainder.getPolinomio().get(0).getExponente() - p2.getPolinomio().get(0).getExponente()));
			remainder.setPolinomio(sustraccion(polinomio, p2.multiplicar(quotient.getPolinomio())));
			if (remainder.getPolinomio().isEmpty())
				return quotient.getPolinomio();

			remainder.deleteZeros();
			remainder.getPolinomio().sort(new CustomComparator());

		} while (remainder.getPolinomio().get(0).getExponente() >= p2.getPolinomio().get(0).getExponente());

		return quotient.getPolinomio();

	}

	public ArrayList<Monomio> sustraccion(ArrayList<Monomio> pole, ArrayList<Monomio> b) {

		Polinomio result = new Polinomio();
		ArrayList<Integer> index = new ArrayList<Integer>();
		int coef;
		boolean found;

		for (int i = 0; i < pole.size(); i++) {
			found = false;
			for (int j = 0; j < b.size(); j++) {
				if (pole.get(i).getExponente() == b.get(j).getExponente()) {
					coef = pole.get(i).getCoeficiente() - b.get(j).getCoeficiente();
					result.getPolinomio().add(new Monomio(coef, pole.get(i).getExponente()));
					found = true;
					index.add(j);
					break;
				}
			}
			if (found == false)
				result.getPolinomio().add(new Monomio(pole.get(i).getCoeficiente(), pole.get(i).getExponente()));

		}
		for (int i = 0; i < b.size(); i++) {
			if (index.contains(i)) {
				continue;
			} else {
				result.getPolinomio().add(new Monomio(-b.get(i).getCoeficiente(), b.get(i).getExponente()));
			}
		}

		result.getPolinomio().sort(new CustomComparator());
		result.deleteZeros();
		return result.getPolinomio();
	}

	public void deleteZeros() {

		Iterator<Monomio> it = this.polinomio.iterator();
		while (it.hasNext()) {
			Monomio mon = it.next();
			if (mon.getCoeficiente() == 0) {
				it.remove();
			}
		}
	}

	public Monomio find(Monomio m) {

		Monomio resultado = null;
		for (Monomio monomio : polinomio) {
			if (monomio.compareTo(m) == 0)
				resultado = monomio;
		}
		return resultado;
	}

	public boolean contains(Monomio m) {

		boolean resultado = false;
		for (Monomio monomio : polinomio) {
			resultado = monomio.compareTo(m) == 0;
		}
		return resultado;
	}

	public ArrayList<Monomio> getPolinomio() {
		return polinomio;
	}

	public void setPolinomio(ArrayList<Monomio> polinomio1) {
		this.polinomio = polinomio1;
	}

}
