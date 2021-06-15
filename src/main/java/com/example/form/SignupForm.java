package com.example.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignupForm {
    @NotBlank(groups = VaildGroup1.class)
    @Email(groups = VaildGroup2.class)
    private String userId;
    @NotBlank(groups = VaildGroup1.class)
    @Length(min = 4 , max = 100, groups = VaildGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = VaildGroup2.class)
    private String password;
    @NotBlank(groups = VaildGroup1.class)
    private String userName;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = VaildGroup1.class)
    private Date birthday;
    @Min(value = 20, groups = VaildGroup2.class)
    @Max(value = 100, groups = VaildGroup2.class)
    private Integer age;
    @NotNull(groups = VaildGroup1.class)
    private Integer gender;
}