package cn.edu.cup.physical

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class QuantityUnitServiceSpec extends Specification {

    QuantityUnitService quantityUnitService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new QuantityUnit(...).save(flush: true, failOnError: true)
        //new QuantityUnit(...).save(flush: true, failOnError: true)
        //QuantityUnit quantityUnit = new QuantityUnit(...).save(flush: true, failOnError: true)
        //new QuantityUnit(...).save(flush: true, failOnError: true)
        //new QuantityUnit(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //quantityUnit.id
    }

    void "test get"() {
        setupData()

        expect:
        quantityUnitService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<QuantityUnit> quantityUnitList = quantityUnitService.list(max: 2, offset: 2)

        then:
        quantityUnitList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        quantityUnitService.count() == 5
    }

    void "test delete"() {
        Long quantityUnitId = setupData()

        expect:
        quantityUnitService.count() == 5

        when:
        quantityUnitService.delete(quantityUnitId)
        sessionFactory.currentSession.flush()

        then:
        quantityUnitService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        QuantityUnit quantityUnit = new QuantityUnit()
        quantityUnitService.save(quantityUnit)

        then:
        quantityUnit.id != null
    }
}
