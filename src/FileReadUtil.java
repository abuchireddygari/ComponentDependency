import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abuchireddygari on 7/10/2016.
 */
public class FileReadUtil {

    File fin = new File("./src/intialcomponentgraph.txt");
    public static HashMap<String,  ArrayList<Component>> readFile(File fin) throws Exception {
        // Construct BufferedReader from FileReader
        HashMap<String,  ArrayList<Component>> graph = new HashMap<String,  ArrayList<Component>>();
        BufferedReader br = new BufferedReader(new FileReader(fin));
        ComponentsDependency componentsDependencyobj = new ComponentsDependency();

        String line = null;
        while ((line = br.readLine()) != null) {
            graph = componentsDependencyobj.insertComponent(graph, line);
        }
        br.close();

        return graph;
    }

    /***
    public static void main(String args[]) throws Exception{

        FileReadUtil fileReadUtilobj = new FileReadUtil();
        readFile(fileReadUtilobj.fin);
    }
     ***/

}
