package Sequence;

import operation.Operation;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private String role;
    private List<Sequence> sequences;
    private List<Operation> joinedList;

    public Transaction(String role){
        this.setRole(role);
        setSequences(new ArrayList());
    }

    public void joinSequences(){
        setJoinedList(new ArrayList<Operation>());
        for(Sequence sequence : sequences)
            for(Operation operation : sequence.getSequence())
                getJoinedList().add(operation);

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

    public List<Operation> getJoinedList() {
        return joinedList;
    }

    public void setJoinedList(List<Operation> joinedList) {
        this.joinedList = joinedList;
    }
}
