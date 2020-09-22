package cn.edu.cup.engineering

import com.alibaba.fastjson.annotation.JSONField

/**
 * 名字、类型、编号简化掉，只保留一个名字。
 * 复杂的编号等等用json格式表示
 * 后去的检索，考虑用like实现
 * ----------------------------------------------------------------------------
 * 名字--系统自动生成的
 * 类型--当地属于什么类型
 * 型号--设备往往都有型号
 * 编号--用户指定的
 */
class EngineeringElement{

    @JSONField(ordinal=0)
    String name
    @JSONField(ordinal=1)
    String appendName
    @JSONField(ordinal=2)
    BasicStructure basicStructure

    @JSONField(ordinal=5)
    SortedSet dataItems

    static hasMany = [dataItems: DataItem]

    static mapping = {}

    static constraints = {
        name(nullable: false)
        appendName(nullable: true)
        basicStructure(nullable: false)
    }

    String toString() {
        return "${name}"
    }

}
