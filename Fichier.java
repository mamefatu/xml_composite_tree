public class Fichier extends Composite {

    public Fichier(String name, int level) {
        super(name, level);
    }

    public void Show() {
        int niveau = getLevel();
        for (int i = 0; i < niveau; i++) {
            System.out.print("│\t");
        }
        System.out.println("├──" + getName());
    }
}
