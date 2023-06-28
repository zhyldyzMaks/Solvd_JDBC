package com.solvd.db.mysql.mapper;

import com.solvd.db.mysql.model.User;
import java.util.List;

public interface UserMapper {

    User getUserById(long id);

    List<User> getAllUsers();

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(long id);
}
