import java.util.ArrayList;
import java.util.List;

public class FeatureVector {
    private int queryType;
    private int length;
    private int numberOfProjections;
    private int projections;
    private int numberOfAttributes;
    private int[] attributesElements;
    private int[] positionOfAttributes;
    private int numberOfSelection;
    private int[] numberOfAttributesFromSelection;
    private int[] positionOfSelection;
    private int numberOfOrder;
    private int[] numberOfAttributesFromOrder;
    private int[] positionOfOrder;
    private int numberOfGroup;
    private int[] numberOfAttributesFromGroup;
    private int[] positionOfGroup;
    private int stringValues;
    private int lengthOfStringValues;
    private int numberOfNumericValues;
    private int numberOfJoins;
    private int numberOfAndOr;

    public FeatureVector(int type){
        this.setQueryType(type);
        this.setLength(0);
        this.setNumberOfProjections(0);
        setProjections(0);
        setNumberOfAttributes(0);
        setPositionOfAttributes(initializeTable());
        setNumberOfSelection(0);
        setPositionOfSelection(initializeTable());
        setNumberOfOrder(0);
        setPositionOfOrder(initializeTable());
        setNumberOfGroup(0);
        setPositionOfGroup(initializeTable());
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

    public int getProjections() {
        return projections;
    }

    public void setProjections(int projections) {
        this.projections = projections;
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public void setNumberOfAttributes(int numberOfAttributes) {
        this.numberOfAttributes = numberOfAttributes;
    }

    public int getNumberOfSelection() {
        return numberOfSelection;
    }

    public void setNumberOfSelection(int numberOfSelection) {
        this.numberOfSelection = numberOfSelection;
    }


    public int getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(int numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }


    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
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

    public int[] getPositionOfAttributes() {
        return positionOfAttributes;
    }

    public void setPositionOfAttributes(int[] positionOfAttributes) {
        this.positionOfAttributes = positionOfAttributes;
    }

    public int[] getPositionOfSelection() {
        return positionOfSelection;
    }

    public void setPositionOfSelection(int[] positionOfSelection) {
        this.positionOfSelection = positionOfSelection;
    }

    public int[] getPositionOfOrder() {
        return positionOfOrder;
    }

    public void setPositionOfOrder(int[] positionOfOrder) {
        this.positionOfOrder = positionOfOrder;
    }

    public int[] getPositionOfGroup() {
        return positionOfGroup;
    }

    public void setPositionOfGroup(int[] positionOfGroup) {
        this.positionOfGroup = positionOfGroup;
    }
}
