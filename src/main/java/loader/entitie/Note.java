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
    private long id;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

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

    public Note(String username, String label, String note) {
        this.username = username;
        this.label = label;
        this.note = note;
    }

    public Note(String label, String note) {
        this.label = label;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username=" + username +
                ", label='" + label + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
