package cn.edu.cup.engineering

import grails.gorm.services.Service

@Service(EngineeringElement)
interface EngineeringElementService {

    EngineeringElement get(Serializable id)

    List<EngineeringElement> list(Map args)

    Long count()

    void delete(Serializable id)

    EngineeringElement save(EngineeringElement engineeringElement)

}