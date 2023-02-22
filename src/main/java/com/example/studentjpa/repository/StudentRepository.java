package com.example.studentjpa.repository;

import com.example.studentjpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository <Student, Integer> {

    //til unit test: gå til Generate som ved gettere og settere og vælg test, så
    //oprattes automatisk en klasse i test pakken

    //det optional objekt som er en pegepind til enten et objekt eller null:
    Optional<Student> findByName(String name);

}
