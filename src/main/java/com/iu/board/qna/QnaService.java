package com.iu.board.qna;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileVO;
import com.iu.util.FileSaver;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {

	@Inject
	private QnaDAO qnaDAO;
	@Inject
	private FileDAO fileDAO;
	@Inject
	private FileSaver fileSaver;
	
	@Override
	public int setWrite(BoardDTO boardDTO, List<MultipartFile> multipartFiles, HttpSession session) throws Exception {
		int result = qnaDAO.setWrite(boardDTO);
		String realPath = session.getServletContext().getRealPath("/resources/upload");
		ArrayList<FileVO> files = new ArrayList<FileVO>();
		for(MultipartFile multipartFile : multipartFiles) {
			FileVO fileVO = new FileVO();
			fileVO.setNum(boardDTO.getNum());
			fileVO.setFname(fileSaver.saveFile(realPath, multipartFile));
			fileVO.setOname(multipartFile.getOriginalFilename());
			files.add(fileVO);
		}
		result = fileDAO.setWrite(files);
		
		return result;
	}

	@Override
	public int setDelete(int num) throws Exception {
		int result = qnaDAO.setDelete(num);
		result = qnaDAO.setReplyDelete(num);
		return result;
	}
	
	public int setReplyDelete(int num) throws Exception {
		return qnaDAO.setReplyDelete(num);
	}

	@Override
	public int setUpdate(BoardDTO boardDTO) throws Exception {
		return qnaDAO.setUpdate(boardDTO);
	}

	@Override
	public BoardDTO getSelect(int num) throws Exception {
		return qnaDAO.getSelect(num);
	}

	@Override
	public List<BoardDTO> getList(PageMaker pageMaker) throws Exception {
		pageMaker.makeRow();
		pageMaker.makePage(qnaDAO.getTotalCount(pageMaker));
		return qnaDAO.getList(pageMaker);
	}
}