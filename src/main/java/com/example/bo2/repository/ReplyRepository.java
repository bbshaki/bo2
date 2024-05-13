package com.example.bo2.repository;

import com.example.bo2.entity.Board;
import com.example.bo2.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r " +
            "from Reply r where r.board.bno = :bno")
    List<Reply> listReplyFromBno(Long bno);

    List<Reply> findByBoard(Board board);
    List<Reply> findByBoard_Bno(Long bno);

    //페이징 처리
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);

    //댓글수
    Long countByBoard_Bno(Long Bno);

    @Query(value = "select * from Reply", nativeQuery = true)
    List<Reply> findaaa();

}
