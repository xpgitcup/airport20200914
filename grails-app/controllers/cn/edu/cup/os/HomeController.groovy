package cn.edu.cup.os

import cn.edu.cup.system.CupToken
import grails.converters.JSON

class HomeController {

    def index() {
        def list = []
        CupToken.list().each { it ->
            def u = [username: it.username]
            list.add(u)
        }
        def ctx = request.session.servletContext
        List serviceUserList = (List) ctx.getAttribute("serviceUserList");
        def result = []
        if (serviceUserList) {
            println("ctx: ${serviceUserList}")
            result = [count: serviceUserList.size(), list: list]
        } else {
            result = [count: 0, list: list]
        }
        render result as JSON
    }
}
