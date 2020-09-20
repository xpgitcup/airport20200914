package cn.edu.cup.physical

/***
 * 物理量
 * */
class PhysicalQuantity {

    //字段定义
    String quantityName // 名字
    String englishName  // 英文名字
    String symbol       // 英文符号
    String unitNameISO  // 国际单位
    String unitSymbolISO    // 国际单位符号

    SortedSet quantityUnits

    static hasMany = [quantityUnits: QuantityUnit]

    static constraints = {
        quantityName(unique: true)
        englishName(unique: true)
        symbol(unique: true)
        unitNameISO(unique: true)
        unitSymbolISO(unique: true)
    }

    String toString() {
        return "${quantityName}/(${unitSymbolISO})";
    }

}
