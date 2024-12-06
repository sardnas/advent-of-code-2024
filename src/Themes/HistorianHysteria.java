package Themes;//**--- Day 1: Historian Hysteria ---
//Data: https://adventofcode.com/2024/day/1/input

import Helpers.URLReader;

import java.util.ArrayList;
import java.util.Collections;

public class HistorianHysteria {
    String input;
    public HistorianHysteria(){
        try {
            URLReader reader = new URLReader();
            String data = reader.getData("https://adventofcode.com/2024/day/1/input");
            input = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalDistance(){

        return calculateDistance().stream().mapToInt(Integer::intValue).sum();
    }

    public int getSimilarityScore(){

        ArrayList<Integer> leftList = createLeftList();
        ArrayList<Integer> rightList = createRightList();

        int score = 0;
        ArrayList<Integer> numbersAlreadyChecked = new ArrayList<>();

        for(Integer number : leftList){
            if(!numbersAlreadyChecked.contains(number)){
                numbersAlreadyChecked.add(number);
                score += (int) (rightList.stream()
                                        .filter(x -> x.equals(number))
                                        .count() * number);

            }
        }

        return score;
    }

    private ArrayList<Integer> calculateDistance(){
        ArrayList<Integer> leftList = createLeftList();
        ArrayList<Integer> rightList = createRightList();

        Collections.sort(leftList);
        Collections.sort(rightList);

        ArrayList<Integer> distanceArray = new ArrayList<>();
        int distance = 0;

        for(int i = 0; i < leftList.size(); i++){
            distance = Math.abs(leftList.get(i) - rightList.get(i));
            distanceArray.add(distance);
        }
        return distanceArray;
    }


    private ArrayList<Integer> createLeftList() {
        ArrayList<Integer> list = new ArrayList<>();

        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length > 0) {
                list.add(Integer.parseInt(tokens[0]));
            }
        }

        return list;
    }
    private ArrayList<Integer> createRightList() {
        ArrayList<Integer> list = new ArrayList<>();

        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length > 0) {
                list.add(Integer.parseInt(tokens[1]));
            }
        }

        return list;
    }

}
