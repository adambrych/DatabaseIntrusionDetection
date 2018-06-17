package fingerprint;

public class Node {
    private boolean start = false;
    private Fingerprint fingerprint;
    private Node next;

    public Node(Fingerprint fingerprint){
        this.fingerprint = fingerprint;
    }

    public String getRole(){
        return fingerprint.role;
    }

    public void setStart(){
        this.start = true;
    }

    public void setNext(Node next){
        this.next = next;
    }

}
