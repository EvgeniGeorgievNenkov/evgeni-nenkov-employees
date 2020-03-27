package work;

import factories.TeamFactory;
import company.Project;
import company.Team;
import base.EmployeeBase;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeWorkImpl implements EmployeeWork {

	private EmployeeBase empBase;

	public EmployeeWorkImpl(EmployeeBase employeeBase) {
		this.empBase = employeeBase;
	}

	// Method which save all projects to the database using EmployeeBase.
	
	@Override
	public void addEmployeeProjects(List<Project> projects) {
		this.empBase.saveAll(projects);
	}

	// Method which finding all teams with overlap and save them into
	// List<Team>.
	
	@Override
	public List<Team> findAllTeamsWithOverlap() {
		List<Project> allProjects = this.empBase.getAllProjects();

		List<Team> teams = new ArrayList<>();
		for (int i = 0; i < allProjects.size() - 1; i++) {
			for (int j = i + 1; j < allProjects.size(); j++) {
				Project empFirst = allProjects.get(i);
				Project empSecond = allProjects.get(j);

				if (empFirst.getProjectId() == empSecond.getProjectId() && hasOverlap(empFirst, empSecond)) {
					int overlapDays = calculateOverlap(empFirst, empSecond);

					if (overlapDays > 0) {
						updateTeamCollection(teams, empFirst, empSecond, overlapDays);
					}
				}
			}
		}
		return teams;
	}

	// Method which calculating the total overlap and returning it.
	
	private int calculateOverlap(Project empFirst, Project empSecond) {
		LocalDate periodStartDate = empFirst.getDateFrom().isBefore(empSecond.getDateFrom()) ? empSecond.getDateFrom()
				: empFirst.getDateFrom();

		LocalDate periodEndDate = empFirst.getDateTo().isBefore(empSecond.getDateTo()) ? empFirst.getDateTo()
				: empSecond.getDateTo();

		return (int) Math.abs(ChronoUnit.DAYS.between(periodStartDate, periodEndDate));
	}

	// hasOverlap method returning if two employees have overlap.
	
	private boolean hasOverlap(Project empFirst, Project empSecond) {

		return (empFirst.getDateFrom().isBefore(empSecond.getDateTo())
				|| empFirst.getDateFrom().isEqual(empSecond.getDateTo()))
				&& (empFirst.getDateTo().isAfter(empSecond.getDateFrom())
						|| empFirst.getDateTo().isEqual(empSecond.getDateFrom()));
	}

	// method check and returning if the current team is already present in team.
	
	private boolean isTeamPresent(Team team, int emp1, int emp2) {
		return (team.getEmployee1() == emp1 && team.getEmployee2() == emp2)
				|| (team.getEmployee1() == emp2 && team.getEmployee2() == emp1);
	}

	// If the team is already present, it's total overlap duration will be
	// updated with the new value, otherwise will be create and add new team
	// with the current data.
	
	private void updateTeamCollection(List<Team> teams, Project empFirst, Project empSecond, int overlapDays) {
		AtomicBoolean isPresent = new AtomicBoolean(false);

		teams.forEach(team -> {
			if (isTeamPresent(team, empFirst.getEmpId(), empSecond.getEmpId())) {
				team.addOverlapDays(overlapDays);
				isPresent.set(true);
			}
		});

		if (!isPresent.get()) {
			Team newTeam = TeamFactory.perform(empFirst.getEmpId(), empSecond.getEmpId(), overlapDays);
			teams.add(newTeam);
		}
	}
}
