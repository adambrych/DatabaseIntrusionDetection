import fingerprint.Fingerprint;
import fingerprint.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static final String QUERY_FILE = "queries.txt";
    private static List<Node> startNodes = new ArrayList<Node>();
    private static Set<String> roles = new HashSet<String>();

    public static void main(String[] args) {
        try {
            readQueriesFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readQueriesFromFile() throws IOException {
        Node previousNode = null;
        File file = new File(QUERY_FILE);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String query;
        while((query = br.readLine()) != null) {
            Fingerprint fingerprint = new Fingerprint(query);
            if(roles.contains(fingerprint.role))
                continue;

            if(previousNode == null) {
                previousNode = new Node(fingerprint);
                previousNode.setStart();
                startNodes.add(previousNode);
            }
            else if(!previousNode.getRole().equals(fingerprint.role)){
                roles.add(previousNode.getRole());
                previousNode = new Node(fingerprint);
                previousNode.setStart();
                startNodes.add(previousNode);
            }
            else{
                Node node = new Node(fingerprint);
                previousNode.setNext(node);
                previousNode = node;
            }
        }
    }
}
