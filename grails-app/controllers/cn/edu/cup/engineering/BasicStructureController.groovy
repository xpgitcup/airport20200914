package cn.edu.cup.engineering

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class BasicStructureController {

    BasicStructureService basicStructureService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: basicStructureService.list(params), total: basicStructureService.count()]
            render result as JSON
        } else {
            respond basicStructureService.list(params), model:[basicStructureCount: basicStructureService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: basicStructureService.get(id)]
            render result as JSON
        } else {
            respond basicStructureService.get(id)
        }
    }

    def create() {
        respond new BasicStructure(params)
    }

    def save(BasicStructure basicStructure) {
        if (basicStructure == null) {
            notFound()
            return
        }

        try {
            basicStructureService.save(basicStructure)
        } catch (ValidationException e) {
            respond basicStructure.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'basicStructure.label', default: 'BasicStructure'), basicStructure.id])
                redirect basicStructure
            }
            '*' { respond basicStructure, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond basicStructureService.get(id)
    }

    def update(BasicStructure basicStructure) {
        if (basicStructure == null) {
            notFound()
            return
        }

        try {
            basicStructureService.save(basicStructure)
        } catch (ValidationException e) {
            respond basicStructure.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'basicStructure.label', default: 'BasicStructure'), basicStructure.id])
                redirect basicStructure
            }
            '*'{ respond basicStructure, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        basicStructureService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'basicStructure.label', default: 'BasicStructure'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'basicStructure.label', default: 'BasicStructure'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'basicStructure.label', default: 'BasicStructure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
