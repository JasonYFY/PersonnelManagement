package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.domain.Department;
import com.jason.domain.Job;
import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.DeptService;
import com.jason.service.EmployeeService;
import com.jason.service.JobService;
import com.jason.service.UserService;
import com.jason.service.Interface.IUserService;
import com.jason.service.domain.EmployeeS;

/**
 * Servlet Filter implementation class loginFilter
 */
@WebFilter("/*")
public class pageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public pageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		//System.out.println("[filter]path:"+path);
		//对于不以.jsp结尾的处理
		if(!path.contains(".jsp")||path.equals("/login.jsp")) {
			if(path.equals("/Employee_query")||path.equals("/Employee_edit")
					||path.equals("/Employee_delete")||path.equals("/Employee_update")) {
				JobService jobService = new JobService();
				DeptService deptService = new DeptService();
				ArrayList<Job> list = jobService.getAllJobs();
				ArrayList<Department> list2 = deptService.getAllDepartments();
				request.setAttribute("jobList", list);
				request.setAttribute("deptList", list2);
			}
			chain.doFilter(request, response);
		}else{
			Object user = session.getAttribute("activeUser");
			if(user!=null) {
				if(path.equals("/addEmployee.jsp")||path.equals("/queryEmployee.jsp")||path.equals("/updateEmployee.jsp")) {
					JobService jobService = new JobService();
					DeptService deptService = new DeptService();
					EmployeeService employeeService = new EmployeeService();
					ArrayList<EmployeeS> allEmployee = employeeService.getAllEmployee();
					ArrayList<Job> list = jobService.getAllJobs();
					ArrayList<Department> list2 = deptService.getAllDepartments();
					request.setAttribute("jobList", list);
					request.setAttribute("deptList", list2);
					request.setAttribute("empList", allEmployee);
				}
				if(path.equals("/queryUser.jsp")) {
					UserService userS = new UserService();
					PageModel page = new PageModel();
					ArrayList<User> allList = userS.QueryUserAll(page);
					request.setAttribute("pageModel", page);
					request.setAttribute("userList", allList);
				}
				chain.doFilter(request, response);
			}else {
				res.sendRedirect("./login.jsp");
			}
			
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
