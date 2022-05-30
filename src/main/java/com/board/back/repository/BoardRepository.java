package com.board.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.board.back.model.Board;

// 간단한 CRUD기능은 JpaRepository를 상속하는 것으로 구현이 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Query("SELECT b FROM board b ORDER BY no DESC LIMIT ?1, ?2")
	List<Board> findFromTo(Integer objectStartNum, Integer objectCountPerPage);

}