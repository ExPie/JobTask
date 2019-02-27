package com.studentIS.controller;

import com.studentIS.exception.BadAuthException;
import com.studentIS.form.AuthForm;
import com.studentIS.form.EnrollForm;
import com.studentIS.hash.Hash;
import com.studentIS.model.Enrollment;
import com.studentIS.model.Student;
import com.studentIS.model.Subject;
import com.studentIS.repository.EnrollmentRepository;
import com.studentIS.repository.StudentRepository;
import com.studentIS.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SystemController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    private boolean auth(AuthForm authForm) {
        Student student = studentRepository.findById(authForm.getStudentId()).orElseThrow(BadAuthException::new);
        String hash;

        try {
            hash = Hash.hash(authForm.getPassword(), student.getSalt());
        } catch (UnsupportedEncodingException e) {
            throw new BadAuthException();
        }

        return hash.equals(student.getHash());
    }

    // Get all subjects
    @PostMapping("/subjects")
    public List<Subject> getAllSubjects(@Valid @RequestBody AuthForm authForm) {
        if(auth(authForm))
            return subjectRepository.findAll();
        else
            throw new BadAuthException();
    }

    // Find a subject
    @PostMapping("/subjects/{name}")
    public List<Subject> getSimilarSubject(@PathVariable(value = "name") String subjectName, @Valid @RequestBody AuthForm authForm) {
        if(auth(authForm))
            return subjectRepository.findByName("%" + subjectName + "%");
        else
            throw new BadAuthException();
    }

    // Enroll
    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@Valid @RequestBody EnrollForm enrollForm) {
        if(auth(enrollForm)) {
            Enrollment example = new Enrollment();
            example.setStudentId(enrollForm.getStudentId());
            example.setSubjectId(enrollForm.getSubjectId());
            Optional<Enrollment> enrollment = enrollmentRepository.findOne(Example.of(example));

            Subject example2 = new Subject();
            example2.setId(enrollForm.getSubjectId());
            Optional<Subject> subject = subjectRepository.findOne(Example.of(example2));

            if(!enrollment.isPresent()) {
                if(subject.isPresent())
                    enrollmentRepository.save(example);
                else
                    return ResponseEntity.badRequest().body("No subject.");

            } else {
                return ResponseEntity.badRequest().body("Already enrolled.");
            }

            return ResponseEntity.ok().build();
        }
        else {
            throw new BadAuthException();
        }
    }

    // Enroll
    @PostMapping("/disenroll")
    public ResponseEntity<?> disenroll(@Valid @RequestBody EnrollForm enrollForm) {
        if(auth(enrollForm)) {
            Enrollment example = new Enrollment();
            example.setStudentId(enrollForm.getStudentId());
            example.setSubjectId(enrollForm.getSubjectId());
            Optional<Enrollment> enrollment = enrollmentRepository.findOne(Example.of(example));

            if(enrollment.isPresent()) {
                enrollmentRepository.delete(enrollment.get());
            } else {
                return ResponseEntity.badRequest().body("Not enrolled.");
            }

            return ResponseEntity.ok().build();
        }
        else {
            throw new BadAuthException();
        }
    }
}
