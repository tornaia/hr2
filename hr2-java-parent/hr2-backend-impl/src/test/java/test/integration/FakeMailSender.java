package test.integration;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class FakeMailSender implements MailSender {

	private List<SimpleMailMessage> elkuldottLevelek = newArrayList();
	
	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		elkuldottLevelek.add(simpleMessage);
	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		elkuldottLevelek.addAll(asList(simpleMessages));
	}
	
	public List<SimpleMailMessage> getElkuldottLevelek() {
		return elkuldottLevelek;
	}

	public void clear() {
		elkuldottLevelek.clear();
	}
}
