package vending.machine.constants;

import lombok.Getter;

@Getter
public enum BillsValue {
    TEN(10), FIVE(5), ONE(1);

    private int value;

    BillsValue(int value) {
        this.value=value;
    }

}
