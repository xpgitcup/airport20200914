package cn.edu.cup.engineering

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EngineeringElementServiceSpec extends Specification {

    EngineeringElementService engineeringElementService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new EngineeringElement(...).save(flush: true, failOnError: true)
        //new EngineeringElement(...).save(flush: true, failOnError: true)
        //EngineeringElement engineeringElement = new EngineeringElement(...).save(flush: true, failOnError: true)
        //new EngineeringElement(...).save(flush: true, failOnError: true)
        //new EngineeringElement(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //engineeringElement.id
    }

    void "test get"() {
        setupData()

        expect:
        engineeringElementService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<EngineeringElement> engineeringElementList = engineeringElementService.list(max: 2, offset: 2)

        then:
        engineeringElementList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        engineeringElementService.count() == 5
    }

    void "test delete"() {
        Long engineeringElementId = setupData()

        expect:
        engineeringElementService.count() == 5

        when:
        engineeringElementService.delete(engineeringElementId)
        sessionFactory.currentSession.flush()

        then:
        engineeringElementService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        EngineeringElement engineeringElement = new EngineeringElement()
        engineeringElementService.save(engineeringElement)

        then:
        engineeringElement.id != null
    }
}
