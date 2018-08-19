import java.util.ArrayList;
import java.util.List;

public class FeatureVector {
    private int queryType;
    private int length;
    private int numberOfProjections;
    private String projections;
    private int numberOfAttributes;
    private int[] attributesElements;
    private List<String> positionOfAttributes;
    private int numberOfSelection;
    private int[] numberOfAttributesFromSelection;
    private List<String> positionOfSelection;
    private int numberOfOrder;
    private int[] numberOfAttributesFromOrder;
    private List<String> positionOfOrder;
    private int numberOfGroup;
    private int[] numberOfAttributesFromGroup;
    private List<String> positionOfGroup;
    private int stringValues;
    private int lengthOfStringValues;
    private int numberOfNumericValues;
    private int numberOfJoins;
    private int numberOfAndOr;

    public FeatureVector(int type){
        this.setQueryType(type);
        this.setLength(0);
        this.setNumberOfProjections(0);
        setProjections("");
        setNumberOfAttributes(0);
        setPositionOfAttributes(new ArrayList<String>());
        setNumberOfSelection(0);
        setPositionOfSelection(new ArrayList<String>());
        setNumberOfOrder(0);
        setPositionOfOrder(new ArrayList<String>());
        setNumberOfGroup(0);
        setPositionOfGroup(new ArrayList<String>());
        setStringValues(0);
        setLengthOfStringValues(0);
        setNumberOfNumericValues(0);
        setNumberOfJoins(0);
        setNumberOfAndOr(0);
        setAttributesElements(initializeTable());
        setNumberOfAttributesFromSelection(initializeTable());
        setNumberOfAttributesFromGroup(initializeTable());
        setNumberOfAttributesFromOrder(initializeTable());
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

    private int[] initializeTable(){
        int[] table = new int[33];
        for(int i=0; i<33; i++){
            table[i] = 0;
        }
        return table;
    }

    public int[] getAttributesElements() {
        return attributesElements;
    }

    public void setAttributesElements(int[] attributesElements) {
        this.attributesElements = attributesElements;
    }

    public int[] getNumberOfAttributesFromSelection() {
        return numberOfAttributesFromSelection;
    }

    public void setNumberOfAttributesFromSelection(int[] numberOfAttributesFromSelection) {
        this.numberOfAttributesFromSelection = numberOfAttributesFromSelection;
    }

    public int[] getNumberOfAttributesFromOrder() {
        return numberOfAttributesFromOrder;
    }

    public void setNumberOfAttributesFromOrder(int[] numberOfAttributesFromOrder) {
        this.numberOfAttributesFromOrder = numberOfAttributesFromOrder;
    }

    public int[] getNumberOfAttributesFromGroup() {
        return numberOfAttributesFromGroup;
    }

    public void setNumberOfAttributesFromGroup(int[] numberOfAttributesFromGroup) {
        this.numberOfAttributesFromGroup = numberOfAttributesFromGroup;
    }
}
