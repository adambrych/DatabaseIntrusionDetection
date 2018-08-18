import java.util.ArrayList;
import java.util.List;

public class FeatureVector {
    private int queryType;
    private int length;
    private int numberOfProjections;
    private String projections;
    private int numberOfAttributes;
    private List<String> attributesElements;
    private List<String> projectionElements;
    private List<Integer> numberOfAttributesFromProjection;
    private List<String> positionOfAttributes;
    private int numberOfSelection;
    private List<Integer> numberOfAttributesFromSelection;
    private List<String> positionOfSelection;
    private int numberOfOrder;
    private List<Integer> numberOfAttributesFromOrder;
    private List<String> positionOfOrder;
    private int numberOfGroup;
    private List<Integer> numberOfAttributesFromGroup;
    private List<String> positionOfGroup;
    private int stringValues;
    private int lengthOfStringValues;
    private int numberOfNumericValues;
    private int numberOfJoins;
    private int numberOfAndOr;

    public FeatureVector(int type){
        this.setQueryType(type);
        this.setLength(0);
        this.setProjectionElements(new ArrayList<String>());
        this.setNumberOfProjections(0);
        setProjections("");
        setNumberOfAttributes(0);
        setNumberOfAttributesFromProjection(new ArrayList<Integer>());
        setPositionOfAttributes(new ArrayList<String>());
        setNumberOfSelection(0);
        setNumberOfAttributesFromSelection(new ArrayList<Integer>());
        setPositionOfSelection(new ArrayList<String>());
        setNumberOfOrder(0);
        setNumberOfAttributesFromOrder(new ArrayList<Integer>());
        setPositionOfOrder(new ArrayList<String>());
        setNumberOfGroup(0);
        setNumberOfAttributesFromGroup(new ArrayList<Integer>());
        setPositionOfGroup(new ArrayList<String>());
        setStringValues(0);
        setLengthOfStringValues(0);
        setNumberOfNumericValues(0);
        setNumberOfJoins(0);
        setNumberOfAndOr(0);
        setAttributesElements(new ArrayList<String>());
    }

    public int getQueryType() {
        return queryType;
    }

    public void setQueryType(int queryType) {
        this.queryType = queryType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumberOfProjections() {
        return numberOfProjections;
    }

    public void setNumberOfProjections(int numberOfProjections) {
        this.numberOfProjections = numberOfProjections;
    }

    public String getProjections() {
        return projections;
    }

    public void setProjections(String projections) {
        this.projections = projections;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public void setNumberOfAttributes(int numberOfAttributes) {
        this.numberOfAttributes = numberOfAttributes;
    }

    public List<Integer> getNumberOfAttributesFromProjection() {
        return numberOfAttributesFromProjection;
    }

    public void setNumberOfAttributesFromProjection(List<Integer> numberOfAttributesFromProjection) {
        this.numberOfAttributesFromProjection = numberOfAttributesFromProjection;
    }

    public List<String> getPositionOfAttributes() {
        return positionOfAttributes;
    }

    public void setPositionOfAttributes(List<String> positionOfAttributes) {
        this.positionOfAttributes = positionOfAttributes;
    }

    public int getNumberOfSelection() {
        return numberOfSelection;
    }

    public void setNumberOfSelection(int numberOfSelection) {
        this.numberOfSelection = numberOfSelection;
    }

    public List<Integer> getNumberOfAttributesFromSelection() {
        return numberOfAttributesFromSelection;
    }

    public void setNumberOfAttributesFromSelection(List<Integer> numberOfAttributesFromSelection) {
        this.numberOfAttributesFromSelection = numberOfAttributesFromSelection;
    }

    public List<String> getPositionOfSelection() {
        return positionOfSelection;
    }

    public void setPositionOfSelection(List<String> positionOfSelection) {
        this.positionOfSelection = positionOfSelection;
    }

    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public List<Integer> getNumberOfAttributesFromOrder() {
        return numberOfAttributesFromOrder;
    }

    public void setNumberOfAttributesFromOrder(List<Integer> numberOfAttributesFromOrder) {
        this.numberOfAttributesFromOrder = numberOfAttributesFromOrder;
    }

    public List<String> getPositionOfOrder() {
        return positionOfOrder;
    }

    public void setPositionOfOrder(List<String> positionOfOrder) {
        this.positionOfOrder = positionOfOrder;
    }

    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public List<Integer> getNumberOfAttributesFromGroup() {
        return numberOfAttributesFromGroup;
    }

    public void setNumberOfAttributesFromGroup(List<Integer> numberOfAttributesFromGroup) {
        this.numberOfAttributesFromGroup = numberOfAttributesFromGroup;
    }

    public List<String> getPositionOfGroup() {
        return positionOfGroup;
    }

    public void setPositionOfGroup(List<String> positionOfGroup) {
        this.positionOfGroup = positionOfGroup;
    }

    public int getStringValues() {
        return stringValues;
    }

    public void setStringValues(int stringValues) {
        this.stringValues = stringValues;
    }

    public int getLengthOfStringValues() {
        return lengthOfStringValues;
    }

    public void setLengthOfStringValues(int lengthOfStringValues) {
        this.lengthOfStringValues = lengthOfStringValues;
    }

    public int getNumberOfNumericValues() {
        return numberOfNumericValues;
    }

    public void setNumberOfNumericValues(int numberOfNumericValues) {
        this.numberOfNumericValues = numberOfNumericValues;
    }

    public int getNumberOfJoins() {
        return numberOfJoins;
    }

    public void setNumberOfJoins(int numberOfJoins) {
        this.numberOfJoins = numberOfJoins;
    }

    public int getNumberOfAndOr() {
        return numberOfAndOr;
    }

    public void setNumberOfAndOr(int numberOfAndOr) {
        this.numberOfAndOr = numberOfAndOr;
    }

    public List<String> getProjectionElements() {
        return projectionElements;
    }

    public void setProjectionElements(List<String> projectionElements) {
        this.projectionElements = projectionElements;
    }

    public List<String> getAttributesElements() {
        return attributesElements;
    }

    public void setAttributesElements(List<String> attributesElements) {
        this.attributesElements = attributesElements;
    }
}
