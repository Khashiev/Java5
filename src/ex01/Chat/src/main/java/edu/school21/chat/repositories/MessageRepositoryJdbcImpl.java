package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessageRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String sql = "SELECT * FROM chat.message WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            User user = findUser(resultSet.getLong("sender_id"));
            Chatroom room = findChat(resultSet.getLong("room_id"));

            return Optional.of(new Message(resultSet.getLong("id"), user, room,
                    resultSet.getString("text"), resultSet.getTimestamp("date_time").toLocalDateTime()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    private User findUser(Long id) throws SQLException {
        String sql = "SELECT * FROM chat.user WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new User(id, resultSet.getString("name"), resultSet.getString("password"),
                    null, null);
        }
    }

    private Chatroom findChat(Long id) throws SQLException {
        String sql = "SELECT * FROM chat.chatroom WHERE id = " + id;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new Chatroom(id, resultSet.getString("chat_name"), null, null);
        }
    }
}