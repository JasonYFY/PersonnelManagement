package com.jason.service;

import java.util.ArrayList;


import com.jason.dao.DaoFactory;
import com.jason.dao.EmployeeDao;
import com.jason.domain.Department;
import com.jason.domain.Employee;
import com.jason.domain.Job;
import com.jason.service.domain.EmployeeS;

public class EmployeeService {
	private EmployeeDao empDao;
	public EmployeeService() {
		empDao = DaoFactory.getEmployeeDao();
	}
	public ArrayList<EmployeeS> getAllEmployee() {
		ArrayList<Employee> allEmployee = empDao.queryAllEmployee();
		ArrayList<EmployeeS> allList = new ArrayList<>();
		if(allEmployee!=null) {
			for(Employee e :allEmployee) {
				Department d = new Department();
				Job j = new Job();
				j.setId(e.getJobId());
				d.setId(e.getDeptId());
				ArrayList<Department> dept = new DeptService().getDepartments(d);
				ArrayList<Job> jobs = new JobService().getJobs(j);
				EmployeeS em  = new EmployeeS();
				em.setEmployee(e);
				if(dept!=null&&dept.size()>0)
				em.setDept(dept.get(0));
				if(jobs!=null&&jobs.size()>0)
				em.setJob(jobs.get(0));
				allList.add(em);
			}
		}
		return allList;
	}
	public ArrayList<EmployeeS> queryEmployee(Employee emp){
		ArrayList<Employee> list = empDao.queryEmployee(emp);
		ArrayList<EmployeeS> allList = new ArrayList<>();
		if(list!=null&&list.size()>0) {
			for(Employee e :list) {
				Department d = new Department();
				Job j = new Job();
				j.setId(e.getJobId());
				d.setId(e.getDeptId());
				ArrayList<Department> dept = new DeptService().getDepartments(d);
				ArrayList<Job> jobs = new JobService().getJobs(j);
				EmployeeS em  = new EmployeeS();
				em.setEmployee(e);
				if(dept!=null&&dept.size()>0)
				em.setDept(dept.get(0));
				if(jobs!=null&&jobs.size()>0)
				em.setJob(jobs.get(0));
				allList.add(em);
			}
			return allList;
		}
		return null;
	}
	public boolean deleteEmployee(Employee emp) {
		int pos = empDao.deleteEmployee(emp);
		if(pos>0) {
			return true;
		}
		return false;
	}
	
	public boolean addEmployee(Employee emp) {
		int pos = empDao.insertEmployee(emp);
		if(pos>0) {
			return true;
		}
		return false;
	}
	
	public boolean udateEmployee(Employee emp) {
		int pos = empDao.updateEmployee(emp);
		if(pos>0) {
			return true;
		}
		return false;
	}
	public boolean isSameCardId(Employee emp) {
		return empDao.isSameCardId(emp);
	}

}
