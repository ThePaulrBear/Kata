package paul.wintz.kata4;

import java.util.*;

public class ObjectFromStringListMapper<T> {

	public interface MapFunction<T> {
		T tryToWrangleListIntoObject(List<String> split);
	}

	private MapFunction<T> mapFunction;

	public final Optional<T> dataObjectFromList(List<String> split){
		try {
			return Optional.of(mapFunction.tryToWrangleListIntoObject(split));
		} catch (Exception e) {
			System.err.println("Could not parse an object from: " + split + ", because of " + e);
			return Optional.empty();
		}
	}

	public void setMapFunction(MapFunction<T> mapFunction) {
		this.mapFunction = mapFunction;
	}

}