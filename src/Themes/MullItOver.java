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
    String regex = "mul\\(\\d+,\\d+\\)";
    Pattern pattern = Pattern.compile(regex);
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
        List<String> listOfMuls = getMuls();
        int sum = 0;
        for(String mul : listOfMuls){
            sum = sum + getProduct(mul);
        }
        return 0;
    }

    private List<String> getMuls(){
        Pattern pattern = Pattern.compile(regex);
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
