package com.jason.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jason.domain.PageModel;
import com.jason.domain.User;
import com.jason.service.UserService;
import com.jason.service.Interface.IUserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet(name = "/UserController", urlPatterns = { "/user_login", "/user_register", 
		"/user_query","/user_loginout","/user_delete","/check_loginname","/user_update","/user_edit" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		IUserService userService = new UserService();
		UserService userS = (UserService)userService;

		if (action.equals("user_login")) {
			// 获取用户参数
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setLoginname(loginname);
			user.setPassword(password);

			boolean isLogin = userService.login(user);

			/*
			 * 逻辑
			 */
			if (isLogin) {
				// ...
				session.setAttribute("activeUser", user);
				response.sendRedirect("./index.jsp");
			} else {
				request.setAttribute("error", "yes");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}

		} else if (action.equals("user_register")) {
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			String status = request.getParameter("status");
			String username = request.getParameter("username");

			User user = new User(loginname, password, Integer.parseInt(status), new Date(), username);

			boolean isRegister = userService.register(user);

			if (isRegister) {
				request.setAttribute("errorA", "no");
				request.getRequestDispatcher("/addUser.jsp").forward(request, response);
			} else {
				request.setAttribute("errorA", "yes");
				request.getRequestDispatcher("/addUser.jsp").forward(request, response);
			}

		}else if(action.equals("user_query")) {   //查询处理
			String status = request.getParameter("status");
			String username = request.getParameter("username");
			String pageIndex = request.getParameter("pageIndex");
			userS = (UserService)userService;
			
			User user = new User();
			PageModel page = new PageModel();
			if(!status.equals("")) {
				user.setStatus(Integer.parseInt(status));
			}
			if(!username.equals(""))
				user.setUsername(username);
			
			if(pageIndex!=null&&!pageIndex.equals("")) {
				page.setPageIndex(Integer.parseInt(pageIndex));
			}else
				page.setPageIndex(1);
				
			ArrayList<User> list = userS.QueryUserByPage(user, page);
			request.setAttribute("user", user);
			if(list!=null) {
				//查询结果
				request.setAttribute("pageModel", page);
				request.setAttribute("userList", list);
			}
			request.getRequestDispatcher("/queryUser.jsp").forward(request, response);
			
		}else if(action.equals("user_delete")) {  //删除处理
			userS = (UserService)userService;
			String[] ids = request.getParameterValues("userId");
			String pageIndex = request.getParameter("pageIndex");
			
			PageModel page = new PageModel();
			if(!pageIndex.equals(""))
				page.setPageIndex(Integer.parseInt(pageIndex));
			if(ids!=null&&ids.length>0) {
				request.setAttribute("errorDelete", "ok");
				for(String s:ids) {
					User u = new User();
					u.setId(Integer.parseInt(s));
					boolean isDelete = userS.delete(u);
					if(!isDelete) {
						request.setAttribute("errorDelete", "yes");
						break;
					}
				}
				ArrayList<User> allList = userS.QueryUserByPage(new User(), page);
				request.setAttribute("pageModel", page);
				request.setAttribute("userList", allList);
				request.getRequestDispatcher("/queryUser.jsp").forward(request, response);
			}else {
				ArrayList<User> allList = userS.QueryUserByPage(new User(), page);
				request.setAttribute("pageModel", page);
				request.setAttribute("userList", allList);
				request.setAttribute("errorDelete", "no");
				request.getRequestDispatcher("/queryUser.jsp").forward(request, response);
			}
		}else if(action.equals("user_loginout")) {
			session.invalidate();
			response.sendRedirect("./login.jsp");
		}else if(action.equals("check_loginname")) {
			response.setContentType("application/json;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        String loginname = request.getParameter("loginname");
	        User u = new User();
	        u.setLoginname(loginname);
	        userS = (UserService)userService;
	        boolean pos = userS.isSameLoginname(u);
	        if(pos) {
	        	 out.print("登陆名已被使用");
	        }else {
	        	out.print("登陆名可以使用");
	        }
		}else if(action.equals("user_edit")){
			String userId = request.getParameter("id");
			User user = new User();
			user.setId(Integer.parseInt(userId));
			userS = (UserService)userService;
			ArrayList<User> list = userS.QueryUser(user);
			if(list!=null&&list.size()>0) {
				User u = list.get(0);
				request.setAttribute("user", u);
				request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
			}
			
		}else if(action.equals("user_update")){
			String loginname = request.getParameter("loginname");
			String password = request.getParameter("password");
			String status = request.getParameter("status");
			String username = request.getParameter("username");
			String id = request.getParameter("id");
			
			
			User user = new User(loginname, password, Integer.parseInt(status), new Date(), username);
			user.setId(Integer.parseInt(id));
			userS = (UserService)userService;
			boolean update = userS.update(user);
			User u = new User();
			user.setId(Integer.parseInt(id));
			ArrayList<User> list = userS.QueryUser(user);
			if(list!=null&&list.size()>0) {
				User us = list.get(0);
				request.setAttribute("user", us);
			}
			if (update) {
				request.setAttribute("errorU", "no");
				request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
			} else {
				request.setAttribute("errorU", "yes");
				request.getRequestDispatcher("/updateUser.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
