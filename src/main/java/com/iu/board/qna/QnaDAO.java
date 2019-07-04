package com.iu.board.qna;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.PageMaker;

@Repository
public class QnaDAO implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "QnaMapper.";
	
	@Override
	public int getTotalCount(PageMaker pageMaker) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"totalCount", pageMaker);
	}

	@Override
	public int setWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"qnaWrite", boardDTO);
	}

	@Override
	public int setDelete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"qnaDelete", num);
	}
	
	public int setReplyDelete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"qnaReplyDelete", num);
	}

	@Override
	public int setUpdate(BoardDTO boardDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"qnaUpdate", boardDTO);
	}

	@Override
	public BoardDTO getSelect(int num) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"qnaSelect", num);
	}

	@Override
	public List<BoardDTO> getList(PageMaker pageMaker) throws Exception {
		return sqlSession.selectList(NAMESPACE+"qnaList", pageMaker);
	}
}