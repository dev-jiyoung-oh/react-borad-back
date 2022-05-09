package com.board.back.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
    // Repository를 호출해서 글목록 데이터를 리턴하는 메소드 
	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

}