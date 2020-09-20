package cn.edu.cup.physical

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class PhysicalQuantityController {

    PhysicalQuantityService physicalQuantityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: physicalQuantityService.list(params), total: physicalQuantityService.count()]
            render result as JSON
        } else {
            respond physicalQuantityService.list(params), model:[physicalQuantityCount: physicalQuantityService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: physicalQuantityService.get(id)]
            render result as JSON
        } else {
            respond physicalQuantityService.get(id)
        }
    }

    def create() {
        respond new PhysicalQuantity(params)
    }

    def save(PhysicalQuantity physicalQuantity) {
        if (physicalQuantity == null) {
            notFound()
            return
        }

        try {
            physicalQuantityService.save(physicalQuantity)
        } catch (ValidationException e) {
            respond physicalQuantity.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), physicalQuantity.id])
                redirect physicalQuantity
            }
            '*' { respond physicalQuantity, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond physicalQuantityService.get(id)
    }

    def update(PhysicalQuantity physicalQuantity) {
        if (physicalQuantity == null) {
            notFound()
            return
        }

        try {
            physicalQuantityService.save(physicalQuantity)
        } catch (ValidationException e) {
            respond physicalQuantity.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), physicalQuantity.id])
                redirect physicalQuantity
            }
            '*'{ respond physicalQuantity, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        physicalQuantityService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'physicalQuantity.label', default: 'PhysicalQuantity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
