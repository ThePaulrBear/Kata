package paul.wintz.kata4;

import static com.google.common.base.Preconditions.*;

public class ExtremeValueFinder<T> {

	private final Comparator<T> comparator;
	private T current;

	public interface Comparator<T> {
		boolean isMoreExtremeThanCurrent(T canidate, T current);
	}

	public ExtremeValueFinder(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T getExtremeObject() {
		checkState(current != null, "No Objects to Compare Added");
		return current;
	}

	public void addDataObject(T data) {
		checkNotNull(data);
		if(current == null) {
			current = data;
			return;
		}

		if(comparator.isMoreExtremeThanCurrent(data, current)) {
			current = data;
		}

	}

}