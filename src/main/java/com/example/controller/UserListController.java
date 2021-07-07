package com.example.controller;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    /**
     * ユーザー一覧画面を表示
     */
    @GetMapping("/list")
    public String getUserList(Model model) {
        //ユーザー一覧を取得
        List<MUser> userList = userService.getUsers();

        //Modelに登録
        model.addAttribute("userList", userList);
        System.out.println(model);
        // ユーザー一覧画面を表示
        return "user/list";
    }
}