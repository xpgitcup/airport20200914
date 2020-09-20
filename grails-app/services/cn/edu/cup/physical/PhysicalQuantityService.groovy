package cn.edu.cup.physical

import grails.gorm.services.Service

@Service(PhysicalQuantity)
interface PhysicalQuantityService {

    PhysicalQuantity get(Serializable id)

    List<PhysicalQuantity> list(Map args)

    Long count()

    void delete(Serializable id)

    PhysicalQuantity save(PhysicalQuantity physicalQuantity)

}