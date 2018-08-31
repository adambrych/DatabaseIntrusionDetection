package fingerprint;

public class Node {
    private boolean start = false;
    private Fingerprint fingerprint;
    private Node next;

    public Node(Fingerprint fingerprint){
        this.setFingerprint(fingerprint);
    }

    public String getRole(){
        return getFingerprint().role;
    }

    public void setStart(){
        this.start = true;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Fingerprint getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(Fingerprint fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Node getNext() {
        return next;
    }
}
