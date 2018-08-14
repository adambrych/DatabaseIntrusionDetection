public enum QueryType {
    SELECT(1),
    INSERT(2),
    UPDATE(3),
    DELETE(4);

    private int type;

    QueryType(int type){
        this.setType(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
