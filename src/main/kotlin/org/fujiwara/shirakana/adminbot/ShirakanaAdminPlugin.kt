package org.fujiwara.shirakana.adminbot

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.event.*
import net.mamoe.mirai.utils.*
import org.fujiwara.shirakana.adminbot.command.*
import org.fujiwara.shirakana.adminbot.configAndData.*
import java.util.concurrent.Executors


object ShirakanaAdminBot : KotlinPlugin(
    JvmPluginDescription(
        id = "org.fujiwara.shirakana.adminbot.plugin",
        name = "Shirakana_Admin_Bot",
        version = "1.0.0",
    ) {
        author("FujiwaraShirakana")
        info("""一个主要用于粉丝群管理的插件，设计该插件的导火索是两个粉丝群炸掉了\n对此需要使用机器人大清洗以及管理粉丝群。\n主要功能 \n1、重复加群检测（重复加群者可手动添加标记防止其加入另一个群聊或者将其从其他的群聊中踢出）\n2、宵禁。\n3、指定特别监控。\n4、管理偏执度系统（钢丝的ZZ偏执度），偏执度25一次小清洗，100一次大清洗。""")
    }
) {



    override fun onEnable() {
        ShirakanaBigCleanSetting.reload()
        ShirakanaDataGroupMember.reload()
        ShirakanaAdministratorList.reload()
        ShirakanaDataFlags.reload()
        ShirakanaEventListener.registerTo(globalEventChannel())
        ShirakanaParanoia.register()
        AddRemTarget.register()
        ShirakanaSelectGroups.register()
        ShirakanaXianZhongRen.register()
        QuickCleanRepeatTarget.register()
        QBAMember.register()

        logger.info { "Plugin “大清洗” loaded" }
    }

    override fun onDisable() {

        AddRemTarget.unregister()
        ShirakanaSelectGroups.unregister()
        ShirakanaParanoia.unregister()
        ShirakanaXianZhongRen.unregister()
        QuickCleanRepeatTarget.register()
        QBAMember.unregister()

    }

}

