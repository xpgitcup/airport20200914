package cn.edu.cup.os

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class SystemLogController {

    SystemLogService systemLogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: systemLogService.list(params), total: systemLogService.count()]
            render result as JSON
        } else {
            respond systemLogService.list(params), model:[systemLogCount: systemLogService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: systemLogService.get(id)]
            render result as JSON
        } else {
            respond systemLogService.get(id)
        }
    }

    def create() {
        respond new SystemLog(params)
    }

    def save(SystemLog systemLog) {
        if (systemLog == null) {
            notFound()
            return
        }

        try {
            systemLogService.save(systemLog)
        } catch (ValidationException e) {
            respond systemLog.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*' { respond systemLog, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemLogService.get(id)
    }

    def update(SystemLog systemLog) {
        if (systemLog == null) {
            notFound()
            return
        }

        try {
            systemLogService.save(systemLog)
        } catch (ValidationException e) {
            respond systemLog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*'{ respond systemLog, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemLogService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
