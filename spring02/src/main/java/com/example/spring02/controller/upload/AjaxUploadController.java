package com.example.spring02.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring02.service.board.BoardService;
import com.example.spring02.util.MediaUtils;
import com.example.spring02.util.UploadFileUtils;

@Controller
public class AjaxUploadController {
	
	private static final Logger logger =
	LoggerFactory.getLogger(AjaxUploadController.class); 
	
	@Inject
	BoardService boardService;
	
	
	
	
	//업로드 디렉토리 
	@Resource(name = "uploadPath") 
	String uploadPath; 
	
	
	@RequestMapping(value = "upload/uploadAjax", method = RequestMethod.GET)
	   public String uploadAjax() {
		   return "/upload/uploadAjax";
		   }
	
	@ResponseBody //객체를 json형식으로 데이터를 리턴한다. 
	   @RequestMapping(value= "/upload/uploadAjax", method= RequestMethod.POST,
	   produces = "text/plain;charset=utf-8") //한글이 깨지지 않도록 처리 
      public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
	      return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath,
	    		  file.getOriginalFilename(),file.getBytes()), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping("/upload/displayFile")
	  public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		  // 서버의 파일을 다운로드하기 위해서 스트림
		  InputStream in = null; 
		  ResponseEntity<byte[]> entity = null; 
		 try {
			     String formatName = fileName.substring(fileName.lastIndexOf(".")+ 1); 
			     MediaType mType = MediaUtils.getMediaType(formatName); 
			     //헤더 구성 객체 
			     HttpHeaders headers = new HttpHeaders(); 			     
			     //inputStream 생성 
			     in = new FileInputStream(uploadPath + fileName); 
			     fileName = fileName.substring(fileName.indexOf("-")+1); 
			     //다운로드용 컨텐츠 타입 
			     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			     headers.add("Content-Disposition", "attachment; filename=\""
			    		 + new String(fileName.getBytes("utf-8"), "iso-8859-1")+"\""); 
			        //바이트 배열(내용), 헤더 상태코드 3개를 묶어서 전달 
		 } catch (Exception e) { 
			   e.printStackTrace();	
			   entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST); //실패했을 때 에러 메시지 전달. 
			   
		 }finally {
			 if(in != null) 
				 in.close(); 
		 }
		 return entity; 
	  }
	  
	  @ResponseBody //뷰가 아닌 데이터를 리턴한다. 
	  @RequestMapping(value ="/upload/deleteFile") 
	  public ResponseEntity<String> deleteFile(String fileName) {
		       logger.info("fileName:" + fileName);
		       //확장자 검사 필수 
		       String formatName = fileName.substring(fileName.lastIndexOf(".")+ 1); 
		       MediaType mType = MediaUtils.getMediaType(formatName);
		       if(mType != null) {
		    	    String front=fileName.substring(0,12); 
		    	    String end=fileName.substring(14); 
		    	    new File(uploadPath+(front+end).replace(
		    	    		'/', File.pathSeparatorChar)).delete(); 
		       }
		       //기타 종류 원본 파일 
		       new File(uploadPath+fileName.replace('/', File.separatorChar)).delete(); 
		        
		       //레코드 삭제 
		       boardService.deleteFile(fileName); 
		       
		       
		       return new ResponseEntity<String>("deleted", HttpStatus.OK); 
		       
		       
	  }
	  

	}

   