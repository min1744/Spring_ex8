package com.iu.file;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "FileMapper.";
	
	public int setWrite(List<FileVO> files) throws Exception{
		return sqlSession.insert(NAMESPACE+"setWrite", files);
	}
	
	public int setUpdate(FileVO fileVO) throws Exception{
		return sqlSession.update(NAMESPACE+"setUpdate", fileVO);
	}
	
	public int setDelete(int fnum) throws Exception{
		return sqlSession.delete(NAMESPACE+"setDelete", fnum);
	}
	
	public FileVO getSelect(int fnum) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getSelect", fnum);
	}
	
	public List<FileVO> getList(int num) throws Exception{
		return sqlSession.selectList(NAMESPACE+"getList", num);
	}
}