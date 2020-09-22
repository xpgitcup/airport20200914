package cn.edu.cup.physical

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UnitSystemServiceSpec extends Specification {

    UnitSystemService unitSystemService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UnitSystem(...).save(flush: true, failOnError: true)
        //new UnitSystem(...).save(flush: true, failOnError: true)
        //UnitSystem unitSystem = new UnitSystem(...).save(flush: true, failOnError: true)
        //new UnitSystem(...).save(flush: true, failOnError: true)
        //new UnitSystem(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //unitSystem.id
    }

    void "test get"() {
        setupData()

        expect:
        unitSystemService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UnitSystem> unitSystemList = unitSystemService.list(max: 2, offset: 2)

        then:
        unitSystemList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        unitSystemService.count() == 5
    }

    void "test delete"() {
        Long unitSystemId = setupData()

        expect:
        unitSystemService.count() == 5

        when:
        unitSystemService.delete(unitSystemId)
        sessionFactory.currentSession.flush()

        then:
        unitSystemService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UnitSystem unitSystem = new UnitSystem()
        unitSystemService.save(unitSystem)

        then:
        unitSystem.id != null
    }
}
