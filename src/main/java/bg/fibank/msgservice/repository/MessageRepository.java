package bg.fibank.msgservice.repository;

import bg.fibank.msgservice.model.CustomerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageRepository {

    private static final Logger logger = LoggerFactory.getLogger(MessageRepository.class);
    private final JdbcTemplate jdbcTemplate;

    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CustomerMessage> fetchPendingMessages() {
        String sql = """
                SELECT record_id, COALESCE(mob_number, '-') AS mob_number, channel, msg_full
                FROM cust_notif
                WHERE is_sent = 'N' AND SYSDATE >= send_sched
                FOR UPDATE SKIP LOCKED
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new CustomerMessage(
                rs.getLong("record_id"),
                rs.getString("mob_number"),
                rs.getString("channel"),
                rs.getString("msg_full")
        ));
    }

    public void markAsWaiting(Long dbId) {
        String sql = "UPDATE cust_notif SET is_sent = 'W', send_time = SYSDATE WHERE record_id = ?";
        jdbcTemplate.update(sql, dbId);
    }

    public void markAsSent(Long dbId) {
        String sql = "UPDATE cust_notif SET is_sent = 'Y', send_time = SYSDATE WHERE record_id = ?";
        jdbcTemplate.update(sql, dbId);
    }

    public void markAsError(Long dbId) {
        String sql = "UPDATE cust_notif SET is_sent = 'E', send_time = SYSDATE WHERE record_id = ?";
        jdbcTemplate.update(sql, dbId);
    }
}
