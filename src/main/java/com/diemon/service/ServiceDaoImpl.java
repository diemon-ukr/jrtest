package com.diemon.service;

import com.diemon.entity.User;
import com.diemon.entity.UserDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Diemon on 6/6/2017.
 */
@Service("serviceDao")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    private SessionFactory sessionFactory;

    public final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean delete(Object obj) {
        getCurrentSession().delete(obj);
        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean truncateDb() {
        getCurrentSession().createSQLQuery("DELETE FROM test.user;").executeUpdate();
        return true;
    }

    @Override
    public User getUserById(Integer id) {
        User t = (User) getCurrentSession().get(User.class, id);
        return t;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = getCurrentSession().createQuery("FROM User a ORDER BY a.name ASC").list();
        return list;
    }

    @Override
    public List<UserDto> getUserQueryByIsAdmin(Boolean isAdmin) {
        return (List<UserDto>) getCurrentSession().createSQLQuery("SELECT id, name as name, age, isAdmin FROM User WHERE isAdmin = :isAdmin")
                .addEntity(UserDto.class)
                .setParameter("isAdmin", isAdmin)
                .list();
    }
}
