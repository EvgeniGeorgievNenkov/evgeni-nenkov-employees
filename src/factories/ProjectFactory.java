package factories;

import company.Project;

import java.time.LocalDate;

public final class ProjectFactory {

	public ProjectFactory() {
	}

	public static Project perform(String line) {
		String[] projectArgs = line.split(", ");

		int empID = Integer.parseInt(projectArgs[0].trim());
		int projectID = Integer.parseInt(projectArgs[1].trim());

		LocalDate dateFrom = LocalDate.parse(projectArgs[2]);

		LocalDate dateTo;
		if (projectArgs[3] == null || "NULL".equals(projectArgs[3])) {
			dateTo = LocalDate.now();
		} else {
			dateTo = LocalDate.parse(projectArgs[3]);
		}

		return new Project(empID, projectID, dateFrom, dateTo);
	}
}
