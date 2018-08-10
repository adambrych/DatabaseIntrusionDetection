package operation.impl;

import operation.Operation;
import operation.OperationType;

public class Read extends Operation {
   public Read(){
       setType(OperationType.READ);
   }
}
