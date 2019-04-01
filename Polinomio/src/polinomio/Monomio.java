package polinomio;

public class Monomio implements Comparable<Monomio>{

	private int coeficiente;
	private int exponente;

	public Monomio(int coeficiente, int exponente) {
		this.coeficiente = coeficiente;
		this.exponente = exponente;
	}

	@Override
	public int compareTo(Monomio o) {
		//Se compara por exponente
		return Integer.compare(this.exponente, o.exponente);
	}
	
	public String toString() {
		if(exponente == 1)
			return coeficiente + "x";
		return (exponente == 0) ? coeficiente + "":coeficiente + "x" + exponente;
	}
	
	public int getCoeficiente() {
		return coeficiente;
	}

	public void setCoeficiente(int coeficiente) {
		this.coeficiente = coeficiente;
	}

	public int getExponente() {
		return exponente;
	}

	public void setExponente(int exponente) {
		this.exponente = exponente;
	}



	

	
}
