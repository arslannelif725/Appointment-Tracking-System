package org.example;

public class Patient {
    private int id;
    private String name;
    private String phone;
    public Patient(int id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public Patient(String name, String phone){
        this.name = name;
        this.phone = phone;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String phone(){
        return phone;
    }
    public void displayPatient(){
        System.out.println("ID: " + id + " | Hasta: " + name + " | Tel: " + phone);
    }
}
