package com.test.springboot.thymeleaf.controller;

import com.test.springboot.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/sendData")
    public ModelAndView sendData() {
        ModelAndView mav = new ModelAndView("data");
        mav.addObject("message", "Take up one idea and make it your life...!");
        return mav;
    }

    @RequestMapping("/student")
    public ModelAndView getStudent() {
        ModelAndView mav = new ModelAndView("student");
        Student s = new Student();
        s.setName("pradeep");
        s.setScore(499);
        mav.addObject("student", s);
        return mav;
    }
    @RequestMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView mav = new ModelAndView("studentList");
        Student s1 = new Student();
        s1.setName("pradeep");
        s1.setScore(499);

        Student s2 = new Student();
        s2.setName("jyo");
        s2.setScore(549);

        List<Student> students = Arrays.asList(s1,s2);

        mav.addObject("students", students);
        return mav;
    }

    @RequestMapping("/studentForm")
    public ModelAndView displayStudentForm() {
        ModelAndView mav = new ModelAndView("studentForm");
        Student s = new Student();
        s.setName("pradeep");
        s.setScore(499);
        mav.addObject("student", s);
        return mav;
    }
}
