package com.example.college_bus_pass;

public class Student {

    String name,rollno,source,destination,charges;

    public Student() {
    }

    public Student(String name, String rollno, String source, String destination, String charges) {
        this.name = name;
        this.rollno = rollno;
        this.source = source;
        this.destination = destination;
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }
}
