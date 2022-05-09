package com.board.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.board.back.model.Board;

// 간단한 CRUD기능은 JpaRepository를 상속하는 것으로 구현이 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

}