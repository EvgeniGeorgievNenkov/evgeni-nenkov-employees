package work;

import company.Project;
import company.Team;

import java.util.List;

public interface EmployeeWork {
	void addEmployeeProjects(List<Project> projects);

	List<Team> findAllTeamsWithOverlap();
}
