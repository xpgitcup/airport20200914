package airport20200914

class BootStrap {

    def initService

    def init = { servletContext ->
        def webRootDir = servletContext.getRealPath("/")
        println("当前系统根路径：${webRootDir}")

        initService.setSampleData()
        initService.loadScripts("${webRootDir}sql")
    }

    def destroy = {
    }
}
