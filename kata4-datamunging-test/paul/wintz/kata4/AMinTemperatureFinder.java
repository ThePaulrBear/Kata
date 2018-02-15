package paul.wintz.kata4;


import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import paul.wintz.kata4.*;

public class AMinTemperatureFinder {

	MinTemperatureFinder minTempFinder = new MinTemperatureFinder();

	@Test(expected = IllegalStateException.class)
	public final void throwsIfNoData() {
		minTempFinder.getExtremeObject();
	}

	@Test(expected = NullPointerException.class)
	public void canAddData() {
		minTempFinder.addDataObject(null);
	}

	@Test
	public void returnsDataOfOnlyDayAdded() {
		minTempFinder.addDataObject(new WeatherData(1, 23, 65));

		WeatherData day = minTempFinder.getExtremeObject();

		assertThat(day, is(equalTo(new WeatherData(1, 23, 65))));
	}

	@Test
	public void returnsOnlyColdestDayIfMultipleDaysAdded() {
		minTempFinder.addDataObject(new WeatherData(1, 56, 65));
		minTempFinder.addDataObject(new WeatherData(2, 32, 65)); //coldest
		minTempFinder.addDataObject(new WeatherData(3, 43, 65));

		WeatherData day = minTempFinder.getExtremeObject();

		assertThat(day, is(equalTo(new WeatherData(2, 32, 65))));
	}

	@Test
	public void returnsOnlyFirstColdestDayIfMultipleDaysWithSameMinimum() {
		minTempFinder.addDataObject(new WeatherData(5, 45, 67));
		minTempFinder.addDataObject(new WeatherData(1, 45, 81)); //first day
		minTempFinder.addDataObject(new WeatherData(3, 45, 49));

		WeatherData day = minTempFinder.getExtremeObject();

		assertThat(day, is(equalTo(new WeatherData(1, 45, 81))));
	}

}
