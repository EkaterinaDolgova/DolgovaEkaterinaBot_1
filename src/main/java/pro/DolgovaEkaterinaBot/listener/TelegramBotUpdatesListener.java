package pro.DolgovaEkaterinaBot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.DolgovaEkaterinaBot.model.Notification;
import pro.DolgovaEkaterinaBot.service.NotificationService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationService notificationService;

    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            Pattern pattern = Pattern.compile("([0-9.:\\s]{16})(\\s)([\\W+]+)");
            Matcher matcher = pattern.matcher(update.message().text());
            long chatId = update.message().chat().id();
            if (matcher.matches()) {
                String datetime = matcher.group(1);
                System.out.println(datetime);
                String textY = matcher.group(3);
                System.out.println(textY);
                LocalDateTime dateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                Notification notification = new Notification(chatId, textY, dateTime);
                System.out.println(dateTime);
                System.out.println(notification);
                notificationService.creatNotification(notification);
            }

            if (update.message().text().equals("/start")) {
                SendResponse response = telegramBot.execute(new SendMessage(chatId, "Привет! Я бот!"));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        notificationService.findByDatetime().stream().map(n->new SendMessage(n.getId_chat(),n.getTextY())).collect(Collectors.toList());
    }

}
