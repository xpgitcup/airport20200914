package cn.edu.cup.common

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter
import grails.gorm.transactions.Transactional

@Transactional
class CommonService {

    def grailsApplication
    def webRootPath = ""
    def dataRootPath

    def getWebRootPath() {
        webRootPath = grailsApplication.getMainContext().servletContext.getRealPath("/")
        return webRootPath
    }

    /*
    从文件中导入对象
    * */

    def synchronized importObjects(captionsFileName, Class clazz, selfCheckMethod) {
        def objects = importObjectArrayFromJsonFileName(captionsFileName, clazz)
        objects.each { e ->
            if (selfCheckMethod) {
                e.metaClass.invokeMethod(e, selfCheckMethod)
            }
            e.save(flush: true)
        }
        return objects
    }

    /*
    对象列表导出到文件（名）
    * */

    void exportObjectsToJsonFileName(objects, String fileName) {
        def realFileName
        realFileName = "${getWebRootPath()}/${fileName}"
        println("文件名：${realFileName}")
        def jsonFile = new File(realFileName)
        if (!jsonFile.exists()) {
            jsonFile.createNewFile()
        }
        exportObjectsToJsonFile(objects, jsonFile)
    }

    void exportObjectToJsonFile(object, File jsonFile) {
        // id字段也应该忽略
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter()
        filter.getExcludes().add("id");

        // 忽略非用户定义的属性
        def jsonString = JSON.toJSONString(object,
                filter,
                SerializerFeature.PrettyFormat,
                SerializerFeature.IgnoreNonFieldGetter
        )
        def printWriter = new PrintWriter(jsonFile, "utf-8")
        printWriter.write(jsonString)
        printWriter.close()
    }

    void exportObjectsToJsonFile(objects, File jsonFile) {
        if (objects?.size() < 1) {
            println("数组是空的！")
            return
        }

        // id字段也应该忽略
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter()
        filter.getExcludes().add("id");

        // 忽略非用户定义的属性
        def jsonString = com.alibaba.fastjson.JSON.toJSONString(objects,
                filter,
                SerializerFeature.PrettyFormat,
                SerializerFeature.IgnoreNonFieldGetter
        )
        def printWriter = new PrintWriter(jsonFile, "utf-8")
        printWriter.write(jsonString)
        printWriter.close()
    }

    /*
    从json文件中导入对象，文件如果不存在，创建文件
    * */

    def importObjectFromJsonFileName(String jsonFileName, Class clazz) {
        def realFileName = "${getWebRootPath()}/${jsonFileName}"
        def jsonFile = new File(realFileName)
        return importObjectFromJsonFile(jsonFile, clazz)
    }

    /*
    从json文件中导入对象，文件如果不存在，创建文件
    * */

    def importObjectFromJsonFile(File jsonFile, Class clazz) {
        if (jsonFile.exists()) {
            def jsonString = jsonFile.text
            return importObjectFromJson(jsonString, clazz)
        } else {
            def printWriter = new PrintWriter(jsonFile, "utf-8")
            def string = com.alibaba.fastjson.JSON.toJSONString(clazz.newInstance())
            printWriter.write(string)
            printWriter.close()
            return null
        }
    }

    /*
    * 从json字符串中导入对象
    * */

    def importObjectFromJson(String jsonString, Class clazz) {
//        println("json: ${jsonString}")
        def object = com.alibaba.fastjson.JSON.parseObject(jsonString, clazz)
        return object
    }

    /*
    从json文件中导入对象数组，文件如果不存在，创建文件
    * */

    def importObjectArrayFromJsonFileName(String jsonFileName, Class clazz) {
        def realFileName = "${getWebRootPath()}/${jsonFileName}"
        def jsonFile = new File(realFileName)
        return importObjectArrayFromJsonFile(jsonFile, clazz)
    }

    /*
    从json文件中导入对象，文件如果不存在，创建文件
    * */

    def importObjectArrayFromJsonFile(File jsonFile, Class clazz) {
        if (jsonFile.exists()) {
            def jsonString = jsonFile.text
            println("准备导入：${jsonString}")
            return importObjectArrayFromJson(jsonString, clazz)
        } else {
            def printWriter = new PrintWriter(jsonFile, "utf-8")
            def array = []
            array.add(clazz.newInstance())
            def string = com.alibaba.fastjson.JSON.toJSONString(array)
            printWriter.write(string)
            printWriter.close()
            return null
        }
    }

