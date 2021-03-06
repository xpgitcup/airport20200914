package cn.edu.cup.engineering

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

class DataItemController {

    DataItemService dataItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        if (request.xhr) {
            def result = [list: dataItemService.list(params), total: dataItemService.count()]
            render result as JSON
        } else {
            respond dataItemService.list(params), model:[dataItemCount: dataItemService.count()]
        }
    }

    def show(Long id) {
        if (request.xhr) {
            def result = [item: dataItemService.get(id)]
            render result as JSON
        } else {
            respond dataItemService.get(id)
        }
    }

    def create() {
        respond new DataItem(params)
    }

    def save(DataItem dataItem) {
        if (dataItem == null) {
            notFound()
            return
        }

        try {
            dataItemService.save(dataItem)
        } catch (ValidationException e) {
            respond dataItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
                redirect dataItem
            }
            '*' { respond dataItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataItemService.get(id)
    }

    def update(DataItem dataItem) {
        if (dataItem == null) {
            notFound()
            return
        }

        try {
            dataItemService.save(dataItem)
        } catch (ValidationException e) {
            respond dataItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
                redirect dataItem
            }
            '*'{ respond dataItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataItemService.delete(id)

        if (request.xhr) {
            def result = [message: message(code: 'default.deleted.message', args: [message(code: 'dataItem.label', default: 'DataItem'), id])]
            render result as JSON
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataItem.label', default: 'DataItem'), id])
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dataItem.label', default: 'DataItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
