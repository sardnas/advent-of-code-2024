package Themes;

import Helpers.URLReader;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {
    String input;
    String testInput;
    String matchMulRegex = "mul\\(\\d+,\\d+\\)";
    String matchDontMulRegex = "mul\\(\\d+,\\d+\\)";

    Pattern pattern = Pattern.compile(matchMulRegex);
    public MullItOver(){
        try {
            URLReader reader = new URLReader();
            String data = reader.getData("https://adventofcode.com/2024/day/3/input");
            input = data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            String readfile = Files.readAllLines(Path.of("C:\\code\\advent-of-code-2024\\TestData\\dayThree"), Charset.defaultCharset()).toString();
            testInput = readfile;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMulSum(){
        return getSum(getMuls());
    }
    public int getReEnabledMulSum(){
        List<String> allMulls = getMuls();
        List<String> dontMuls = getDontMuls();
        List<String> result = removeDuplicates(allMulls, dontMuls);
        return getSum(result);
    }

    private int getSum(List<String> list){
        int sum = 0;
        for(String mul : list){
            sum = sum + getProduct(mul);
        }
        return sum;
    }
    private List<String> removeDuplicates(List<String> list, List<String> duplicates){
        list.removeAll(duplicates);
        return list;
    }
    private List<String> getDontMuls(){
        Pattern pattern = Pattern.compile(matchDontMulRegex);
        Matcher matcher = pattern.matcher(testInput);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    private List<String> getMuls(){
        Pattern pattern = Pattern.compile(matchMulRegex);
        Matcher matcher = pattern.matcher(testInput);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    private int getProduct(String mulExpression){
        String reg = "\\d+";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(mulExpression);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return Integer.parseInt(matches.get(0)) * Integer.parseInt(matches.get(1));
    }
}
