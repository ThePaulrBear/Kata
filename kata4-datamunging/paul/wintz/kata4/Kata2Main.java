package paul.wintz.kata4;

import java.io.*;
import java.nio.file.*;
import java.util.Optional;

public class Kata2Main {

	public static void main(String[] args) {
		findSmallestSpread();
		findMinTemperature();
	}

	private static void findSmallestSpread() {
		TeamData teamWithSmallestSpread = findExtremeValue(new TeamDataParser(), new SmallestDifferenceInPointsFinder(), "football.dat");
		System.out.println("The team with the smallestSpread was: " + teamWithSmallestSpread);
	}

	private static void findMinTemperature() {
		WeatherData coldestDay = findExtremeValue(new WeatherDataParser(), new MinTemperatureFinder(), "weather.dat");
		System.out.println("The coldest day was: " + coldestDay);
	}

	private static  <T> T findExtremeValue(DataParser<T> parser, ExtremeValueFinder<T> finder, String filename){
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename))){

			String line;
			while((line = reader.readLine()) != null) {
				Optional<T> parsed = parser.parse(line);
				if(parsed.isPresent()) {
					finder.addDataObject(parsed.get());
				}
			}

		} catch (IOException e) {
			System.err.println("An error occured while trying to read data file. " + e);
		}
		return finder.getExtremeObject();
	}

}
