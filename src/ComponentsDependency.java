import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by abuchireddygari on 7/10/2016.
 */
public class ComponentsDependency {
    static HashMap<String, ArrayList<Component>> graph = new HashMap<String, ArrayList<Component>>();

    public  static void main(String args[]) throws Exception {
        FileReadUtil fileReadUtilobj = new FileReadUtil();
        graph = fileReadUtilobj.readFile(fileReadUtilobj.fin);
        printComponentGraph(graph);

        String removeLabel = "component3";
        removeComponent(removeLabel);

        printComponentGraph(graph);
    }

    public static HashMap<String, ArrayList<Component>> insertComponent(HashMap<String, ArrayList<Component>> graph, String line) throws Exception{
        //System.out.println(line);
        String componentlabel[] = line.split(":");
        String dependentlabels[] = componentlabel[1].split(",");

        ArrayList<Component>  arrayListDependentLabels;

        if(graph.containsKey(componentlabel[0]))
            arrayListDependentLabels = graph.get(componentlabel[0]);
        else
            arrayListDependentLabels = new ArrayList<Component>();

        for(String label : dependentlabels ) {
            Component componentobj = new Component();
            componentobj.label = label;
            componentobj.type = 1;
            arrayListDependentLabels.add(componentobj);
            ArrayList<Component> dependentdependencylist;
            if (graph.containsKey(label)) {
                dependentdependencylist = graph.get(label);
            }
            else {
                dependentdependencylist = new ArrayList<>();
            }
            Component dependentcomponentobj = new Component();
            dependentcomponentobj.label = componentlabel[0];
            dependentcomponentobj.type = -1;
            dependentdependencylist.add(dependentcomponentobj);
            graph.put(label, dependentdependencylist);
        }

    graph.put(componentlabel[0], arrayListDependentLabels);

    return graph;
    }

    private static void removeComponent(String componentLabel){
        ArrayList<Component> dependentlabels = graph.get(componentLabel);
        HashMap<String,Boolean> removeHashMap = new HashMap<>();
        removeHashMap.put(componentLabel,Boolean.TRUE);
        boolean removeComponentflag= true;
        for(int i=dependentlabels.size()-1; i>=0; i--) {
            //System.out.println(componentObj.label);
            Component componentObj = dependentlabels.get(i);
            if(componentObj.type == 1) {
                ArrayList<Component> dependentdependencylabels = graph.get(componentObj.label);
                boolean removeflag= true;
                //System.out.println("component "+componentObj.label );
                for(Component dependentObj: dependentdependencylabels) {
                    if( dependentObj.type == -1 && !removeHashMap.containsKey(dependentObj.label)) {
                        removeflag = false;
                        //System.out.println(dependentObj.label);
                        break;
                    }
                }
                if(removeflag) {
                    removeHashMap.putIfAbsent(componentObj.label, Boolean.TRUE);
                    graph.remove(componentObj.label);
                    dependentlabels.remove(componentObj);

                }
            }
            else {
                removeComponentflag = false;
            }
        }
        if(removeComponentflag)
            graph.remove(componentLabel);
    }

    private static void printComponentGraph(HashMap<String, ArrayList<Component>> graph) {
        System.out.println(" The Graph is");
        Iterator it = graph.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + ":");
            ArrayList<Component> arrayListComponent = (ArrayList)pair.getValue();
            for (Component componentObj: arrayListComponent) {
                if(componentObj.type == 1)
                    System.out.print(componentObj.label /**+" type "+componentObj.type***/ + ", ");
            }
            System.out.println();
           // it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
