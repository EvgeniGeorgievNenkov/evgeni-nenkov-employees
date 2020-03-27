package power;

import power.interfaces.Runnable;
import factories.ProjectFactory;
import reading.FileReading;
import company.Project;
import company.Team;
import work.EmployeeWork;

import java.util.List;
import java.util.stream.Collectors;

public class Power implements Runnable {
	private FileReading fileReading;
	private EmployeeWork empWork;

	public Power(FileReading fileReading, EmployeeWork empWork) {
		this.fileReading = fileReading;
		this.empWork = empWork;
	}

	@Override
	public void run() {
		// Read all projects data from data.txt file.
		List<Project> projects = this.fileReading.read(".\\src\\resources\\data.txt")
				.stream()
				.map(ProjectFactory::perform)
				.collect(Collectors.toList());

		// Save all employee projects
		this.empWork.addEmployeeProjects(projects);

		// Find the teams with overlap.
		List<Team> teams = this.empWork.findAllTeamsWithOverlap();

		printResult(teams);
	}

	private void printResult(List<Team> teams) {
		if (teams.size() != 0) {
			teams.sort((team1, team2) -> (int) (team2.getTotalDays() - team1.getTotalDays()));
			Team bestTeam = teams.get(0);

			System.out.print("The pair of employees has worked for the most time together are: " + "\n");
			System.out.println("First employee: ID -> " + bestTeam.getEmployee2() + "\n" + "Second employee: ID -> "
					+ bestTeam.getEmployee1() + "\n" + "Days worked: " + bestTeam.getTotalDays() + " days");

		} else {
			System.out.print("Doesn't exist pair of employees which has worked together.");
		}
	}
}
