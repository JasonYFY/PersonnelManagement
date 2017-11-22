package com.jason.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.jason.domain.Document;
import com.jason.domain.User;
import com.jason.service.DocumentService;
import com.jason.service.domain.File;

/**
 * Servlet implementation class DocumentController
 */
@MultipartConfig
@WebServlet(name="/DocumentController", urlPatterns = { "/upload_file","/doc_query",
		"/doc_download","/doc_delete"})
public class DocumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentController() {
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
		DocumentService docService = new DocumentService();
		
		if (action.equals("upload_file")) {
			String title = request.getParameter("title");
			String fileRemark = request.getParameter("fileRemark");
			Part part = request.getPart("fileData");
			
			Document doc = new Document();
			doc.setCreateDate(new Date());
			doc.setFileData(part.getInputStream());
			doc.setFileName(getFileName(part));
			doc.setRemark(fileRemark);
			doc.setTitle(title);
			User user = (User) session.getAttribute("activeUser");
			if(user!=null) {
				doc.setUserId(user.getId());
				
				boolean pos = docService.uploadDocument(doc);
				if(pos) {
					request.setAttribute("errorF", "no");
				}else {
					request.setAttribute("errorF", "yes");
				}
				request.getRequestDispatcher("/uploadFile.jsp").forward(request, response);
			}
			
		}else if(action.equals("doc_query")) {
			String title = request.getParameter("title");
			if(title.equals("")) {
				ArrayList<File> list = docService.getAllDocument();
				request.setAttribute("fileList", list);
				session.setAttribute("files", list);
			}else {
				Document doc = new Document();
				doc.setTitle(title);
				ArrayList<File> list = docService.queryDocument(doc);
				request.setAttribute("fileList", list);
				session.setAttribute("files", list);
			}
			request.getRequestDispatcher("/queryDocument.jsp").forward(request, response);
		}else if(action.equals("doc_download")) {
			ArrayList<File> list = (ArrayList<File>) session.getAttribute("files");
			int id = Integer.parseInt(request.getParameter("fileId"));
			if(list!=null&&list.size()>0) {
				for(File f:list) {
					if(id==f.getId()) {
						response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(f.getFileName(),"UTF-8"));
						ServletOutputStream out = response.getOutputStream();
						InputStream fileData = f.getFileData();
						byte[] bt = new byte[1024];
						int ret = 0;
						while((ret=fileData.read(bt))!=-1) {
							out.write(bt, 0, ret);
						}
						out.close();
						break;
					}
				}
			}
		}else if(action.equals("doc_delete")) {
			int id = Integer.parseInt(request.getParameter("fileId"));
			Document doc = new Document();
			doc.setId(id);
			boolean pos = docService.deleteDocument(doc);
			if(pos) {
				request.setAttribute("errorD", "no");
			}else {
				request.setAttribute("errorD", "yes");
			}
			ArrayList<File> list = docService.getAllDocument();
			request.setAttribute("fileList", list);
			request.getRequestDispatcher("/queryDocument.jsp").forward(request, response);
		}
	}
	
	public String getFileName(Part filePart)
	{
		//form-data; name="fileData"; filename="FromClient.txt"
		String fileHeader=filePart.getHeader("Content-Disposition");
		String filename=fileHeader.substring(
				fileHeader.indexOf("filename=\"")+10, 
				fileHeader.lastIndexOf("\""));
		
		return filename;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
