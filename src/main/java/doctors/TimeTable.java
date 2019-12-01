package doctors;

import java.util.Arrays;
import java.util.Collections;

public class TimeTable {
    private String[][] timeTable = {{"1a","2a","3a","4a","5a","6a","7a"},{"1b","2b","3b","4b","5b","6b","7b"},{"1c","2c","3c","4c","5c","6c","7c"}};
    private int numPatients;
    private int numDoctors;
    private int [][] docAllo = new int[7][3];       //Doctor allocation, requires the number of doctors in a time slot to be at least able to
                                                    //look after the number of patients in a ward.

    public TimeTable(int numPatients, int numDoctors) {
        this.numPatients = numPatients;
        this.numDoctors = numDoctors;
    }

    public String getSlot (int col, char rowC){
        int row = 0;
        if (rowC == 'a') {
            row = 1;
        }else if (rowC == 'b') {
            row = 2;
        }else if (rowC == 'c'){
            row = 3;
        }
        docAllo[col][row]++;
        return timeTable[col][row];
    }
}
