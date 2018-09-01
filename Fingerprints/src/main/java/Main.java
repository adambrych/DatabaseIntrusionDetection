import fingerprint.Fingerprint;
import fingerprint.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    private static final String QUERY_FILE = "queriesSingle.txt";
    private static final String TEST_FILE = "queriesSingle_additionalColumn.txt";
    private static List<Node> startNodes = new ArrayList<Node>();
    private static List<Node> startTestNodes = new ArrayList<Node>();

    public static void main(String[] args) {
        try {
            readQueriesFromFile();
            prepareTestNodes();
            test();
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

            if(previousNode == null) {
                previousNode = new Node(fingerprint);
                previousNode.setStart();
                startNodes.add(previousNode);
            }
            else if(!previousNode.getRole().equals(fingerprint.role)){
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

    private static void prepareTestNodes() throws IOException {
        Node previousNode = null;
        File file = new File(TEST_FILE);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String query;
        while((query = br.readLine()) != null) {
            Fingerprint fingerprint = new Fingerprint(query);

            if(previousNode == null) {
                previousNode = new Node(fingerprint);
                previousNode.setStart();
                startTestNodes.add(previousNode);
            }
            else if(!previousNode.getRole().equals(fingerprint.role)){
                previousNode = new Node(fingerprint);
                previousNode.setStart();
                startTestNodes.add(previousNode);
            }
            else{
                Node node = new Node(fingerprint);
                previousNode.setNext(node);
                previousNode = node;
            }
        }
    }

    private static void test(){
        for(Node node : startNodes){
            for(Node testNode : startTestNodes) {
                if (node.getRole().equals(testNode.getRole())) {
                    Node newNode = node;
                    Node newTestNode = testNode;
                    boolean good = true;
                    while(true){
                        if(newNode.getFingerprint().getPattern().pattern().equals(newTestNode.getFingerprint().getPattern())
                                && newNode.getNext() != null && newTestNode.getNext() != null){
                            newNode = newNode.getNext();
                            newTestNode = newTestNode.getNext();
                        }
                        else if(newNode.getFingerprint().getPattern().pattern().equals(newTestNode.getFingerprint().getPattern())
                                && (newNode.getNext() == null || newTestNode.getNext() == null)){
                            System.out.println("Rola " + newNode.getRole() + " brak zapytania");
                            good = false;
                            break;
                        }
                        else if(!newNode.getFingerprint().getPattern().pattern().equals(newTestNode.getFingerprint().getPattern())){
                            System.out.println("Rola " + newNode.getRole() + " różnica " + newNode.getFingerprint().getPattern().pattern() + " " + newTestNode.getFingerprint().getPattern());
                            good = false;
                            break;
                        }
                        else
                            break;
                    }
                    if(good){
                        System.out.println("Rola " + node.getRole() + " poprawne");
                    }
                }
            }
        }
    }
}
