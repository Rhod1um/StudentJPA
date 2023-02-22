package com.example.studentjpa.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "students") //hvis man ikke skriver det får tabellen samme navn som klassen
public class Student {
    //spring.h2.console.enabled=true
    //skal skrives i application.properties under ressources

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.AUTO laver en tabel
    // ved siden af med id?? som tæller id'er
    //hvis man pludselighar en mærkelig tabel man ikke ved hvor kommer fra er det måske fordi
    //man brugte AUTO
    private int id;

    private String name;
    private LocalDate bornDate;
    private LocalTime bornTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public LocalTime getBornTime() {
        return bornTime;
    }

    public void setBornTime(LocalTime bornTime) {
        this.bornTime = bornTime;
    }

    public Student(){} //behøver egentlig ikke tom konstruktor som det er nu, men i test
    //sætter vi ikke navn, det er bare nemmere
    public Student(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bornDate=" + bornDate +
                ", bornTime=" + bornTime +
                '}';
    }
}
