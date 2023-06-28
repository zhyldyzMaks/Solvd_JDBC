package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.mysql.model.Student;
import com.solvd.db.mysql.model.Transcript;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TranscriptDAO extends AbstractDAO<Transcript> implements IDAO<Transcript> {
    private static final Logger logger = LogManager.getLogger(TranscriptDAO.class);

    public static final String insertQuery = "insert into transcripts (grade, completion_date, student_id, course_id) " +
            "values(?,?,?,?)";
    public static final String updateQuery = "update transcripts set grade = ?, completion_date = ?, student_id = ?," +
            " course_id = ? where id = ?";

    @Override
    public boolean create(Transcript tr) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tr.getGrade());
            preparedStatement.setDate(2, tr.getCompletionDate());
            preparedStatement.setLong(3, tr.getStudent().getId());
            preparedStatement.setLong(4, tr.getCourse().getId());

            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    logger.info("Transcript created with ID: " + generatedId);
                    return true;
                }
            } else {
                logger.warn("Failed to create transcript.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating transcript.", e);
        }
        return false;
    }

    @Override
    public Transcript getById(long id) {
        Transcript tr = new Transcript();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from transcripts where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tr.setId(resultSet.getLong("id"));
                tr.setGrade(resultSet.getString("grade"));
                tr.setCompletionDate(resultSet.getDate("completion_date"));
                long studentId = resultSet.getLong("student_id");
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);
                tr.setStudent(student);

                long courseId = resultSet.getLong("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                tr.setCourseId(course);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving transcript.", e);
        }
        return tr;
    }

    @Override
    public List<Transcript> getAll() {
        List<Transcript> transcripts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from transcripts");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String grade = resultSet.getString("grade");
                Date completionDate = resultSet.getDate("completion_date");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getById(studentId);

                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);

                Transcript tr = new Transcript(id, grade, completionDate, student, course);
                transcripts.add(tr);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving all transcripts.", e);
        }
        return transcripts;
    }

    @Override
    public boolean update(Transcript tr) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, tr.getGrade());
            preparedStatement.setDate(2, tr.getCompletionDate());
            preparedStatement.setLong(3, tr.getStudent().getId());
            preparedStatement.setLong(4, tr.getCourse().getId());
            preparedStatement.setLong(5, tr.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating transcript.", e);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from transcripts where id = ?");
            preparedStatement.setLong(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting transcript.", e);
        }
        return false;
    }

    public Transcript getTranscriptByStudentId(int studentId) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection()) {
            String query = "select t.id, t.grade, t.completion_date, t.student_id, t.course_id " +
                    "from transcripts t " +
                    "where t.student_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, studentId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Transcript transcript = new Transcript();
                        transcript.setId(resultSet.getInt("t.id"));
                        transcript.setGrade(resultSet.getString("t.grade"));
                        transcript.setCompletionDate(resultSet.getDate("t.completion_date"));
                        StudentDAO studentDAO = new StudentDAO();
                        Student student = studentDAO.getById(studentId);
                        transcript.setStudent(student);
                        int courseId = resultSet.getInt("t.course_id");
                        CourseDAO courseDAO = new CourseDAO();
                        Course course = courseDAO.getById(courseId);
                        transcript.setCourseId(course);
                        return transcript;
                    }
                }
            }
            return null;
        }
    }
}