package cn.edu.cup.physical

import grails.gorm.services.Service

@Service(UnitSystem)
interface UnitSystemService {

    UnitSystem get(Serializable id)

    List<UnitSystem> list(Map args)

    Long count()

    void delete(Serializable id)

    UnitSystem save(UnitSystem unitSystem)

}