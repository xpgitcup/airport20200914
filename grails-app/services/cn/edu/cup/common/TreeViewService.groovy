package cn.edu.cup.common

import cn.edu.cup.system.UIFrame
import grails.gorm.transactions.Transactional

@Transactional
class TreeViewService extends GenericService {
    /*
    * 2016.12.17
    * 创建对象列表的树形显示字符串，基于EasyUI
    * 这是调用的入口
    * */

    def createTreeViewString(objects, params, UIFrame jsFrame) {

        def result = []

        //先检查参数设置
        //result.add(paramsCheckA("eid", params))
        result.add(paramsCheck("context", params))
        result.add(paramsCheck("subItems", params))

        if (result.isEmpty()) {
            result.add(generateNodesString(objects, params, jsFrame))
        }

        return result
    }

    /*
    * 2016.12.17
    * 生成节点的Map
    * */

    def generateNodesString(objects, params, UIFrame jsFrame) {
        def result = []

        def itemText = params.context
        def itemSubs = params.subItems
        def useMethod = params.useMethod
        def itemAttributes = analysisAttributes(params)
        println("属性：${itemAttributes}")

        objects.each() { e ->
            def emap = [:]
            def tempText
            if (!useMethod) {
                tempText = e.metaClass.getProperty(e, "${itemText}")
            } else {
                tempText = e.metaClass.invokeMethod(e, "${itemText}")
                //println "使用方法....${tempText}"
            }
            //emap.put("nodeId", e.id)  //这个没有用，系统会自己定义节点编号
            switch (jsFrame) {
                case UIFrame.EasyUI:
                case UIFrame.BootStrap:
                    emap.put("text", tempText)  //
                    break
                case UIFrame.BaiduECharts:
                case UIFrame.zTree:
                    emap.put("name", tempText)  //
                    break
                case UIFrame.ElementUI:
                    emap.put("id", e.metaClass.getProperty(e, "id"))  //
                    emap.put("name", tempText)  //
                    break
            }
            //处理属性
            if (itemAttributes.size() > 0) {
                def attrs = []
                itemAttributes.each { it ->
                    attrs.add(e.metaClass.getProperty(e, it))
                }
                println("附加属性：${attrs}")
                //根据框架选择不同的字符串
                switch (jsFrame) {
                    case UIFrame.EasyUI:
                        emap.put("attributes", attrs)
                        break
                    case UIFrame.BootStrap:
                        emap.put("tags", attrs)
                        break
                    case UIFrame.BaiduECharts:
                        emap.put("value", attrs)
                        break
                    case UIFrame.zTree: // zTree
                        emap.put("value", attrs)
                        break
                    case UIFrame.ElementUI:
                        emap.put("attributes", attrs)
                        break
                }
            }
            //处理下一级
            def subItems = e.metaClass.getProperty(e, "${itemSubs}")
            if (subItems) {
                //println "获取下一级 ${subItems}"
                def childrenmap = generateNodesString(subItems, params, jsFrame)
                //根据框架选择不同的字符串
                switch (jsFrame) {
                    case UIFrame.EasyUI:
                    case UIFrame.BaiduECharts:
                    case UIFrame.zTree:
                    case UIFrame.ElementUI:
                        emap.put("children", childrenmap) //对于EasyUI使用children
                        break
                    case UIFrame.BootStrap:
                        emap.put("nodes", childrenmap)      //对于bootstrap-treeview使用nodes
                        break
                }
            }
            //处理完毕
            //println(emap)
            result.add(emap)
        }
        return result       //这一句很重要，不然就栈溢出了
    }

    def analysisAttributes(params) {
        def res = []

        if (params.attributes) {
            res = params.attributes.split(',')
        }
        return res
    }

}
