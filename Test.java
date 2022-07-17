import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;

public class Test {
    private static int niveau;

    public static String pathToXml(String path) {

        File directory = new File(path);
        String xmlstring = "\n" + "<directory name = " + "'/" + directory.getName() + "'" + ">";
        File[] liste = directory.listFiles();
        for (File df : liste) {
            if (df.isFile()) {
                xmlstring = xmlstring + "\n" + "\t" + "<file name = " + "'" + df.getName() + "'" + "/>";
            } else if (df.isDirectory()) {
                xmlstring = xmlstring + pathToXml(df.getAbsolutePath());
            }
        }

        xmlstring = xmlstring + "\n" + "</directory>" + "\n";
        return xmlstring;

    }

    public static Composite insertion(Element e) {
        Folder home = new Folder(e.getAttribute("name"), niveau);
        NodeList nodes = e.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) nodes.item(i);
                if (el.getNodeName().equals("file")) {
                    Composite myFile;
                    myFile = new Fichier(el.getAttribute("name"), home.getLevel() + 1);
                    home.ajouter(myFile);
                } else if (el.getNodeName().equals("directory")) {
                    niveau = home.getLevel() + 1;
                    home.ajouter(insertion(el));
                    niveau--;
                }
            }
        }
        return home;
    }

    public static Composite xmlToDoc(String xmlstring) throws ParserConfigurationException, SAXException, IOException {
        String xmlStr = "<?xml version=\"1.0\"?>" + xmlstring;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append(xmlStr);
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element element = doc.getDocumentElement();
        return insertion(element);
    }
   public static void RecursivePrint(File[] arr, int level)
	{
		for (File f : arr) {
			for (int i = 0; i < level; i++)
				System.out.print("\t");

			if (f.isFile())
				System.out.println("\t|___"+f.getName());

			else if (f.isDirectory()) {
				System.out.println(  "directory:"+f.getName() + ":");

				RecursivePrint(f.listFiles(), level + 1);
			}
		}
	}

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {



        String xmlString1 = pathToXml("/Users/mame fatou/Desktop/and_files");
        System.out.println(xmlString1);

         String maindirpath
         = "/Users/mame fatou/Desktop/and_files";

     File maindir = new File(maindirpath);
     if (maindir.exists() && maindir.isDirectory()) {
        File arr[] = maindir.listFiles();

        System.out.println("**************************************************************");

        System.out.println(
            "Files from main directory : " + maindir);
            System.out.println("**************************************************************");

        RecursivePrint(arr, 0);
    }

    }
}
