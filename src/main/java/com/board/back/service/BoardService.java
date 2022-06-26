package com.board.back.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.board.back.exception.ResourceNotFoundException;
import com.board.back.model.Board;
import com.board.back.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public int findAllCount() {
		return (int) boardRepository.count();
	}
	
    // Repository를 호출해서 글목록 데이터를 리턴하는 메소드 
	// get boards data
	public ResponseEntity<Page<Board>> getAllBoard(Pageable pageable) {
		Page<Board> pageList = boardRepository.findAll(pageable);
		
		for(Board b : pageList) {
			System.out.println(b);
		}
		
		return ResponseEntity.ok(pageList);
	}

	// create board
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}

	// get board one by id
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

	// update board 
	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}

	// delete board
	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}