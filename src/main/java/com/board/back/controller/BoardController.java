package com.board.back.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.model.Board;
import com.board.back.service.BoardService;

@CrossOrigin(origins = "http://localhost:3000") // CORS 문제를 해결하기 위해 추가
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

    // Service를 호출해서 글목록의 데이터를 리턴하는 메소드
	// 페이징 처리를 할 수 있도록 수정
	// get all board 
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "pNum", required=false) Integer pNum) {
		if (pNum == null || pNum <= 0) pNum = 1;
		
		return boardService.getAllBoard(pNum);
	}
	
	// create board
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}
	
	// get board
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(@PathVariable Integer no) {
		return boardService.getBoard(no);
	}
	
	// update board
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Board board){
		
		return boardService.updateBoard(no, board);
	}
	
	// delete board
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
}