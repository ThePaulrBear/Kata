package paul.wintz.kata4;

import static com.google.common.base.Preconditions.*;

import java.util.*;
import java.util.stream.Collectors;

public class DelimitedStringSplitter {

	private String delimiter;

	public DelimitedStringSplitter(String delimiter) {
		checkArgument(!delimiter.isEmpty());
		setDelimiter(delimiter);
	}

	public List<String> split(String toSplit) {
		return Arrays.stream(toSplit.split(delimiter))
				.map(String::trim)
				.filter(s -> !s.isEmpty())
				.collect(Collectors.toList());
	}

	public final void setDelimiter(String delimiter) {
		this.delimiter = checkNotNull(delimiter);
	}
}
