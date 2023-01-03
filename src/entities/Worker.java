package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private WorkerLevel level;
	private Double baseSalary;
	
	//Associations
	private Department department;
	
	//Compositions (one has many)
	private List<HourContract> contracts = new ArrayList<>();
	
	public Worker() {
		
	}

	//Constructors are generated without list-type attributes -- just initialize the list empity
	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		super();
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}
	
	// We mustn't allow the setContracts() method if we do not want to switch the contract list
	public void addContract(HourContract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(HourContract contract) {
		this.contracts.remove(contract);
	}
	//Obs: Calendar.MONTH starts at 0
	public double income(int year, int month) {
		double sum = this.baseSalary;
		Calendar cal = Calendar.getInstance();
		for (HourContract c : this.contracts) {
			cal.setTime(c.getDate());
			if(cal.get(Calendar.YEAR)==year && cal.get(Calendar.MONTH)+1==month)
				sum+= c.totalValue();
		}
		return sum;
	}
	
}
