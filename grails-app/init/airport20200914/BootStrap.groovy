package airport20200914

import cn.edu.cup.system.OsInfo

class BootStrap {

    def initService

    def init = { servletContext ->
        println("当前操作系统：${OsInfo.getOSname()}")
        def webRootDir = servletContext.getRealPath("/")
        println("当前系统根路径：${webRootDir}")
        environments {
            development {
                initService.setSampleData()
                initService.loadScripts("${webRootDir}sql")
            }
        }
    }

    def destroy = {
    }
}
