package tech.mctown.redstone;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.literal;

public class TimeCommand implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	private static int run(CommandContext<ServerCommandSource> context) {
		{
			int time=executeQuery(context.getSource(), (int)(context.getSource().getWorld().getTime() % 2147483647L));
			String text = "§a===============================\n"+
						  "§a         MCTS帮助页面      \n"+
						  "§a          ==建设中==     \n"+
						  "§a   服务端已运行§l§d" + time/20/3600/24 + "§a天§l§d"+ time/1200%60 + "§a时§l§d" + time/20%60 + "§a分\n" +
						  "§a===============================";
			context.getSource().sendFeedback(Text.of(text), false);
		}
		return 1;
	}
	private static int executeQuery(ServerCommandSource source, int time) {
		return time;
	}
	//获取时间

	@Override
	public void onInitialize() {
		// 这行代码在MC加载完成后运行
		// However, some things (like resources) may still be uninitialized.
		//注册指令
		LOGGER.info("Hello Fabric world!");
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(literal("mct") //待选文本
					 .executes(TimeCommand::run)
			);
		});
	}
}