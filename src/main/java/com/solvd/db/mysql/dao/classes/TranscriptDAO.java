package com.solvd.db.mysql.dao.classes;

import com.solvd.db.mysql.dao.IDAO;
import com.solvd.db.mysql.model.Transcript;
import com.solvd.db.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TranscriptDAO implements IDAO<Transcript> {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @Override
    public boolean create(Transcript tr) {
        try ( Connection connection = ConnectionManager.getConnection()) {
            String insertQuery = "insert into transcripts (grade, completion_date, student_id, course_id) " +
                    "values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tr.getGrade());
            preparedStatement.setDate(2, tr.getCompletionDate());
            preparedStatement.setLong(3,tr.getStudent());
            preparedStatement.setLong(4,tr.getCourse());

            if (preparedStatement.executeUpdate()>0){
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Transcript created with ID: " + generatedId);
                }
            } else {
                System.out.println("Failed to create transcript.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }
    @Override
    public Transcript getById(long id) throws SQLException {
        Transcript tr = new Transcript();
        try ( Connection connection = ConnectionManager.getConnection()) {
            preparedStatement = connection.prepareStatement("select * from transcripts where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tr.setId(resultSet.getLong("id"));
                tr.setGrade(resultSet.getString("grade"));
                tr.setCompletionDate(resultSet.getDate("completion_date"));
                tr.setStudent(resultSet.getLong("student_id"));
                tr.setCourse(resultSet.getLong("course_id"));
            }
        }
        return tr;
    }
    @Override
    public List<Transcript> getAll() {
        List<Transcript> transcripts = new ArrayList<>();
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("select * from transcripts");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String grade = resultSet.getString("grade");
                Date completionDate = resultSet.getDate("completion_date");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");

                Transcript tr = new Transcript(id, grade, completionDate, studentId, courseId);
                transcripts.add(tr);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transcripts;
    }

    @Override
    public boolean update(Transcript tr) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String updateQuery = "update transcripts set grade = ?, completion_date = ?, student_id = ?," +
                    " course_id = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, tr.getGrade());
            preparedStatement.setDate(2, tr.getCompletionDate());
            preparedStatement.setLong(3, tr.getStudent());
            preparedStatement.setLong(4, tr.getCourse());
            preparedStatement.setLong(5, tr.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            preparedStatement = connection.prepareStatement("delete from transcripts where id = ?");
            preparedStatement.setLong(1,id);
            int deletedRows = preparedStatement.executeUpdate();
            return  deletedRows > 0;
        }
    }
}
