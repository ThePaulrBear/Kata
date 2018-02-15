package paul.wintz.kata4;

import paul.wintz.utils.RegexUtils;

public class TeamDataParser extends DataParser<TeamData> {

	private static final ObjectFromStringListMapper<TeamData> MAPPER = new ObjectFromStringListMapper<>();

	static {
		MAPPER.setMapFunction(split -> {
			String name = split.get(1);
			int pointsFor = Integer.parseInt(split.get(6));
			int pointsAgainst = Integer.parseInt(split.get(8));

			return new TeamData(name, pointsFor, pointsAgainst);
		});
	}

	public TeamDataParser() {
		super(new DelimitedStringSplitter(RegexUtils.ANY_NUMBER_OF_SPACES), MAPPER);
	}

}
