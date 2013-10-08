package lab1;

public class LiczbyPierwsze {
	private Integer n;
    public LiczbyPierwsze(Integer _n) {
    	n = _n;
    }
    
    public void znajdz() {
    	int j = 2;
    	for (int i = 2; i < n; i++) {
    		boolean flag = true;
    		j = 2;
    	    while (j * j <= i && flag) {
    		    if (i % j == 0)
    		    	flag = false;
    		    j++;
    	    }
    		if (flag)
    			System.out.println(i);
    	}			
    }
    
}
