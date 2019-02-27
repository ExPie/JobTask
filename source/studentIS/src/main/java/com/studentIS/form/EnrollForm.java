package com.studentIS.form;

import javax.validation.constraints.NotNull;

public class EnrollForm extends AuthForm {

    @NotNull
    private Long subjectId;

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
