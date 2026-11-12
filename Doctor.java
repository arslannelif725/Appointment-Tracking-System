package org.example;

public class Doctor {
    private int id;
    private String name;
    private String branch;
    //constructor (nesene oluşturulurken kullanılacak)
    public Doctor(int id, String name, String branch){
        this.id = id;
        this.name = name;
        this.branch = branch;
    }
    public Doctor(String name, String branch){
        this.name = name;
        this.branch = branch;
    }
    //getter metotları
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getBranch(){
        return branch;
    }
    //doktor bilgilerini konsola yazdırmak için
    public void displayDoctor(){
        System.out.println("ID: " + id + " | Dr. " + name + " (" + branch + ")");
    }
}
