package lab1;

import java.util.ArrayList;

public class LiczbyPierwsze {
	private Integer n;
    public LiczbyPierwsze(Integer _n) {
    	n = _n;
    }
    
    public ArrayList(0) znajdz() {
    	int [] result = null;
    	int j = 2;
    	for (int i = 2; i < n; i++) {
    		boolean flag = true;
    		j = 2;
    	    while (j * j < i && flag) {
    		    if (i % j == 0)
    		    	flag = false;
    		    j++;
    	    }
    		if (flag)
    			result(i);
    	}
    			
    }
    
}
