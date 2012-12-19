package org.codebone.security.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;


public class AuthenticationDao extends JdbcDaoImpl {
	
	private static final Logger logger = Logger.getLogger("security");

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		RowMapper<UserDetails> rowMapper = new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long idx = rs.getLong(1);
				String password = rs.getString(2);
				boolean enabled = rs.getBoolean(3);
				logger.info("idx : "+idx +", password : "+ password + ", enabled" + enabled);
				return new User(
						Long.toString(idx),
						password,
						enabled,
						true,
						true,
						true,
						AuthorityUtils.NO_AUTHORITIES);
			}
		};
		
		List<UserDetails> userList = getJdbcTemplate().query(getUsersByUsernameQuery(),new String[] { username }, rowMapper);
		return userList;
	}
	
	
}