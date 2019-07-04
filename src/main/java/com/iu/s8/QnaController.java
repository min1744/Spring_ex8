package com.iu.s8;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.board.qna.QnaService;
import com.iu.board.qna.QnaVO;
import com.iu.util.PageMaker;

@Controller
@RequestMapping(value = "/qna/")
public class QnaController {

	@Inject
	private QnaService qnaService;

	@RequestMapping(value = "qnaWrite", method = RequestMethod.POST)
	public String setWrite(BoardDTO boardDTO, List<MultipartFile> multipartFiles, HttpSession session, Model model) throws Exception {
		int result = qnaService.setWrite(boardDTO, multipartFiles, session);
		String view = "common/messageMove";
		if(result > 0) {
			view = "redirect:./qnaList";
		} else {
			model.addAttribute("message", "Wrte Fail");
			model.addAttribute("path", "./qnaList");
		}
		return view;
	}
	
	@RequestMapping(value = "qnaWrite", method = RequestMethod.GET)
	public String setWrite(Model model) throws Exception {
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}

	@RequestMapping("qnaDelete")
	public ModelAndView setDelete(@RequestParam(required = false, defaultValue = "0") int num, ModelAndView mv) throws Exception {
		int result = qnaService.setDelete(num);
		if(result > 0) {
			mv.setViewName("redirect:./qnaList");
		} else {
			mv.addObject("message", "Delete Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/messageMove");
		}
		
		return mv;
	}

	@RequestMapping(value = "qnaUpdate", method = RequestMethod.GET)
	public String setUpdate(@RequestParam(required = false, defaultValue = "0") int num, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.getSelect(num);
		model.addAttribute("boardDTO", boardDTO);
		model.addAttribute("board", "qna");
		
		return "board/boardUpdate"; 
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.POST)
	public ModelAndView setUpdate(BoardDTO boardDTO, ModelAndView mv) throws Exception {
		int result = qnaService.setUpdate(boardDTO);
		if(result > 0) {
			mv.setViewName("redirect:./qnaSelect?num="+boardDTO.getNum());
		} else { 
			mv.addObject("message", "Update Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/messageMove");
		}
		return mv;
	}

	@RequestMapping("qnaSelect")
	public String getSelect(@RequestParam(required = false, defaultValue = "0") int num, Model model) throws Exception {
		BoardDTO boardDTO = qnaService.getSelect(num);
		String view = "common/messageMove";
		if(boardDTO != null) {
			view = "board/boardSelect";
			model.addAttribute("board", "qna");
			model.addAttribute("boardDTO", boardDTO);
		} else {
			model.addAttribute("message", "no Contents");
			model.addAttribute("path", "./noticeList");
		}
		return view;
	}

	@RequestMapping("qnaList")
	public String getList(PageMaker pageMaker, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.getList(pageMaker);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pageMaker);
		model.addAttribute("board", "qna");
		
		return "board/boardList";
	}
}