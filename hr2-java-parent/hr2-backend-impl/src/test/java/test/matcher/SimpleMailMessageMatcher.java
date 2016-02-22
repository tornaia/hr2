package test.matcher;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsAnything;
import org.springframework.mail.SimpleMailMessage;

import test.matcher.AbstractTypeSafeDiagnosingMatcher;

public class SimpleMailMessageMatcher extends AbstractTypeSafeDiagnosingMatcher<SimpleMailMessage> {

	private Matcher<String[]> to = new IsAnything<>();
	
	private Matcher<String[]> cc = new IsAnything<>();
	
	private Matcher<String[]> bcc = new IsAnything<>();
	
	private Matcher<String> subject = new IsAnything<>();
	
	private Matcher<String> text = new IsAnything<>();

	public SimpleMailMessageMatcher to(String... to) {
		if (to.length == 0) {
			this.to = Matchers.arrayWithSize(0);
		} else {
			this.to = Matchers.arrayContaining(to);
		}
		return this;
	}
	
	public SimpleMailMessageMatcher cc(String... cc) {
		if (cc.length == 0) {
			this.cc = Matchers.arrayWithSize(0);
		} else {
			this.cc = Matchers.arrayContaining(cc);
		}
		return this;
	}
	
	public SimpleMailMessageMatcher bcc(String... bcc) {
		if (bcc.length == 0) {
			this.bcc = Matchers.arrayWithSize(0);
		} else {
			this.bcc = Matchers.arrayContaining(bcc);
		}
		return this;
	}
	
	public SimpleMailMessageMatcher subject(String subject) {
		this.subject = CoreMatchers.is(subject);
		return this;
	}
	
	public SimpleMailMessageMatcher text(String text) {
		this.text = CoreMatchers.is(text);
		return this;
	}
	
	@Override
	protected boolean matchesSafely(SimpleMailMessage item, Description mismatchDescription) {
		return matches(to, item.getTo(), "to value: ", mismatchDescription) &&
				matches(cc, item.getCc(), "cc value: ", mismatchDescription) &&
				matches(bcc, item.getBcc(), "bcc value: ", mismatchDescription) &&
				matches(subject, item.getSubject(), "subject value: ", mismatchDescription) &&
				matches(text, item.getText(), "text value: ", mismatchDescription);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(SimpleMailMessage.class.getSimpleName())
    	.appendText(", to: ").appendDescriptionOf(to)
    	.appendText(", cc: ").appendDescriptionOf(cc)
    	.appendText(", bcc: ").appendDescriptionOf(bcc)
    	.appendText(", subject: ").appendDescriptionOf(subject)
    	.appendText(", text: ").appendDescriptionOf(text);
	}
}
