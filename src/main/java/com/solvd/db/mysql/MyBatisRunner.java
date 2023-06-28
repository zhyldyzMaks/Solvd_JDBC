package com.solvd.db.mysql;

import com.solvd.db.mysql.mapper.UserMapper;
import com.solvd.db.mysql.model.User;
import com.solvd.db.mysql.services.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyBatisRunner {
    private static final Logger logger = LogManager.getLogger(MyBatisRunner.class);

    public static void main(String[] args) {
        try (InputStream stream = Resources.getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            properties.setProperty("url", url);
            properties.setProperty("user", username);
            properties.setProperty("password", password);
            String mybatisConfig = "mybatis-config.xml";

            try (InputStream mybatisStream = Resources.getResourceAsStream(mybatisConfig);
                 SqlSession session = new SqlSessionFactoryBuilder().build(mybatisStream,properties).openSession(true)) {
                UserService userService = new UserService(session.getMapper(UserMapper.class));
                User user = userService.getById(17);
                logger.info("User from DB: \n" + user);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


