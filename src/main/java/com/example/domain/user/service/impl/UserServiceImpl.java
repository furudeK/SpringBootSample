package com.example.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    /**
     * ユーザーの登録
     */
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1);//部署
        user.setRole("ROLE_GENERAL");//ロール
        mapper.insertOne(user);
    }

    /**
     * ユーザーの取得
     */
    @Override
    public List<MUser> getUsers(MUser user) {
        return mapper.findMany(user);
    }

    /**
     * ユーザー1件取得
     */
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }

    /**
     * ユーザー1件更新
     */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
        mapper.updateOne(userId, password, userName);

//        //例外を発生させる(8.4 トランザクション p.182~)
//        int i = 1 / 0;
    }

    /**
     * ユーザー1件削除
     */
    @Override
    public void deleteUserOne(String userId) {
        int count = mapper.deleteOne(userId);
    }

}
