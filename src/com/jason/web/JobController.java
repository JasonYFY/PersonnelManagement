package com.jason.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jason.domain.Job;
import com.jason.service.JobService;


/**
 * Servlet implementation class JobController
 */
@WebServlet(name="/JobController", urlPatterns = { "/job_add","/job_query",
		"/job_delete","/job_edit","/job_update","/check_jobName"})
public class JobController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
		JobService jobService = new JobService();
		
		if (action.equals("job_add")) {
			String jobName = request.getParameter("jobName");
			String jobRemark = request.getParameter("jobRemark");
			Job job = new Job();
			job.setJobName(jobName);
			job.setRemark(jobRemark);
			boolean pos = jobService.addJob(job);
			if(pos) {
				request.setAttribute("errorJ", "no");
			}else {
				request.setAttribute("errorJ", "yes");
			}
			request.getRequestDispatcher("/addJob.jsp").forward(request, response);
		}else if(action.equals("job_query")) {
			String jobName = request.getParameter("jobName");
			if(jobName!=null&&jobName.equals("")) {
				ArrayList<Job> jobList = jobService.getAllJobs();
				request.setAttribute("jobList", jobList);
				request.getRequestDispatcher("/queryJob.jsp").forward(request, response);
			}else {
				Job job = new Job();
				job.setJobName(jobName);
				ArrayList<Job> jobList = jobService.getJobs(job);
				if(jobList!=null) {
					//查询结果
					request.setAttribute("jobList", jobList);
				}
				request.getRequestDispatcher("/queryJob.jsp").forward(request, response);
				
			}
		}else if(action.equals("job_delete")) {
			String[] ids = request.getParameterValues("jobId");
			if(ids!=null&&ids.length>0) {
				for(String s:ids) {
					Job job = new Job();
					job.setId(Integer.parseInt(s));
					boolean pos = jobService.deleteJob(job);
					if(!pos) {
						ArrayList<Job> jobList = jobService.getAllJobs();
						request.setAttribute("jobList", jobList);
						request.setAttribute("errorDelete", "yes");
						request.getRequestDispatcher("/queryJob.jsp").forward(request, response);
						return;
					}
				}
				ArrayList<Job> jobList = jobService.getAllJobs();
				request.setAttribute("jobList", jobList);
				request.setAttribute("errorDelete", "ok");
				request.getRequestDispatcher("/queryJob.jsp").forward(request, response);
			}else {
				ArrayList<Job> jobList = jobService.getAllJobs();
				request.setAttribute("jobList", jobList);
				request.setAttribute("errorDelete", "no");
				request.getRequestDispatcher("/queryJob.jsp").forward(request, response);
			}
		}else if(action.equals("job_edit")) {
			String id = request.getParameter("id");
			Job job = new Job();
			job.setId(Integer.parseInt(id));
			ArrayList<Job> list = jobService.getJobs(job);
			
			if(list!=null&&list.size()>0) {
				Job j = list.get(0);
				request.setAttribute("job",j);
				request.getRequestDispatcher("/updateJob.jsp").forward(request, response);
			}
		}else if(action.equals("job_update")) {
			String id = request.getParameter("id");
			String jobName = request.getParameter("jobName");
			String jobRemark = request.getParameter("remark");
			
			Job job = new Job();
			job.setId(Integer.parseInt(id));
			job.setJobName(jobName);
			job.setRemark(jobRemark);
			
			boolean udateJob = jobService.udateJob(job);
			
			ArrayList<Job> list = jobService.getJobs(job);
			if(list!=null&&list.size()>0) {
				Job j = list.get(0);
				request.setAttribute("job",j);
			}
			if (udateJob) {
				request.setAttribute("errorU", "no");
				request.getRequestDispatcher("/updateJob.jsp").forward(request, response);
			} else {
				request.setAttribute("errorU", "yes");
				request.getRequestDispatcher("/updateJob.jsp").forward(request, response);
			}
		}else if(action.equals("check_jobName")) {
			response.setContentType("application/json;charset=UTF-8");
	        PrintWriter out = response.getWriter();
			String jobName = request.getParameter("jobName");
			Job j = new Job();
			j.setJobName(jobName);
			boolean pos = jobService.isSameJobName(j);
			if(pos) {
	        	 out.print("职位名称已被使用");
	        }else {
	        	out.print("职位名称可以使用");
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
