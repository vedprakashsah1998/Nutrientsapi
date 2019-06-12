package com.food.vpman.nutrientsapi;

public class NutrientsCategory
{
    String label,unit;
    String quantity;

    public NutrientsCategory(String label, String unit, String quantity) {
        this.label = label;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
