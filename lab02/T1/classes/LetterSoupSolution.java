package lab01.classes;

import java.util.Collections;
import java.util.List;

public class LetterSoupSolution { 
    LetterSoup letterSoup;
    List<Solution> allSolutions;
    
    public LetterSoupSolution(LetterSoup letterSoup,List<Solution> allSolutions) {
        Collections.sort(allSolutions, (s1, s2) -> { return ((Integer)s1.getOrder()).compareTo((Integer)s2.getOrder());});
        this.allSolutions = allSolutions;
        this.letterSoup = letterSoup;
    }

    public List<Solution> getAllSolutions() {
        return allSolutions;
    }

    public LetterSoup getLetterSoup() {
        return letterSoup;
    }
    
}
