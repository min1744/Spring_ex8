package com.iu.board.qna;

import java.util.List;

import com.iu.board.BoardDTO;
import com.iu.file.FileVO;

public class QnaVO extends BoardDTO {
	private int ref;
	private int step;
	private int depth;
	private List<FileVO> files;

	public List<FileVO> getFiles() {
		return files;
	}
	public void setFiles(List<FileVO> files) {
		this.files = files;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
}