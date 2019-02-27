package com.studentIS.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthForm {

    @NotNull
    private Long studentId;

    @NotBlank
    private String password;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
