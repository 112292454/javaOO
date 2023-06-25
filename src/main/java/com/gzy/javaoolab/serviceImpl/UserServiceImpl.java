package com.gzy.javaoolab.serviceImpl;

import com.gzy.javaoolab.dao.UserMapper;
import com.gzy.javaoolab.entity.User;
import com.gzy.javaoolab.service.UserService;
import com.gzy.javaoolab.vo.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description user
 * @author gzy
 * @date 2022-11-09
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    HashMap<Integer, Date> id_time_token=new HashMap<>();

    @Override
    public Object insert(User user) {

        // valid
        if (user == null) {
            return Result.error("必要参数缺失");
        }

        userMapper.insert(user);
        return Result.success();
    }


    @Override
    public Object delete(int id) {
        int ret = userMapper.delete(id);
        return ret>0?Result.success():Result.error();
    }


    @Override
    public Object update(User user) {
        int ret = userMapper.update(user);
        return ret>0?Result.success():Result.error();
    }


    @Override
    public User load(Integer id) {
        return userMapper.load(id);
    }

    @Override
    public List<User> loadAll() {
        List<User> users = userMapper.loadAll();
        users.forEach(a->{
            a.setPassword(null);
            a.setEmail(null);
            a.setLastLogin(null);
            a.setAvatarId(null);
        });
        return users;
    }

    @Override
    public User loadByName(String name) {
        return userMapper.loadByName(name);
    }

    @Override
    public Date getLoginTime(Integer userId) {
        return id_time_token.get(userId);
    }

    @Override
    public void addLoginTime(Integer userId, Date time) {
        id_time_token.put(userId, new Date());
    }

    @Override
    public boolean contains(String name) {
        User user=userMapper.loadByName(name);
        return user!=null;
    }

    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<User> pageList = userMapper.pageList(offset, pagesize);
        int totalCount = userMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

    @Override
    public Object register(String name, String password) {
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        //TODO:设置其他默认值？
        return this.insert(user);
    }
}