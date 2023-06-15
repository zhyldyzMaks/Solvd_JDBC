package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.AbstractDAO;
import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Course;
import com.solvd.db.mysql.model.Exam;
import com.solvd.db.utils.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO extends AbstractDAO<Exam> implements IDAO<Exam> {
    private static final Logger logger = LogManager.getLogger(StudentDAO.class);
    public static final  String insertQuery = "insert into exams(name, date, course_id) values(?,?,?)";
    public static final  String updateQuery = "update exams set name = ?, date = ?, course_id = ? where id = ?";
    @Override
    public boolean create(Exam exam){
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, exam.getName());
            preparedStatement.setDate(2, exam.getDate());
            preparedStatement.setLong(3,exam.getCourse().getId());

            if (preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    logger.info("Exam created with ID: " + generatedId);
                }
            } else {
                logger.warn("Failed to create exam.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Error while creating exam.", e);
        } return false;
    }

    @Override
    public Exam getById(long id){
        Exam exam = new Exam();
        try ( Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from exams where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                exam.setId(resultSet.getLong("id"));
                exam.setName(resultSet.getString("name"));
                exam.setDate(resultSet.getDate("date"));
                long courseId = resultSet.getInt("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                exam.setCourse(course);
            }
        } catch (SQLException e) {
            logger.error("Error while retrieving exam.", e);
        }
        return exam;
    }

    @Override
    public List<Exam> getAll(){
        List<Exam> allExams = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from exams");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date date = resultSet.getDate("date");
                int courseId = resultSet.getInt("course_id");
                CourseDAO courseDAO = new CourseDAO();
                Course course = courseDAO.getById(courseId);
                Exam exam = new Exam(id, name, date, course);
                allExams.add(exam);
            }
        }catch (SQLException e){
            logger.error("Error while retrieving all exams.", e);
        }
        return allExams;
    }

    @Override
    public boolean update(Exam exam) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, exam.getName());
            preparedStatement.setDate(2, exam.getDate());
            preparedStatement.setLong(3,exam.getCourse().getId());
            preparedStatement.setLong(4, exam.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while updating exam.", e);
        }return false;
    }

    @Override
    public boolean delete(long id) {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("delete from exams where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        } catch (SQLException e) {
            logger.error("Error while deleting exam.", e);
        }
        return false;
    }
}