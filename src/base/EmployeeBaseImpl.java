package base;

import company.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeBaseImpl implements EmployeeBase {
	private List<Project> database;

	public EmployeeBaseImpl() {
		this.database = new ArrayList<>();
	}

	@Override
	public void save(Project project) {
		this.database.add(project);
	}

	@Override
	public void saveAll(Collection<Project> projects) {
		this.database.addAll(projects);
	}

	@Override
	public List<Project> getAllProjects() {
		return Collections.unmodifiableList(this.database);
	}
}
