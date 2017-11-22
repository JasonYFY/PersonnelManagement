package com.jason.web;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.domain.Department;
import com.jason.domain.Employee;
import com.jason.domain.Job;
import com.jason.domain.User;
import com.jason.service.DeptService;
import com.jason.service.EmployeeService;
import com.jason.service.JobService;
import com.jason.service.UserService;
import com.jason.service.domain.EmployeeS;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet(name="/EmployeeController",urlPatterns= {"/Employee_add","/Employee_query",
		"/check_cardId","/Employee_edit","/Employee_update","/Employee_delete"})
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		EmployeeService employeeService = new EmployeeService();
		
		if(action.equals("Employee_add")) {
			String employeeName = request.getParameter("employeeName");
			String cardId = request.getParameter("cardId");
			String jobId = request.getParameter("jobId");
			String sex = request.getParameter("sex");
			String education = request.getParameter("education");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String party = request.getParameter("party");
			String qqNum = request.getParameter("qqNum");
			String address = request.getParameter("address");
			String postCode = request.getParameter("postCode");
			String birthday = request.getParameter("birthday");
			String race = request.getParameter("race");
			String speciality = request.getParameter("speciality");
			String hobby = request.getParameter("hobby");
			String remark = request.getParameter("remark");
			String deptId = request.getParameter("deptId");
			String phone = request.getParameter("phone");
			
			Employee e = new Employee();
			e.setAddress(address);
			if(!birthday.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date parse;
				try {
					parse = sdf.parse(birthday);
					e.setBirthday(parse);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			e.setCardId(cardId);
			e.setCreateDate(new Date());
			e.setEducation(education);
			e.setEmail(email);
			e.setEmployeeName(employeeName);
			e.setHobby(hobby);
			e.setDeptId(Integer.parseInt(deptId));
			e.setJobId(Integer.parseInt(jobId));
			e.setParty(party);
			e.setPhone(phone);
			e.setPostCode(postCode);
			e.setQqNum(qqNum);
			e.setRace(race);
			e.setRemark(remark);
			e.setSex(Integer.parseInt(sex));
			e.setSpeciality(speciality);
			e.setTel(tel);
			
			boolean pos = employeeService.addEmployee(e);
			if(pos) {
				request.setAttribute("errorA", "no");
			}else {
				request.setAttribute("errorA", "yes");
			}
			request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
			
		}else if(action.equals("Employee_query")) {
			String employeeName = request.getParameter("employeeName");
			String cardId = request.getParameter("cardId");
			String jobId = request.getParameter("jobId");
			String sex = request.getParameter("sex");
			String deptId = request.getParameter("deptId");
			String phone = request.getParameter("phone");
			
			if(employeeName.equals("")&&cardId.equals("")&&jobId.equals("")
					&&sex.equals("")&&deptId.equals("")&&phone.equals("")) {
				ArrayList<EmployeeS> allEmployee = employeeService.getAllEmployee();
				request.setAttribute("empList", allEmployee);
				request.getRequestDispatcher("/queryEmployee.jsp").forward(request, response);
			}else {
				Employee emp = new Employee();
				emp.setEmployeeName(employeeName);
				emp.setCardId(cardId);
				if(!jobId.equals(""))
				emp.setJobId(Integer.parseInt(jobId));
				if(!sex.equals(""))
				emp.setSex(Integer.parseInt(sex));
				if(!deptId.equals(""))
				emp.setDeptId(Integer.parseInt(deptId));
				emp.setPhone(phone);
				ArrayList<EmployeeS> list = employeeService.queryEmployee(emp);
				request.setAttribute("empList", list);
				request.getRequestDispatcher("/queryEmployee.jsp").forward(request, response);
			}
		}else if(action.equals("check_cardId")) {
			response.setContentType("application/json;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String cardId = request.getParameter("cardId");
	        Employee e = new Employee();
	        e.setCardId(cardId);
	        boolean pos = employeeService.isSameCardId(e);
	        if(pos) {
	        	 out.print("身份证已被使用");
	        }else {
	        	out.print("身份证可以使用");
	        }
		}else if(action.equals("Employee_edit")) {
			String id = request.getParameter("id");
			Employee emp = new Employee();
			emp.setId(Integer.parseInt(id));
			ArrayList<EmployeeS> list = employeeService.queryEmployee(emp);
			if(list!=null&&list.size()>0)
				request.setAttribute("emp", list.get(0));
			request.getRequestDispatcher("/updateEmployee.jsp").forward(request, response);
		}else if(action.equals("Employee_update")) {
			String employeeName = request.getParameter("employeeName");
			String cardId = request.getParameter("cardId");
			String jobId = request.getParameter("jobId");
			String sex = request.getParameter("sex");
			String education = request.getParameter("education");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String party = request.getParameter("party");
			String qqNum = request.getParameter("qqNum");
			String address = request.getParameter("address");
			String postCode = request.getParameter("postCode");
			String birthday = request.getParameter("birthday");
			String race = request.getParameter("race");
			String speciality = request.getParameter("speciality");
			String hobby = request.getParameter("hobby");
			String remark = request.getParameter("remark");
			String deptId = request.getParameter("deptId");
			String phone = request.getParameter("phone");
			String id = request.getParameter("id");
			
			Employee e = new Employee();
			e.setId(Integer.parseInt(id));
			e.setAddress(address);
			if(birthday!=null&&!birthday.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sdf.parse(birthday);
					e.setBirthday(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			e.setCardId(cardId);
			e.setCreateDate(new Date());
			e.setEducation(education);
			e.setEmail(email);
			e.setEmployeeName(employeeName);
			e.setHobby(hobby);
			e.setDeptId(Integer.parseInt(deptId));
			e.setJobId(Integer.parseInt(jobId));
			e.setParty(party);
			e.setPhone(phone);
			e.setPostCode(postCode);
			e.setQqNum(qqNum);
			e.setRace(race);
			e.setRemark(remark);
			e.setSex(Integer.parseInt(sex));
			e.setSpeciality(speciality);
			e.setTel(tel);
			
			boolean pos = employeeService.udateEmployee(e);
			if(pos) {
				ArrayList<EmployeeS> list = employeeService.queryEmployee(e);
				if(list!=null&&list.size()>0)
					request.setAttribute("emp", list.get(0));
				request.setAttribute("errorU", "no");
			}else {
				request.setAttribute("errorU", "yes");
			}
			request.getRequestDispatcher("/updateEmployee.jsp").forward(request, response);
		}else if(action.equals("Employee_delete")) {
			String[] ids = request.getParameterValues("employeeId");
			if(ids.length==0)
				request.setAttribute("errorD", "noC");
			else
				request.setAttribute("errorD", "no");
			for(String s: ids ) {
				Employee e = new Employee();
				e.setId(Integer.parseInt(s));
				boolean pos = employeeService.deleteEmployee(e);
				if(!pos) {
					request.setAttribute("errorD", "yes");
					request.getRequestDispatcher("/queryEmployee.jsp").forward(request, response);
					return;
				}
			}
			ArrayList<EmployeeS> allEmployee = employeeService.getAllEmployee();
			request.setAttribute("empList", allEmployee);
			request.getRequestDispatcher("/queryEmployee.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
