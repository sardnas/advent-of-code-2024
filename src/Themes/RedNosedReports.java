package Themes;

import Helpers.URLReader;
import Models.Report;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {
    String input;
    public RedNosedReports(){
        try {
            URLReader reader = new URLReader();
            String data = reader.getData("https://adventofcode.com/2024/day/2/input");
            input = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getSafeReports(){
        int count = 0;
        ArrayList<ArrayList<Integer>> reports = createArrayFromInput();
        for(ArrayList<Integer> report : reports){
            Report r = new Report(report);
            if(r.getIsSafe()) {
                count++;
            }
        }
        return count;
    }
    public int getSafeReportsWithProblemDampener(){
        int count = 0;
        ArrayList<ArrayList<Integer>> reports = createArrayFromInput();
        for(ArrayList<Integer> report : reports){
            Report r = new Report(report);
            if(r.getIsSafe()) {
                count++;
            }else if(useProblemDampener(report)){
                count++;
            }
        }
        return count;
    }

    private boolean useProblemDampener(ArrayList<Integer> report) {
        for(int i = 0; i < report.size(); i++)
       {
           ArrayList<Integer> modifiedReport = removeElementAtIndex(report, i);
           Report r = new Report(modifiedReport);
           if(r.getIsSafe()) {
               return true;
           }
       }
       return false;
    }

    private ArrayList<Integer> removeElementAtIndex(ArrayList<Integer> report, int index)
    {
        ArrayList<Integer> newReport = new ArrayList<>();
        for(int i = 0; i < report.size(); i++)
        {
            if(i != index)
            {
                newReport.add(report.get(i));
            }
        }
        return newReport;
    }


    private ArrayList<ArrayList<Integer>> createArrayFromInput() {
        ArrayList<ArrayList<Integer>> reportList = new ArrayList<>();

        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] tokens = line.trim().split("\\s+");
            if (tokens.length > 0) {
                ArrayList<Integer> report = new ArrayList<>();
                for(String token : tokens){
                    report.add(Integer.parseInt(token));
                }
                reportList.add(report);
            }
        }

        return reportList;
    }
    private ArrayList<ArrayList<Integer>> createArrayFromTestData(){
        ArrayList<ArrayList<Integer>> reportList = new ArrayList<>();
        try {
            List<String> readfile = Files.readAllLines(Path.of("C:\\code\\advent-of-code-2024\\TestData\\dayTwo"), Charset.defaultCharset());
            String[] lines = readfile.toArray(new String[readfile.size()]);
            for (String line : lines) {
                String[] tokens = line.trim().split("\\s+");
                if (tokens.length > 0) {
                    ArrayList<Integer> report = new ArrayList<>();
                    for(String token : tokens){
                        report.add(Integer.parseInt(token));
                    }
                    reportList.add(report);
                }
            }
            return reportList;
        }catch(Exception e){
            throw new RuntimeException("Error fetching data from file", e);
        }
    }
}
