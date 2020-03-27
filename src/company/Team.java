package company;

public class Team {

	private int employee1;
	private int employee2;
	private int totalDays;

	public Team(int employee1, int employee2, int totalDays) {
		this.employee1 = employee1;
		this.employee2 = employee2;
		this.totalDays = totalDays;
	}

	public int getEmployee1() {
		return employee1;
	}

	public void setEmployee1(int employee1) {
		this.employee1 = employee1;
	}

	public int getEmployee2() {
		return employee2;
	}

	public void setEmployee2(int employee2) {
		this.employee2 = employee2;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public void addOverlapDays(long overlap) {
		this.totalDays += overlap;
	}
}
