package Sequence;

import operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    protected List<Operation> sequence;
    private SequenceType sequenceType;

    public Sequence(){
        setSequence(new ArrayList<Operation>());
    }

    public List<Operation> getSequence(){
        return sequence;
    }

    public void setSequence(List<Operation> sequence) {
        this.sequence = sequence;
    }

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }
}
