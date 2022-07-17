import java.util.ArrayList;

public class Folder extends Composite {

    ArrayList<Composite> composants = new ArrayList<Composite>();

    public Folder(String nom, int niveau) {
        super(nom, niveau);
    }

    public void ajouter(Composite c) {
        composants.add(c);
    }

    public void Show() {

        int level = getLevel();
        for (int i = 0; i < level; i++) {
            System.out.print("│\t");
        }
        System.out.println("├──" + getName());
        for (Composite c : composants) {
            c.Show();
        }
    }
}
