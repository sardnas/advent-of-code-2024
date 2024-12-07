import Themes.HistorianHysteria;
import Themes.MullItOver;
import Themes.RedNosedReports;

public class Main {
    public static void main(String[] args) {
        HistorianHysteria historianHysteria = new HistorianHysteria();
        //System.out.printf("Day 1 Part one: %d \n", historianHysteria.getTotalDistance());
        //System.out.printf("Day 1 Part two: %d \n", historianHysteria.getSimilarityScore());
        RedNosedReports redNosedReports = new RedNosedReports();
        //System.out.printf("Day 2 Part one: %d \n", redNosedReports.getSafeReports());
        //System.out.printf("Day 2 Part two: %d \n", redNosedReports.getSafeReportsWithProblemDampener());
        MullItOver mullItOver = new MullItOver();
        System.out.printf("Day 3 Part one: %d \n", mullItOver.getMulSum());
        System.out.printf("Day 3 Part two: %d \n", mullItOver.getReEnabledMulSum());
    }
}