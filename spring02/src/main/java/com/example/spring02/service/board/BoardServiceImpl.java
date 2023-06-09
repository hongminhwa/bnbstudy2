package com.example.spring02.service.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring02.model.board.dao.BoardDAO;
import com.example.spring02.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	BoardDAO boardDao;

	@Override
	public void deleteFile(String fullName) {
		boardDao.deleteFile(fullName);
	}

	@Override
	public List<String> getAttach(int bno) {
		return boardDao.getAttach(bno);
	}

	@Override
	public void addAttach(String fullName) {
		
	}

	@Override
	public void updateAttach(String fullName, int bno) {
		// TODO Auto-generated method stub

	}

	//1.글쓰기 - 게시물 번호 생성
	//2.첨부파일 등록 - 게시물 번호 사용
	//위 두개가 하나의 트랜잭션 단위가 됨
	@Transactional
	@Override
	public void create(BoardDTO dto) throws Exception {
		boardDao.create(dto);//board 테이블에 레코드 추가
		//attach 테이블에 레코드 추가
		String[] files=dto.getFiles();//첨부파일 이름 배열
		if(files==null) return; //첨부파일이 없으면 skip
		for(String name : files) {
			boardDao.addAttach(name);//attach테이블에 insert
		}
	}

	@Transactional
	@Override
	public void update(BoardDTO dto) throws Exception {
		boardDao.update(dto);
		//별도로 만들었던 파일처리 attach테이블도 수정해줘야함, 같이 수정처리함.
		//service는 transaction으로 묶기에 좀 더 유리하다.
		String[] files=dto.getFiles();
		if(files==null) return;
		for(String name : files) {
			System.out.println("첨부파일 이름 : "+name);
			boardDao.updateAttach(name, dto.getBno());
		}
	}

	@Transactional
	@Override
	public void delete(int bno) throws Exception {
		//reply 레코드 삭제
		//attach
		//board
		boardDao.delete(bno);
	}

	@Override
	public List<BoardDTO> listAll(String search_option, String keyword, int start, int end) throws Exception {
		return boardDao.listAll(search_option,keyword,start,end);
	}

	@Override
	public void increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time=0;
		if(session.getAttribute("update_time_"+bno)!=null) {
			//최근에 조회수를 올린 시간
			update_time=(long)session.getAttribute("update_time_"+bno);
		}
		long current_time=System.currentTimeMillis();
		//일정 시간(5초)이 경과한 후 조회수 증가 처리
		if(current_time - update_time > 5*1000) {//24*60*60*100(하루)
			//조회수 증가 처리
			boardDao.increaseViewcnt(bno);
			//조회수를 올린 시간 저장
			session.setAttribute("update_time_"+bno, current_time);
		}
	}

	@Override
	public int countArticle() throws Exception {
		return boardDao.countArticle();
	}

	@Override
	public BoardDTO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		// TOod stub
		
	}

}
