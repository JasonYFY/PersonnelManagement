package com.jason.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.domain.Department;
import com.jason.service.DeptService;

/**
 * Servlet implementation class DeptController
 */
@WebServlet(name="/DeptController", urlPatterns = { "/dept_add","/dept_query",
		"/dept_delete","/dept_edit","/dept_update","/check_deptName"})
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		DeptService deptService = new DeptService();
		
		if (action.equals("dept_add")) {
			String deptName = request.getParameter("deptName");
			String deptRemark = request.getParameter("deptRemark");
			if(deptName.equals("")) {
				request.getRequestDispatcher("/addDept.jsp").forward(request, response);
				return;
			}
			Department dept = new Department();
			dept.setDeptName(deptName);
			dept.setRemark(deptRemark);
			boolean pos = deptService.addDepartment(dept);
			if(pos) {
				request.setAttribute("errorD", "no");
			}else {
				request.setAttribute("errorD", "yes");
			}
			request.getRequestDispatcher("/addDept.jsp").forward(request, response);
		}else if(action.equals("dept_query")) {
			String deptName = request.getParameter("deptName");
			if(deptName.equals("")) {
				ArrayList<Department> deptList = deptService.getAllDepartments();
				request.setAttribute("deptList", deptList);
				request.getRequestDispatcher("/queryDept.jsp").forward(request, response);
			}else {
				Department dept = new Department();
				dept.setDeptName(deptName);
				ArrayList<Department> deptList = deptService.getDepartments(dept);
				if(deptList!=null) {
					//查询结果
					request.setAttribute("deptList", deptList);
				}
				request.getRequestDispatcher("/queryDept.jsp").forward(request, response);
				
			}
		}else if(action.equals("dept_delete")) {
			String[] ids = request.getParameterValues("deptId");
			if(ids!=null&&ids.length>0) {
				for(String s:ids) {
					Department dept = new Department();
					dept.setId(Integer.parseInt(s));
					boolean pos = deptService.DeleteDepartment(dept);
					if(!pos) {
						ArrayList<Department> deptList = deptService.getAllDepartments();
						request.setAttribute("deptList", deptList);
						request.setAttribute("errorDelete", "yes");
						request.getRequestDispatcher("/queryDept.jsp").forward(request, response);
						return;
					}
				}
				ArrayList<Department> deptList = deptService.getAllDepartments();
				request.setAttribute("deptList", deptList);
				request.setAttribute("errorDelete", "ok");
				request.getRequestDispatcher("/queryDept.jsp").forward(request, response);
			}else {
				ArrayList<Department> deptList = deptService.getAllDepartments();
				request.setAttribute("deptList", deptList);
				request.setAttribute("errorDelete", "no");
				request.getRequestDispatcher("/queryDept.jsp").forward(request, response);
			}
		}else if(action.equals("dept_edit")) {
			String id = request.getParameter("id");
			Department dept = new Department();
			dept.setId(Integer.parseInt(id));
			ArrayList<Department> list = deptService.getDepartments(dept);
			if(list!=null&&list.size()>0) {
				Department d = list.get(0);
				request.setAttribute("dept", d);
				request.getRequestDispatcher("/updateDept.jsp").forward(request, response);
			}
		}else if(action.equals("dept_update")) {
			String id = request.getParameter("id");
			String deptName = request.getParameter("deptName");
			String deptRemark = request.getParameter("remark");
			Department dept = new Department();
			dept.setDeptName(deptName);
			dept.setRemark(deptRemark);
			dept.setId(Integer.parseInt(id));
			
			boolean d = deptService.udateDepartment(dept);
			ArrayList<Department> list = deptService.getDepartments(dept);
			if(list!=null&&list.size()>0) {
				Department dx = list.get(0);
				request.setAttribute("dept", dx);
			}
			if (d) {
				request.setAttribute("errorU", "no");
				request.getRequestDispatcher("/updateDept.jsp").forward(request, response);
			} else {
				request.setAttribute("errorU", "yes");
				request.getRequestDispatcher("/updateDept.jsp").forward(request, response);
			}
		}else if(action.equals("check_deptName")) {
			response.setContentType("application/json;charset=UTF-8");
	        PrintWriter out = response.getWriter();
			String deptName = request.getParameter("deptName");
			Department dept = new Department();
			dept.setDeptName(deptName);
			boolean pos = deptService.isSameDeptName(dept);
			if(pos) {
	        	 out.print("部门名称已被使用");
	        }else {
	        	out.print("部门名称可以使用");
	        }
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
