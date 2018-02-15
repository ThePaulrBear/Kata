package paul.wintz.kata4;

import static com.google.common.base.Preconditions.checkArgument;

public class WeatherData {

	//Dy MxT   MnT   AvT   HDDay  AvDP 1HrP TPcpn WxType PDir AvSp Dir MxS SkyC MxR MnR AvSLP
	int day;
	int minTemp;
	int maxTemp;

	public WeatherData(int day, int minTemp, int maxTemp) {
		checkArgument(minTemp <= maxTemp, "min was not <= max");
		this.day = day;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(!(other instanceof WeatherData)) return false;

		WeatherData otherData = (WeatherData) other;

		return day == otherData.day && minTemp == otherData.minTemp && maxTemp == otherData.maxTemp;
	}

	@Override
	public String toString() {
		return String.format("WeatherData{day=%d, minTemp=%d, maxTemp=%d", day, minTemp, maxTemp);
	}



}
