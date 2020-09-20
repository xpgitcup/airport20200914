package cn.edu.cup.engineering

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class EngineeringElementController {

    EngineeringElementService engineeringElementService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: engineeringElementService.list(params), total: engineeringElementService.count()]
            render result as JSON
        } else {
            respond engineeringElementService.list(params), model:[engineeringElementCount: engineeringElementService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: engineeringElementService.get(id)]
            render result as JSON
        } else {
            respond engineeringElementService.get(id)
        }
    }

    def create() {
        respond new EngineeringElement(params)
    }

    def save(EngineeringElement engineeringElement) {
        if (engineeringElement == null) {
            notFound()
            return
        }

        try {
            engineeringElementService.save(engineeringElement)
        } catch (ValidationException e) {
            respond engineeringElement.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'engineeringElement.label', default: 'EngineeringElement'), engineeringElement.id])
                redirect engineeringElement
            }
            '*' { respond engineeringElement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond engineeringElementService.get(id)
    }

    def update(EngineeringElement engineeringElement) {
        if (engineeringElement == null) {
            notFound()
            return
        }

        try {
            engineeringElementService.save(engineeringElement)
        } catch (ValidationException e) {
            respond engineeringElement.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'engineeringElement.label', default: 'EngineeringElement'), engineeringElement.id])
                redirect engineeringElement
            }
            '*'{ respond engineeringElement, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        engineeringElementService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'engineeringElement.label', default: 'EngineeringElement'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'engineeringElement.label', default: 'EngineeringElement'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'engineeringElement.label', default: 'EngineeringElement'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
