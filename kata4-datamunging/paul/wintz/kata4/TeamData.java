package paul.wintz.kata4;

public class TeamData {

	String teamName;
	int pointsFor;
	int pointsAgainst;

	public TeamData(String teamName, int pointsFor, int pointsAgainst) {
		this.teamName = teamName;
		this.pointsFor = pointsFor;
		this.pointsAgainst = pointsAgainst;
	}

	public int pointSpread() {
		return Math.abs(pointsFor - pointsAgainst);
	}

	@Override
	public boolean equals(Object other) {
		if(this == other) return true;
		if(!(other instanceof TeamData)) return false;

		TeamData otherData = (TeamData) other;

		return teamName.equals(otherData.teamName) && pointsFor == otherData.pointsFor && pointsAgainst == otherData.pointsAgainst;
	}

	@Override
	public String toString() {
		return String.format("TeamData{teamName=%s, pointsFor=%d, pointsAgainst=%d", teamName, pointsFor, pointsAgainst);
	}

	static TeamData teamWithSmallerSpread(TeamData team1, TeamData team2) {
		if(team1.pointSpread() <= team2.pointSpread())
			return team1;
		else
			return team2;
	}


}
