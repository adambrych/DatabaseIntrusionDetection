package operation;

public abstract class Operation {
    protected String column;
    private OperationType type;

    public void setColumn(String column){
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}
