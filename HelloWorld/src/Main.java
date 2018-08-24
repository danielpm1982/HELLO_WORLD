import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
public class Main {
	public static void main(String[] args) {
		String dateTimeString = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
		dateTimeString=dateTimeString.substring(0, 1).toUpperCase()+dateTimeString.substring(1);
		System.out.println(dateTimeString+"\n");
		System.out.println("System Properties:\n");
		System.getProperties().forEach((x,y)->System.out.println(x+" "+y));
	}
}
