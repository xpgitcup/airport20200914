package cn.edu.cup.os.operation

import cn.edu.cup.os.SystemLog
import cn.edu.cup.os.SystemLogController
import grails.converters.JSON

class SystemLogOperationController extends SystemLogController {

    def commonService

    def recordAction(String username, String doing) {
        println("日志：${params}")
        def sessionId = request.session.getId()
        def ip = commonService.getIpAddress(request)
        def actionDate = new Date()
        def systemLog = new SystemLog(
                sessionId: sessionId,
                userName: username,
                doing: doing,
                actionDate: actionDate,
                hostIP: ip
        )
        systemLogService.save(systemLog)
        return systemLog as JSON
    }
}
