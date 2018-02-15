package paul.wintz.kata4;


import static org.hamcrest.CoreMatchers.*;

import java.util.Optional;

import org.junit.Test;

import static org.junit.Assert.*;

import paul.wintz.kata4.*;

public final class AWeatherDataParser {

	WeatherDataParser parser = new WeatherDataParser();

	@Test
	public final void returnsEmptyIfDataNotInNumericFormat() {
		Optional<WeatherData> result = parser.parse("a b c");

		assertFalse(result.isPresent());
	}

	@Test
	public void returnsWeatherData() {
		Optional<WeatherData> data = parser.parse("  5  72  32");

		assertThat(data.get(), is(equalTo(new WeatherData(5, 32, 72))));
	}

	/*
	 * Needs to read a line of delimiter-separated data and produce a data-object
	 * that contains each of the fields.
	 */
}
