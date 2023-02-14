package no.hvl.dat102.mengde.kjedet;

/**
 * Representerer en node.
 * @param <T> Elementtype i node
 */
class LinearNode<T> {
	private LinearNode<T> neste;
	private T element;

	/**
	 * Oppretter en tom node
	 */
	public LinearNode() {
		neste = null;
		element = null;
	}

	/**
	 * Oppretter en node med et element.
	 * @param elem element å legge inn i node
	 */
	public LinearNode(T elem) {
		neste = null;
		element = elem;
	}

	/**
	 * Returnerer etterfølger.
	 * @return referanse til neste node
	 */
	public LinearNode<T> getNeste() {
		return neste;
	}

	/**
	 * Setter neste til node
	 * @param node denne noden sin neste node
	 */
	public void setNeste(LinearNode<T> node) {
		neste = node;
	}

	/**
	 * Returnerer elementet til denne noden
	 * @return Element som er i noden
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Setter nytt element i denne noden.
	 * @param elem Element som skal settes i noden
	 */
	public void setElement(T elem) {
		element = elem;
	}

}
