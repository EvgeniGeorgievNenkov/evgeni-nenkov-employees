package base;

import company.Project;

import java.util.Collection;
import java.util.List;

public interface EmployeeBase {
	void save(Project project);

	void saveAll(Collection<Project> projects);

	List<Project> getAllProjects();
}
