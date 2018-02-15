package paul.wintz.kata4;


import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import paul.wintz.kata4.DelimitedStringSplitter;
import paul.wintz.utils.RegexUtils;

public class ADelimitedStringSplitter {

	DelimitedStringSplitter splitter = new DelimitedStringSplitter(",");

	@Test(expected = NullPointerException.class)
	public final void throwsIfNullDelimiter() {
		new DelimitedStringSplitter(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsIfEmptyDelimiter() {
		new DelimitedStringSplitter("");
	}

	@Test(expected = NullPointerException.class)
	public void throwsIfPassedNull() {
		new DelimitedStringSplitter(" ").split(null);
	}

	@Test
	public void doesNotSplitWhenThereAreNoDelimiters() {
		splitter.setDelimiter(" ");

		List<String> split = splitter.split("word");

		assertThat(split,is(equalTo(asList("word"))));
	}

	@Test
	public void splitsCSV() {
		splitter.setDelimiter(",");

		List<String> split = splitter.split("apple,orange,banana");

		assertThat(split,is(equalTo(asList("apple","orange","banana"))));
	}

	@Test
	public void splitsTabDelimiter() {
		splitter.setDelimiter("\t");

		List<String> split = splitter.split("apple\torange\tbanana");

		assertThat(split,is(equalTo(asList("apple","orange","banana"))));
	}

	@Test
	public void splitsRegexDelimiter() {
		splitter.setDelimiter(RegexUtils.ANY_NUMBER_OF_SPACES);

		List<String> split = splitter.split("Four score            and  seven       years ago...");

		assertThat(split,is(equalTo(asList("Four", "score", "and", "seven", "years", "ago..."))));
	}

	@Test
	public void ommitEmptyElements() {
		splitter.setDelimiter(",");

		List<String> split = splitter.split(",apple,,orange,banana");

		assertThat(split,is(equalTo(asList("apple","orange","banana"))));
	}

	@Test
	public void trimsWhitespaceForEachElement() {
		splitter.setDelimiter(",");

		List<String> result = splitter.split(" , 1,\t2,  3,\n4 ");

		assertThat(result, is(equalTo(asList("1", "2", "3", "4"))));
	}

}
