package Sequence;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String role;
    private List<Sequence> sequences;

    public Transaction(String role){
        this.setRole(role);
        setSequences(new ArrayList());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }
}
