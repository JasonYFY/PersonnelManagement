package com.jason.service;

import java.util.ArrayList;

import com.jason.dao.DaoFactory;
import com.jason.dao.DeptDao;
import com.jason.dao.Interface.IDeptDao;
import com.jason.domain.Department;

public class DeptService {
	private IDeptDao deptDao;
	public DeptService() {
		deptDao = DaoFactory.getDeptDao();
	}
	public boolean addDepartment(Department dept) {
		if(!isSameDeptName(dept)) {
			int pos = deptDao.insertDepartment(dept);
			if(pos>0) {
				return true;
			}
		}
		return false;
		
	}
	public boolean DeleteDepartment(Department dept) {
		int pos = deptDao.deleteDepartment(dept);
		if(pos>0) {
			return true;
		}
		return false;
		
	}
	public boolean udateDepartment(Department dept) {
		int pos = deptDao.updateDepartment(dept);
		if(pos>0) {
			return true;
		}
		return false;
		
	}
	public ArrayList<Department> getDepartments(Department dept){
		ArrayList<Department> list = deptDao.queryDepartment(dept);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	public ArrayList<Department> getAllDepartments(){
		DeptDao dao = (DeptDao)deptDao;
		return dao.queryAllDepartment();
	}
	public boolean isSameDeptName(Department dept) {
		DeptDao dao = (DeptDao)deptDao;
		return dao.querySame(dept);
	}

}
