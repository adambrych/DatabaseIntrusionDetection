package Sequence;

import java.util.ArrayList;
import java.util.List;

public class RWSequences {
    private List<Sequence> sequences;

    public RWSequences(){
        setSequences(new ArrayList<Sequence>());
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequence> sequences) {
        this.sequences = sequences;
    }
}