    /*
    * 从json字符串中导入对象
    * */

    def importObjectArrayFromJson(String jsonString, Class clazz) {
        def objectArray = com.alibaba.fastjson.JSON.parseArray(jsonString, clazz)
        //println("导入的：${objectArray}")
        return objectArray
    }

    /*
    * 将对象列表导出到json字符串中
    * */

    def exportObjects2JsonString(ObjectList) {
        def tempList = []
        ObjectList.each { e ->
            def q = [:]
            e.properties.each { ee ->
                q.put(ee.key, ee.value)
            }
            tempList.add(q)
        }

        def fjson = com.alibaba.fastjson.JSON.toJSONString(tempList)
        return fjson
    }

    def applicationName() {
        println("${grailsApplication}")
        return grailsApplication.metadata.getApplicationName()
    }

    /*
    增加双引号
    * */

    List getQuotationList(list) {
        def tmp = []
        list.each { e ->
            tmp.add("\'${e}\'")
        }
        tmp
    }

    /*
    * 数据导出服务：
    * 将对象导出到Map
    * */

    def exportObjectList2DataTable(Object object) {
        def head = []
        def data = []
        if (object instanceof List) {
            println("${object} 是List类型。")
            if (object.size() > 0) {
                object[0].properties.each { e ->
                    head.add(e.key)
                }
                object.each { e ->
                    def row = []
                    e.properties.each { ee ->
                        row.add(ee.value)
                    }
                    data.add(row)
                }
            }
        } else {
            println("${object}不是List")
            object.properties.each { e ->
                head.add(e.key)
                data.add(e.value)
            }
        }
        def model = [head: head, data: data]
        return model
    }

    /*
    * 上传文件
    * */

    File upload(params) {
        println("service: ${params}")
        println("service: ${params.uploadedFile}")
        println("service: ${params.uploadedFile.originalFilename}")
        if (params.uploadedFile && params.destDir) {
            def uploadedFile = params.uploadedFile
            def destDir = params.destDir
            def userDir = new File(destDir)
            if (!userDir.exists()) {
                userDir.mkdirs()
            }
            def destFile = new File(userDir, uploadedFile.originalFilename)
            uploadedFile.transferTo(destFile)
            println "upload ${destFile}"
            return destFile
        }
    }

    /*
    * 下载文件
    * */

    def downLoadFile(params) {
        def hasError = []
        if (params.downLoadFileName) {
            def filename = params.downLoadFileName
            def sf = new File(filename)
            println "download: ${sf} -- ${filename}"
            if (sf.exists()) {
                println "begin download......"
                def fName = sf.getName()
                // 处理中文乱码
                def name = URLEncoder.encode(fName, "UTF-8");
                def response = getResponse()
                response.setHeader("Content-disposition", "attachment; filename=" + name)
                response.contentType = "application/x-rarx-rar-compressed"
                //response.contentType = ""

                def out = response.outputStream
                def inputStream = new FileInputStream(sf)
                byte[] buffer = new byte[1024]
                int i = -1
                while ((i = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, i)
                }
                out.flush()
                out.close()
                inputStream.close()
            } else {
                hasError.add("文件不存在.")
            }
        } else {
            hasError.add("缺少downLoadFileName参数.")
        }
    }

    //Getting the Request object
    def getRequest() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentRequest()
    }

    //Getting the Response object
    def getResponse() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentResponse()
    }

    //Getting the ServletContext object
    def getServletContext() {
        //def webUtils = WebUtils.retrieveGrailsWebRequest()
        //webUtils.getServletContext()
        return grailsApplication.getMainContext().servletContext
    }

    //获取当前程序名称
    def getApplicationName() {
        return grails.util.Metadata.current.'app.name'
    }

    /*
    * 根据环境检查文件路径
    * */

    def checkFilePath4Enviroment(pathString) {
        def result
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                result = pathString
                break
            case Environment.PRODUCTION:
                result = "${webRootPath}/WEB-INF/${pathString}"
                break
        }
        return result
    }

}
