package com.example.spring02.controller.upload;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ImageUploadController { 
	private static final Logger logger =
			LoggerFactory.getLogger(ImageUploadController.class); 
	
	 
	@RequestMapping("imageUpload.do") 
	 public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam MultipartFile upload) throws Exception {
		        OutputStream out = null; 
		        PrintWriter printWriter = null; 
		        response.setCharacterEncoding("utf-8");
		        response.setContentType("text/html; charset=utf-8");
		        
		        try {
					//업로드한 파일 이름 th
		        	String fileName=upload.getOriginalFilename();
		        	byte[] bytes=upload.getBytes(); 
		        	String uploadPath="C:\\work\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\spring02\\resources\\images\\";
					/* out = new FileOutputStream(new File(uploadPath+fileName)); */
		        	
		             out.write(bytes);
		             printWriter=response.getWriter(); 
		             String fileUrl=request.getContextPath()+"/images/"+ fileName;
		             printWriter.println("{\"filename\": \"" + fileName+"\","
		             		+ "\"uploaded\" : 1, \"url\";\""+fileUrl+"\"}");
				} catch (Exception e) {
				       e.printStackTrace();					
				
				}finally {
					try {
						  if(out != null) {
							  out.close(); 
						  }
						  if(printWriter != null) {
							  printWriter.close(); 
						  }
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
		        return; 
	}
}   
