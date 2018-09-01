package Sequence.impl;

import Sequence.*;

public class ReadOnlySequence extends Sequence{
    public ReadOnlySequence(){
        super();
        this.setSequenceType(SequenceType.READ_ONLY);
    }
}
