package cn.edu.cup.engineering

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BasicStructureServiceSpec extends Specification {

    BasicStructureService basicStructureService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new BasicStructure(...).save(flush: true, failOnError: true)
        //new BasicStructure(...).save(flush: true, failOnError: true)
        //BasicStructure basicStructure = new BasicStructure(...).save(flush: true, failOnError: true)
        //new BasicStructure(...).save(flush: true, failOnError: true)
        //new BasicStructure(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //basicStructure.id
    }

    void "test get"() {
        setupData()

        expect:
        basicStructureService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<BasicStructure> basicStructureList = basicStructureService.list(max: 2, offset: 2)

        then:
        basicStructureList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        basicStructureService.count() == 5
    }

    void "test delete"() {
        Long basicStructureId = setupData()

        expect:
        basicStructureService.count() == 5

        when:
        basicStructureService.delete(basicStructureId)
        sessionFactory.currentSession.flush()

        then:
        basicStructureService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        BasicStructure basicStructure = new BasicStructure()
        basicStructureService.save(basicStructure)

        then:
        basicStructure.id != null
    }
}
