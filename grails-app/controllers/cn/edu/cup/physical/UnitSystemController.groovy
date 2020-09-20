package cn.edu.cup.physical

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class UnitSystemController {

    UnitSystemService unitSystemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: unitSystemService.list(params), total: unitSystemService.count()]
            render result as JSON
        } else {
            respond unitSystemService.list(params), model:[unitSystemCount: unitSystemService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: unitSystemService.get(id)]
            render result as JSON
        } else {
            respond unitSystemService.get(id)
        }
    }

    def create() {
        respond new UnitSystem(params)
    }

    def save(UnitSystem unitSystem) {
        if (unitSystem == null) {
            notFound()
            return
        }

        try {
            unitSystemService.save(unitSystem)
        } catch (ValidationException e) {
            respond unitSystem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), unitSystem.id])
                redirect unitSystem
            }
            '*' { respond unitSystem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond unitSystemService.get(id)
    }

    def update(UnitSystem unitSystem) {
        if (unitSystem == null) {
            notFound()
            return
        }

        try {
            unitSystemService.save(unitSystem)
        } catch (ValidationException e) {
            respond unitSystem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), unitSystem.id])
                redirect unitSystem
            }
            '*'{ respond unitSystem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        unitSystemService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'unitSystem.label', default: 'UnitSystem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
