package Models;

import java.util.ArrayList;

public class Report {
    ArrayList<Integer> report;
    boolean isSafe;

    public Report(ArrayList<Integer> _report){
        report = _report;
        isSafe = safeIncrease() || safeDecrease();
    }
    public boolean getIsSafe(){
        return isSafe;
    }

    private boolean safeIncrease(){
        Integer prev = null;
        for(Integer value : report){
            if(prev != null){
                if(!((value <= (prev + 3)) && (value > prev))){
                    return false;
                }
            }
            prev = value;
        }
        return true;
    }

    private boolean safeDecrease(){
        Integer prev = null;
        for(Integer value : report){
            if(prev != null){
                if(!((value >=  (prev - 3)) && (value < prev))){
                    return false;
                }
            }
            prev = value;
        }
        return true;
    }

}
