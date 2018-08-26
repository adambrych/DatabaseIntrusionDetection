import enums.QueryType;

public class Query {
    private QueryType queryType;
    private FeatureVector featureVector;
    private String role;

    public Query(QueryType queryType){
        this.queryType = queryType;
        featureVector = new FeatureVector(queryType.getType());
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public FeatureVector getFeatureVector() {
        return featureVector;
    }

    public void setFeatureVector(FeatureVector featureVector) {
        this.featureVector = featureVector;
    }

    @Override
    public String toString(){
        return featureVector.toString() + "," + role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
