package com.example.rest;

import com.example.domain.user.model.MUser;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import org.apache.catalina.User;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

import java.security.Signature;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    /**ユーザー登録*/
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Locale locale){
        //入力チェック結果
        if(bindingResult.hasErrors()){
            //チェック結果NG
            Map<String, String > errors = new HashMap<>();

            //エラーメッセージの取得
            for(FieldError error : bindingResult.getFieldErrors()){
                String message = messageSource.getMessage(error,locale);
                errors.put(error.getField(),message);
            }
            return new RestResult(90,errors);
        }

        //formをMUserクラスに変換
        MUser user = modelMapper.map(form,MUser.class);
        //ユーザー登録
        userService.signup(user);

        //結果の返却
        return new RestResult(0,null);
    }

    /** ユーザーを更新 */
    @PutMapping("/update")
    public int updateUser(UserDetailForm form){
        //ユーザーを更新
        userService.updateUserOne(form.getUserId(),form.getPassword(),form.getUserName());

        return 0;
    }

    /** ユーザーを削除 */
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form){
        //ユーザーを削除
        userService.deleteUserOne(form.getUserId());

        return 0;
    }

}
