package polinomio;

import java.util.Comparator;

public class CustomComparator implements Comparator<Monomio>{

    @Override
    public int compare(Monomio o1, Monomio o2) {
		return -o1.compareTo(o2);
    }
} 
