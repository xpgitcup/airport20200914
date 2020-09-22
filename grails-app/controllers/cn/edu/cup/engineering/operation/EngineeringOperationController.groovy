package cn.edu.cup.engineering.operation

import cn.edu.cup.engineering.BasicStructure
import cn.edu.cup.engineering.DataItem
import cn.edu.cup.system.BasicStructureType
import grails.converters.JSON

import javax.print.attribute.standard.MediaSize

class EngineeringOperationController {

    def index(Integer max) {
        List list = countElementInfomation(max)
        if (request.xhr) {
            def result = [list: list, total: BasicStructure.countByBasicStructureType(BasicStructureType.ElementObject)]
            render result as JSON
        } else {
            respond list, model: [testCount: list.size()]
        }
    }

    private List countElementInfomation(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def list = []
        def typeStringList = BasicStructure.findAllByBasicStructureType(BasicStructureType.ElementObject, params)
        for (BasicStructure t : typeStringList) {
            def c = cn.edu.cup.engineering.EngineeringElement.countByBasicStructure(t);
            def item = ["实体类": "${t}", "数量": c]
            list.add(item)
        }
        list
    }

}
