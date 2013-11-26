package board;

/**
 * @author wukat
 *
 */
public class BoardCell {

    private String content; // content of cell
    private final Boolean[][] abilities; // array of abilities - hor/ver beg/in/end
    // enable/disable

    public static final int beg = 0;
    public static final int in = 1;
    public static final int end = 2;
    public static final int hor = 0;
    public static final int ver = 1;

    /**
     *
     * Constructor
     *
     * @param content - content of cell
     */
    public BoardCell(String content) {
        this.content = content;
        this.abilities = new Boolean[3][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                abilities[i][j] = Boolean.TRUE;
            }
        }
    }

    /**
     * Checks if there is any content in cell
     *
     * @return true if cell have non zero-length content
     */
    public boolean checkContent() {
        return getContent().length() > 0;
    }

    /**
     * Sets ability in certain direction/position
     *
     * @param pos - position (beg, in, end)
     * @param dir - direction (ver, hor)
     * @param ability - boolean - true if enable
     */
    public void setAbility(int pos, int dir, Boolean ability) {
        abilities[pos][dir] = ability;
    }

    /**
     * Gets ability in certain direction/position
     *
     * @param pos - position (beg, in, end)
     * @param dir - direction (ver, hor)
     * @return ability - boolean - true if enable
     */
    public Boolean getAbility(int pos, int dir) {
        return abilities[pos][dir];
    }

    /**
     * Getter
     *
     * @return the content of cell
     */
    public String getContent() {
        return content;
    }

    /**
     * string content setter
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * char content setter
     *
     * @param content the content to set
     */
    public void setContent(char content) {
        this.content = "" + content;
    }

    /**
     * Sets vertical abilities false.
     */
    public void setVerFalse() {
        abilities[beg][ver] = Boolean.FALSE;
        abilities[in][ver] = Boolean.FALSE;
        abilities[end][ver] = Boolean.FALSE;
    }

    /**
     * Sets horizontal abilities false.
     */
    public void setHorFalse() {
        abilities[beg][hor] = Boolean.FALSE;
        abilities[in][hor] = Boolean.FALSE;
        abilities[end][hor] = Boolean.FALSE;
    }

    /**
     * Sets all cell's abilities false.
     */
    public void setFalse() {
        setHorFalse();
        setVerFalse();
    }

    /**
     * Function copying boardCell
     *
     * @return copy of cell
     */
    public BoardCell copy() {
        BoardCell boardCellCopy = new BoardCell(new String(this.getContent()));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                boardCellCopy.setAbility(i, j, new Boolean(getAbility(i, j)));
            }
        }
        return boardCellCopy;
    }
}
