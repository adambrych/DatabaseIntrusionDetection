package Sequence;

import java.util.ArrayList;
import java.util.List;

public class RWSequences {
    private List<Sequence> sequences;
    private String role;

    public RWSequences(){
        setSequences(new ArrayList<Sequence>());
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
