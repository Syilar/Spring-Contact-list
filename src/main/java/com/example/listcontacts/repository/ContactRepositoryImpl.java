package com.example.listcontacts.repository;

import com.example.listcontacts.Contact;
import com.example.listcontacts.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
@Slf4j
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact addContact(Contact contact) {
        contact.setId(System.currentTimeMillis());

        String sql = "INSERT INTO contact (firstName, lastName, email, phone, id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(),
                contact.getPhone(), contact.getId());
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existedContact = findById(contact.getId()).orElse(null);

        if (existedContact != null) {
            String sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(),
                    contact.getPhone(), contact.getId());
        }
        return contact;
    }

    @Override
    public void deleteContact(Long id) {
        String sql = "DELETE FROM contact WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void batchInsert(List<Contact> contacts) {
        String sql = "INSERT INTO contact (firstName, lastName, email, phone, id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Contact contact = contacts.get(i);
                ps.setString(1,contact.getFirstName());
                ps.setString(2,contact.getLastName());
                ps.setString(3,contact.getEmail());
                ps.setString(4,contact.getPhone());
                ps.setLong(5,contact.getId());
            }

            @Override
            public int getBatchSize() {
                return contacts.size();
            }
        });
    }
}
