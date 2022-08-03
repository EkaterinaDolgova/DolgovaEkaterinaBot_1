package pro.DolgovaEkaterinaBot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_chat;
    private String textY;
    private LocalDateTime datetime;

    public Notification(Long id_chat, String textY, LocalDateTime datetime) {
        this.id_chat = id_chat;
        this.textY = textY;
        this.datetime = datetime;
    }

    public Notification() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_chat() {
        return id_chat;
    }

    public void setId_chat(Long id_chat) {
        this.id_chat = id_chat;
    }

    public String getTextY() {
        return textY;
    }

    public void setTextY(String textY) {
        this.textY = textY;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", id_chat='" + id_chat + '\'' +
                ", textY='" + textY + '\'' +
                ", datetime=" + datetime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification task = (Notification) o;
        return id_chat == task.id_chat && id.equals(task.id) && textY.equals(task.textY) && datetime.equals(task.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_chat, textY, datetime);
    }
}
