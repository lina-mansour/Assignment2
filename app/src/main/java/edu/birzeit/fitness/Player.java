package edu.birzeit.fitness;

public class Player {
    private String name;
    private double height;
    private double weight;
    private String gender;
    private String BMI;

    public Player(){
    }

    public Player(String name, double height, double weight, String gender, String BMI) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.BMI = BMI;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
