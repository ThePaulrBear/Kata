package paul.wintz.kata4;

public class MinTemperatureFinder extends ExtremeValueFinder<WeatherData> {

	public MinTemperatureFinder() {
		super((canidate, current) -> {

			if(canidate.minTemp < current.minTemp)
				return true;

			// If two days have the same coldest temperature,
			// then the first one holds the record.
			if (canidate.minTemp == current.minTemp
					&& canidate.day < current.day)
				return true;

			return false;
		});

	}

}
