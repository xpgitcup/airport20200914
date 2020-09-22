package cn.edu.cup.engineering

import cn.edu.cup.system.BasicStructureType
import cn.edu.cup.system.DataPropertyType
import com.alibaba.fastjson.annotation.JSONField

/**
 * 最最基础的数据结构设置
 *
 */
class BasicStructure implements Comparable<BasicStructure> {

    @JSONField(ordinal = 0)
    String name
    @JSONField(ordinal = 1)
    String auxname
    @JSONField(ordinal = 2)
    BasicStructureType basicStructureType
    @JSONField(ordinal = 3)
    DataPropertyType dataPropertyType
    @JSONField(ordinal = 4)
    BasicStructure parent

    SortedSet children

    static hasMany = [children: BasicStructure]

    static mapping = {
        sort "id"
    }

    static constraints = {
        name(nullable: false)
        auxname(nullable: true)
        basicStructureType(nullable: true)
        dataPropertyType(nullable: true)
        parent(nullable: true)
    }

    String toString() {
        if (parent) {
            return "${parent}.${name}"
        } else {
            return "${name}"
        }
    }

    @Override
    int compareTo(BasicStructure o) {
        return id.compareTo(o.id)
    }
}
