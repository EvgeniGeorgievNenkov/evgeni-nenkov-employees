package main;

import power.Power;
import reading.FileReading;
import reading.FileReadingImpl;

import base.EmployeeBase;
import base.EmployeeBaseImpl;
import work.EmployeeWork;
import work.EmployeeWorkImpl;

public class LongestPeriod {
	public static void main(String[] args) {

		FileReading fileReading = new FileReadingImpl();
		EmployeeBase employeeBase = new EmployeeBaseImpl();
		EmployeeWork empWork = new EmployeeWorkImpl(employeeBase);

		Power power = new Power(fileReading, empWork);
		power.run();
	}
}
