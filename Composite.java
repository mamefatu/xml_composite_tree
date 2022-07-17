public abstract class Composite {

    private int level;
    private String name;

    public Composite(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public abstract void Show();
    
    public int getLevel() {
        return this.level;
    }
    public String getName() {
        return this.name;
    }
    
}
