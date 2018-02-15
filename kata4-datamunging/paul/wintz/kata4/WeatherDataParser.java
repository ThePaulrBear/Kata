package paul.wintz.kata4;

import java.util.List;

import paul.wintz.kata4.ObjectFromStringListMapper.MapFunction;
import paul.wintz.utils.RegexUtils;

//Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP
//
//1  88    59    74          53.8       0.00 F       280  9.6 270  17  1.6  93 23 1004.5
public class WeatherDataParser extends DataParser<WeatherData>{

	private static final ObjectFromStringListMapper<WeatherData> MAPPER = new ObjectFromStringListMapper<>();

	static {
		MAPPER.setMapFunction(new MapFunction<WeatherData>() {

			@Override
			public WeatherData tryToWrangleListIntoObject(List<String> split) {
				int day = Integer.parseInt(split.get(0).replaceAll("\\D", ""));
				int maxTemp = Integer.parseInt(split.get(1).replaceAll("\\D", ""));
				int minTemp = Integer.parseInt(split.get(2).replaceAll("\\D", ""));
				return new WeatherData(day, minTemp, maxTemp);
			}
		});
	}

	public WeatherDataParser(){
		super(new DelimitedStringSplitter(RegexUtils.ANY_NUMBER_OF_SPACES), MAPPER);
	}

}
