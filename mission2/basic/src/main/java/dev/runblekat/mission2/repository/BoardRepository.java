package dev.runblekat.mission2.repository;

import dev.runblekat.mission2.Entity.Board;
import java.util.List;

public interface BoardRepository {
    boolean save(Board board);
    List<Board> findAll();
    Board findById(Long id);
    boolean update(Long id, Board board);
    boolean delete(Long id);
}
