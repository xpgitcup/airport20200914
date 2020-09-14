import cn.edu.cup.system.OnlineUserCountListener
import cn.edu.cup.system.UserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    onlineUserCountListener(OnlineUserCountListener)
    userPasswordEncoderListener(UserPasswordEncoderListener)
}
