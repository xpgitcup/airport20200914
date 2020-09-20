package cn.edu.cup.engineering

import cn.edu.cup.system.ElementType
import com.alibaba.fastjson.annotation.JSONField

class DataItem implements Comparable<DataItem> {

    @JSONField(ordinal = 0)
    String keyString

    @JSONField(ordinal = 1)
    String appendString

    @JSONField(ordinal = 2)
    String valueString

    @JSONField(ordinal = 3)
    ElementType elementType = ElementType.scalar

    @JSONField(ordinal = 4)
    DataItem parentItem

    @JSONField(ordinal = 5)
    int orderCode = 0

    @JSONField(ordinal = 6, name = "dataItems")
    static hasMany = [dataItems: DataItem]

    DataItem() {
        keyString = "demo标题"
        appendString = "demo单位"
        valueString = "demo数值"
        parentItem = null
        elementType = ElementType.object
    }

    static mapping = {
    }

    static constraints = {
        keyString(nullable: false)
        appendString(nullable: true)
        valueString(nullable: true)
        parentItem(nullable: true)
        elementType()
        orderCode()
    }

    String toString() {
        if (valueString) {
            return "${keyString}=${valueString}(${appendString})"
        } else {
            return "${keyString}"
        }
    }

    @Override
    int compareTo(DataItem o) {
        return orderCode.compareTo(o.orderCode)
    }
}
