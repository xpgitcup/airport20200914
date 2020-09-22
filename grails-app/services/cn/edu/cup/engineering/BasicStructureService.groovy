package cn.edu.cup.engineering

import grails.gorm.services.Service

@Service(BasicStructure)
interface BasicStructureService {

    BasicStructure get(Serializable id)

    List<BasicStructure> list(Map args)

    Long count()

    void delete(Serializable id)

    BasicStructure save(BasicStructure basicStructure)

}