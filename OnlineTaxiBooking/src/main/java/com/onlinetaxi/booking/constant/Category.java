package com.onlinetaxi.booking.constant;

import com.onlinetaxi.booking.exception.TaxiBookingException;
import lombok.Getter;

@Getter
public enum Category {
    MINI("mini"), SEDAN("sedan"), SUV("suv"), SHARE("share"), OUTSTATION("outstation");

    private String categoryType;

    Category(String categoryType) {
        this.categoryType = categoryType;
    }

    public Category getCategoryByValue(final String categoryType) throws TaxiBookingException {


        for (Category category : Category.values()) {
            if (category.getCategoryType().equalsIgnoreCase(categoryType)) {
                return category;
            }
        }

        throw new TaxiBookingException("Invalid Category");
    }
}
