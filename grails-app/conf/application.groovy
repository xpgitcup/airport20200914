

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'cn.edu.cup.system.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'cn.edu.cup.system.UserRole'
grails.plugin.springsecurity.authority.className = 'cn.edu.cup.system.Role'
grails.plugin.springsecurity.requestMap.className = 'cn.edu.cup.system.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'cn.edu.cup.system.CupToken'
// 永远记住我？
//grails.plugin.springsecurity.rememberMe.alwaysRemember = true	// 好像也不灵啊

// 设置令牌存储域类名称
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = "cn.edu.cup.system.CupToken"
grails.plugin.springsecurity.rest.token.storage.gorm.tokenValuePropertyName = "token"
grails.plugin.springsecurity.rest.token.storage.gorm.usernamePropertyName = "username"
