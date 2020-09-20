package cn.edu.cup.physical

import grails.gorm.services.Service

@Service(QuantityUnit)
interface QuantityUnitService {

    QuantityUnit get(Serializable id)

    List<QuantityUnit> list(Map args)

    Long count()

    void delete(Serializable id)

    QuantityUnit save(QuantityUnit quantityUnit)

}