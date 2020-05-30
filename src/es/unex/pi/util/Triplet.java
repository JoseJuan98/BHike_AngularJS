package es.unex.pi.util;

public class Triplet<F, S, T> {
	private F first; // first member of triplet
	private S second; // second member of triplet
	private T third; // third member of triplet

	public Triplet(F first, S second, T third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public void setFirst(F first) {
		this.first = first;
	}

	public void setSecond(S second) {
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}
	
	public T getThird() {
		return third;
	}

	public void setThird(T third) {
		this.third = third;
	}

}
