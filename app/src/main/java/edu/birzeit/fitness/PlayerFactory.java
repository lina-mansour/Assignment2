package edu.birzeit.fitness;

public class PlayerFactory {
    public IFitnessData getModel(){

        return new PlayerData();
    }

}
