package factories;

import company.Team;

public final class TeamFactory {

	public TeamFactory() {

	}

	public static Team perform(int Employee1, int Employee2, int overlapDays) {
		return new Team(Employee1, Employee2, overlapDays);
	}
}
