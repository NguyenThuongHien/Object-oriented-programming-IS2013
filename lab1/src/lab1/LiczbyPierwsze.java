package lab1;

/**
 * @brief Class finding prim's numbers
 * @author wukat
 *
 */
public class LiczbyPierwsze {
	private Integer n; /** n - integer, founded prim's will be smaller than it **/
	
	/**
	 * @brief Constructor
	 * @param _n
	 */
    public LiczbyPierwsze(Integer _n) {
    	n = _n;
    }
    
    /**
     * @brief Function founds and prints prim's numbers
     */
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
