package ru.innotech.service;

import ru.innotech.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void updateUser(Long id, String newName) {
        String sql = "UPDATE users SET username = ? WHERE id = ?";
        Object[] params = {newName, id};
        int[] types = {Types.VARCHAR, Types.BIGINT};
        jdbcTemplate.update(sql, params, types);
    }

    @Override
    public void addUser(Long id, String newName) {
        String sql = "INSERT INTO users (id, username) VALUES (?, ?)";
        Object[] params = {id, newName};
        int[] types = {Types.BIGINT, Types.VARCHAR,};
        jdbcTemplate.update(sql, params, types);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT id, username FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            return user;
        });
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        Object[] params = {id};
        int[] types = {java.sql.Types.BIGINT};
        jdbcTemplate.update(sql, params, types);
    }

    @Override
    public Integer maxIdUsers() {
        String sql = "SELECT max(id) FROM users";
        Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxId == null ? 0 : maxId;

    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT id, username FROM users WHERE id = ?";
        Object[] params = {id};
        List<User> users = jdbcTemplate.query(sql, params, new UserMapper());
        return users.isEmpty() ? null : users.get(0);
    }


    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            return user;
        }
    }
}
