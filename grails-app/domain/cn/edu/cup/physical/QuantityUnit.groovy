package cn.edu.cup.physical

class QuantityUnit implements Comparable<QuantityUnit> {

    //字段定义
    String unitName
    String englishName
    String symbol
    Double factorA = 1
    Double factorB = 0

    static belongsTo = [unitSystem: UnitSystem, physicalQuantity: PhysicalQuantity]

    static constraints = {
        unitName(unique: true);
        englishName(nullable: true)
        symbol()
        factorA();
        factorB();
    }

    String toString() {
        return "${symbol}"
    }

    @Override
    int compareTo(QuantityUnit o) {
        return symbol.compareTo(o.symbol)
    }
}
