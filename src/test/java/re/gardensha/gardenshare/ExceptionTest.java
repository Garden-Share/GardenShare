package re.gardensha.gardenshare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExceptionTest {
	// Michael wrote this test :)
	
	@Test
	public void testListingEx() {
		InvalidListingException ex = new InvalidListingException("message");
		assertEquals("message", ex.getMessage());
		boolean caught = false;
		try {
			throwListingEx(ex);
		} catch (InvalidListingException e) {
			caught = true;
			assertEquals("message", e.getMessage());
		}
		assertTrue(caught);
	}
	
	private void throwListingEx(InvalidListingException ex) throws InvalidListingException {
		throw ex;
	}
	
	@Test
	public void testReviewEx() {
		InvalidReviewException ex = new InvalidReviewException("message");
		assertEquals("message", ex.getMessage());
		boolean caught = false;
		try {
			throwReviewEx(ex);
		} catch (InvalidReviewException e) {
			caught = true;
			assertEquals("message", e.getMessage());
		}
		assertTrue(caught);
	}
	
	private void throwReviewEx(InvalidReviewException ex) throws InvalidReviewException {
		throw ex;
	}

}
