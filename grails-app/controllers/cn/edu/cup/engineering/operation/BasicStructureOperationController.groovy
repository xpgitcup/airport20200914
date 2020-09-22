package cn.edu.cup.engineering.operation

import cn.edu.cup.engineering.BasicStructure
import cn.edu.cup.engineering.BasicStructureController
import cn.edu.cup.system.UIFrame
import grails.converters.JSON

class BasicStructureOperationController extends BasicStructureController {

    def treeViewService

    def index4tree(Integer max) {
        println("index4tree--${params}")
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def list = BasicStructure.findAllByParentIsNull(params)
            params.context = "name"
            params.subItems = "children"
            params.attributes = "basicStructureType,dataPropertyType"    //
            def treeData = treeViewService.generateNodesString(list, params, UIFrame.ElementUI)
            println("${treeData}")
            def result = [list: treeData, count: BasicStructure.countByParentIsNull()]
            println("${result}")
            render(result as JSON)
        } else {
            respond basicStructureService.list(params), model: [basicStructureCount: basicStructureService.count()]
        }
    }

}
