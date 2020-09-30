package cn.edu.cup.os

import grails.gorm.services.Service

@Service(SystemLog)
interface SystemLogService {

    SystemLog get(Serializable id)

    List<SystemLog> list(Map args)

    Long count()

    void delete(Serializable id)

    SystemLog save(SystemLog systemLog)

}