import enums.QueryType;

public class Query {
    private QueryType queryType;
    private FeatureVector featureVector;

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
}
