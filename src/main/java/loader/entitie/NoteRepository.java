package loader.entitie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    //Optional<Note> findNoteByIdAndUserId(long id, long userId);
    Iterable<Note> findAllByUsername(String username);
    //boolean existsByIdAndUserId(long id, long userId);
    void deleteAllByUsername(String username);
}
