package office.openbet.model;


public enum SelectionTypeEnum {
	
	home("home"),
	away("away"),
	draw("draw"),
	odd("odd"),
	even("even"),
	over("over"),
	line("line"),
	under("under"),
	high("high"),
	low("low"),
	score("score"),
	none("none"),
	homehome("home/home"),
	homedraw("home/draw"),
	homeaway("home/away"),
	drawhome("draw/home"),
	drawdraw("draw/draw"),
	drawaway("draw/away"),
	awayhome("away/home"),
	awaydraw("away/draw"),
	awayaway("away/away");

    private final String type;

    /**
     * @param type
     */
    SelectionTypeEnum(final String type) {

        this.type = type;
    }

    /**
     * @return type
     */
    public String getType() {

        return type;
    }
    
   
}
