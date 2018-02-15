package paul.wintz.kata4;

import static org.hamcrest.CoreMatchers.*;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

import paul.wintz.kata4.*;
import paul.wintz.utils.RegexUtils;

public class ADataParser {

	DelimitedStringSplitter splitter = new DelimitedStringSplitter("\t");
	ObjectFromStringListMapper<String> wrangler = new ObjectFromStringListMapper<>();
	DataParser<String> parser = new DataParser<>(splitter, wrangler);

	@Test
	public void returnsEmptyIfUnparsable() {
		wrangler.setMapFunction(split -> {throw new IllegalArgumentException();});

		Optional<String> result = parser.parse("unparsable");

		assertFalse(result.isPresent());
	}

	@Test(expected = NullPointerException.class)
	public void throwsWhenStringIsNull() {
		parser.parse(null);
	}

	@Test
	public void willCreateObject() {
		wrangler.setMapFunction(List<String>::toString);

		Optional<String> result = parser.parse("1\t2\t3\t4");

		assertThat(result.get(), is(equalTo("[1, 2, 3, 4]")));
	}

	@Test
	public void parsesRegexDelimiters() {
		wrangler.setMapFunction(List<String>::toString);
		splitter.setDelimiter(RegexUtils.ANY_NUMBER_OF_SPACES);

		Optional<String> result = parser.parse("1 2  3   4");

		assertThat(result.get(), is(equalTo("[1, 2, 3, 4]")));
	}

}
