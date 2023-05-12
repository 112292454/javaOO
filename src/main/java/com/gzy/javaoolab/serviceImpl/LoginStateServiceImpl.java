package com.gzy.javaoolab.serviceImpl;


import com.gzy.javaoolab.dao.LoginStateMapper;
import com.gzy.javaoolab.entity.LoginState;
import com.gzy.javaoolab.service.LoginStateService;
import com.gzy.javaoolab.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description login_state
 * @author gzy
 * @date 2022-11-17
 */
@Service
public class LoginStateServiceImpl implements LoginStateService {

    @Resource
    private LoginStateMapper loginStateMapper;


    @Override
    public Object insert(LoginState loginState) {

        // valid
        if (loginState == null) {
            return Result.error("必要参数缺失");
        }

        loginStateMapper.insert(loginState);
        return Result.success();
    }


    @Override
    public Object delete(int id) {
        int ret = loginStateMapper.delete(id);
        return ret>0?Result.success():Result.error();
    }


    @Override
    public Object update(LoginState loginState) {
        int ret = loginStateMapper.update(loginState);
        return ret>0?Result.success():Result.error();
    }


    @Override
    public LoginState load(int id) {
        return loginStateMapper.load(id);
    }


    @Override
    public Map<String,Object> pageList(int offset, int pagesize) {

        List<LoginState> pageList = loginStateMapper.pageList(offset, pagesize);
        int totalCount = loginStateMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return result;
    }

}