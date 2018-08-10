package operation.impl;

import operation.Operation;
import operation.OperationType;

public class Write extends Operation {
    public Write(){
        setType(OperationType.WRITE);
    }
}
