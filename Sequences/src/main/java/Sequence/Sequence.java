package Sequence;

import operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    protected SequenceType sequenceType;
    protected List<Operation> sequence;

    public Sequence(){
        sequence = new ArrayList<Operation>();
    }

    public List<Operation> getSequence(){
        return sequence;
    }
}
