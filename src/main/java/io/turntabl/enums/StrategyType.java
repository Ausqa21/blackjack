package io.turntabl.enums;

public enum StrategyType {


    DEFAULT("default"), ALWAYS_STICK("always-stick"), ALWAYS_HIT("always-hit"), RISK_CALCULATOR("risk-calculator");

    public final String strategyName;

    StrategyType(String strategyName) {
        this.strategyName = strategyName;
    }

    public static StrategyType fromString(String text) {
        for (StrategyType b : StrategyType.values()) {
            if (b.strategyName.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return StrategyType.DEFAULT;
    }

}
