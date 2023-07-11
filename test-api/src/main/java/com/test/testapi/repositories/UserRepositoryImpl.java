package com.test.testapi.repositories;

import com.test.testapi.domain.User;
import com.test.testapi.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepositoryImpl implements  UserRepository{
    private static final String SQL_CREATE = "insert into USER(firstName, lastName, email, password) values ( ?, ?, ?, ?)";
      private static final String SQL_COUNT_BY_EMAIL = "select count(*) from " +
            "USER where email = ?";

    private static final String SQL_FIND_BY_ID = "select id, firstName, lastName, email, password from " +
            "USER where id = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                return ps;
            }, keyHolder);
            System.out.println(keyHolder.getKeys());
            System.out.println((Integer) keyHolder.getKeys().get("id"));
            System.out.println(keyHolder.getKeys().get("GENERATED_KEY").getClass().getName() );
            return (Integer)  ((BigInteger)keyHolder.getKeys().get("GENERATED_KEY")).intValue();
        }
        catch (Exception e){
            throw new EtAuthException("Invalid details. Failed to create user");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,
                new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer id) {
        //return repo.findById(id);
        System.out.println("here"+ String.valueOf(id));
        //return null;
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id},userRowMapper);
    }
    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("id"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"));
    });
}
