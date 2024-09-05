package edu.school21.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom chatroom;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Chatroom chatroom, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.chatroom = chatroom;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getChatroom() {
        return chatroom;
    }

    public void setChatroom(Chatroom chatroom) {
        this.chatroom = chatroom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Message message = (Message) obj;
        return Objects.equals(id, message.id) && Objects.equals(author, message.author)
                && Objects.equals(chatroom, message.chatroom)
                && Objects.equals(text, message.text)
                && Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, chatroom, text, dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        String dateTimeStr = (dateTime == null ? null : dateTime.format(formatter));

        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", chatroom=" + chatroom +
                ", text='" + text + '\'' +
                ", dateTime=" + dateTimeStr +
                "}";
    }
}
