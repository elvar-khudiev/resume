package com.company;

import com.company.dao.impl.UserRepositoryCustomImpl;
import com.company.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryCustomImplIT {

    @Autowired
    UserRepositoryCustomImpl userRepositoryCustom;

    @Test
    public void testGetAll() {
        List<User> list = userRepositoryCustom.getAll(null, null, null);

        Assert.assertEquals("user size must be 2", 2, list.size());
        System.out.println("list" + list);
    }
}
