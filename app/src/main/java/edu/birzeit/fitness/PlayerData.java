package edu.birzeit.fitness;

public class PlayerData implements IFitnessData {


    @Override
    public String[] getGenders() {
        String[] gender = new String[] {"Select Gender", "Male", "Female"};
        return gender;
    }
}
