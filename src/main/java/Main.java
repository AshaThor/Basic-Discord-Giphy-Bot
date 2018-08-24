import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends ListenerAdapter{
    Giphy gif = new Giphy();
    public static void main(String[] args) throws LoginException, IOException {
        //New instance of JDABuilder (Wrapper for Discord)
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        System.out.println("Getting Access Token");
        //File Reader to get a your app token
        BufferedReader tokenReader = new BufferedReader(new FileReader(".\\src\\main\\token.txt"));
        System.out.println("Access Token File found Aquired");
        //Reads file to obtain token
        String token = tokenReader.readLine();
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();

    }
    @Override
    public void onMessageReceived (MessageReceivedEvent event){
        if(event.getMessage().getContentRaw().contains("!gif")) {
            String plainText = event.getMessage().getContentDisplay();
            plainText = plainText.toLowerCase();
            String targetText = plainText.replace("!gif ","");
            try{
                event.getChannel().sendMessage("Here is your gif " +
                        gif.getGif(targetText)).queue();
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
