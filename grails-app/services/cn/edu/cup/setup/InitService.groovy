package cn.edu.cup.setup

import cn.edu.cup.system.Requestmap
import cn.edu.cup.system.Role
import cn.edu.cup.system.User
import cn.edu.cup.system.UserRole
import grails.gorm.transactions.Transactional
import groovy.io.FileType
import groovy.json.JsonOutput
import org.grails.orm.hibernate.cfg.GrailsDomainBinder

import javax.xml.crypto.Data

@Transactional
class InitService {

    def commonService
    def dataSource
    def springSecurityService


    def setSampleData() {
        // 初始化的代码，用一次就不用了
//        commonService.exportObjectsToJsonFileName(defaultUrls, "config/defaultUrls.json")
//        commonService.exportObjectsToJsonFileName(userUrls, "config/userUrls.json")
        def defaultUrls = commonService.importObjectArrayFromJsonFileName("config/defaultUrls.json", String.class)
        println("缺省的：${defaultUrls}")
        createNewRequestmap(defaultUrls, 'permitAll')
        def userUrls = commonService.importObjectArrayFromJsonFileName("config/userUrls.json", String.class)
        createNewRequestmap(userUrls, 'ROLE_ADMIN')

        createUserAndRole()
    }

    private void createUserAndRole() {
        // 创建角色
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        // 创建用户
        def users = ['me', '李晓平', '李维嘉', '康琦', '吴海浩', '宫敬', '王雨墨',
                     '刘大千', '姬帅鹏', '吴佳虎', '任书健',
                     '刘胜男', '都兆楠', '谭遥', '崔雪萌',
                     '杨起', '焦玉博', '王延庆',
        ]
        users.each { it ->
            def testUser = new User(username: it, password: 'password').save()
            // 给用户赋予权限
            def userRole = UserRole.create(testUser, adminRole)
            UserRole.withSession {
                it.flush()
                it.clear()
            }
            userRole.save()
        }

    }


    def createNewRequestmap(urls, roleName) {
        for (String url in urls) {
            new Requestmap(url: url, configAttribute: roleName).save()
        }
        springSecurityService.clearCachedRequestmaps()
    }

    //加载数据库初始化脚本
    def loadScripts(String dir) {
        def File sf = new File(dir)
        println "load scripts ${dir}"
        if (sf.exists()) {
            if (sf) {
                sf.eachFileMatch(FileType.FILES, ~/.*\.sql/) { f ->
                    println("found a file ${f}...")
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            }
        }
    }

    //执行附加脚本
    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

}
