package loader.entitie;

import javax.persistence.*;

@Entity(name = "Note")
@Table(name = "Note")
public class Note {

    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "note_sequence"
    )
    private Long id;
    @Column(
            name = "user_id",
            nullable = false
    )
    private Long userId;
    @Column(
            name = "label",
            nullable = false
    )
    private String label;
    @Column(
            name = "note",
            nullable = false
    )
    private String note;

    public Note() {
    }

    public Note(Long userId, String label, String note) {
        this.userId = userId;
        this.label = label;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", userId=" + userId +
                ", label='" + label + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
