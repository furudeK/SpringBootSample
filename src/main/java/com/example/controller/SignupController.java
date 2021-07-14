package com.example.controller;

import java.lang.management.MemoryUsage;
import java.util.Locale;
import java.util.Map;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.application.service.UserApplicationService;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    /**
     * ユーザー登録画面を表示
     */
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale,
                            @ModelAttribute SignupForm form) {
        // 性別を取得
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);

        // ユーザー登録画面に遷移
        return "user/signup";
    }

    /**
     * ユーザー登録処理
     */
    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,
                             @ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            // NG:ユーザー登録画面に戻ります
            return getSignup(model, locale, form);
        }

        log.info(form.toString());


        //formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);
        //ユーザ登録
        userService.signup(user);
        // ログイン画面にリダイレクト
        return "redirect:/login";
    }

    /**
     * データベース関連の例外処理
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHundler(DataAccessException e, Model model) {

        //空文字をセット
        model.addAttribute("error", "");

        //メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        //HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    /** その他の例外処理*/
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){

        //空文字をセット
        model.addAttribute("error", "");

        //メッセージをModelに登録
        model.addAttribute("message", "SignupControllerで例外が発生しました");

        //HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";

    }
}