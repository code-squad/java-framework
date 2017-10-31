package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		JdbcTemplate insertTemplate = new JdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		insertTemplate.update("INSERT INTO USERS VALUES (?, ?, ?, ?)");
	}

	public void update(User user) throws SQLException {
		JdbcTemplate updateTemplate = new JdbcTemplate() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getEmail());
				pstmt.setString(4, user.getUserId());
			}

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		updateTemplate.update("UPDATE USERS set password=?, name=?, email=? WHERE userId=?");
	}

	public List<User> findAll() throws SQLException {
		JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {

			@Override
			public void setValues(PreparedStatement pstmt) {
				// 없어
			}
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		return selectJdbcTemplate.query("SELECT UserId, password, name, email FROM USERS");
	}

	public User findByUserId(String userId) throws SQLException {
		JdbcTemplate selectJdbcTemplate = new JdbcTemplate() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		};
		return (User) selectJdbcTemplate.queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?");
	}
}
