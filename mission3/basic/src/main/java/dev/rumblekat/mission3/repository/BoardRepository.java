package dev.rumblekat.mission3.repository;

import dev.rumblekat.mission3.entity.BoardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends CrudRepository<BoardEntity,Long> {
    @Query("select b,p from BoardEntity b join fetch PostEntity p on p.boardEntity.id = b.id where b.id = :bno")
    List<Object[]> getPostsWithBoardId(@Param("bno") Long bno);

    @Query("select b,p from BoardEntity b join fetch PostEntity p on p.boardEntity.id = b.id")
    List<Object[]> getPostsWithBoard();

}
