package loader.entitie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> findNoteByIdAndUsername(long id, String username);
    Iterable<Note> findAllByUsername(String username);
    boolean existsByIdAndUsername(long id, String username);
    void deleteAllByUsername(String username);
}
