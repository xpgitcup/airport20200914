package cn.edu.cup.physical

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class QuantityUnitController {

    QuantityUnitService quantityUnitService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: quantityUnitService.list(params), total: quantityUnitService.count()]
            render result as JSON
        } else {
            respond quantityUnitService.list(params), model:[quantityUnitCount: quantityUnitService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: quantityUnitService.get(id)]
            render result as JSON
        } else {
            respond quantityUnitService.get(id)
        }
    }

    def create() {
        respond new QuantityUnit(params)
    }

    def save(QuantityUnit quantityUnit) {
        if (quantityUnit == null) {
            notFound()
            return
        }

        try {
            quantityUnitService.save(quantityUnit)
        } catch (ValidationException e) {
            respond quantityUnit.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), quantityUnit.id])
                redirect quantityUnit
            }
            '*' { respond quantityUnit, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond quantityUnitService.get(id)
    }

    def update(QuantityUnit quantityUnit) {
        if (quantityUnit == null) {
            notFound()
            return
        }

        try {
            quantityUnitService.save(quantityUnit)
        } catch (ValidationException e) {
            respond quantityUnit.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), quantityUnit.id])
                redirect quantityUnit
            }
            '*'{ respond quantityUnit, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        quantityUnitService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quantityUnit.label', default: 'QuantityUnit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
