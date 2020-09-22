package cn.edu.cup.engineering.operation

import cn.edu.cup.engineering.BasicStructure
import cn.edu.cup.engineering.DataItem
import grails.converters.JSON

class EngineeringOperationController {

    def index(Integer max) {
        List list = countElementInfomation(max)
        if (request.xhr) {
            def result = [list: list, total: BasicStructure.countByChildrenIsEmpty()]
            render result as JSON
        } else {
            respond list, model: [testCount: list.size()]
        }
    }

    private List countElementInfomation(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = []
        def typeStringList = BasicStructure.findAllByChildrenIsEmpty(params)
        for (BasicStructure t : typeStringList) {
            def c = DataItem.countByKeyString(t.toString());
            def p = DataItem.findByKeyString(t.toString())
            def d = DataItem.countByParentItem(p)
            def item = ["项目": "${t}", "数量": c, "类别": "${t.parent}", "数据项": d]
            list.add(item)
        }
        list
    }

}
