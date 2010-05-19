package paint;

import java.io.Serializable;

/**
 *
 * @author fires
 */
public class StepInfo implements Serializable{
    private int stepType;
    private Coordinate stepCoordinate;


    public StepInfo(int inStepType, Coordinate inStepCoordinate){
        stepType = inStepType;
        stepCoordinate = inStepCoordinate;
    }

    public int getStepType(){
        return stepType;
    }

    public Coordinate getStepCoordinate(){
        return stepCoordinate;
    }
}