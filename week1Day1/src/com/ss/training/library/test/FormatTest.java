package com.ss.training.library.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.ss.training.library.UserInterface;


public class FormatTest {
	
	/**
	 * Test the selectedType set and get
	 */
	@Test
	public void gettingSelectedType() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(1);
		assertEquals(userInterface.getSelectedType(), 1);
	}
	
	@Test
	public void settingSelectedType() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(2);
		assertEquals(userInterface.getSelectedType(), 2);
	}

	/**
	 * Test the selectedAction get and set
	 */
	@Test
	public void settingSelectedAction() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedAction(2);
		assertEquals(userInterface.getSelectedAction(), 2);
	}

	@Test
	public void gettingSelectedAction() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedAction(3);
		assertEquals(userInterface.getSelectedAction(), 3);
	}
	
	/**
	 * Test typeToString()
	 */
	@Test
	public void authorTypeToString() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(1);
		assertEquals(userInterface.typeToString(), "Author");
	}
	@Test
	public void publisherTypeToString() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(2);
		assertEquals(userInterface.typeToString(), "Publisher");
	}
	@Test
	public void bookTypeToString() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(3);
		assertEquals(userInterface.typeToString(), "Book");
	}
	@Test
	public void notVaildTypeToString() {
		UserInterface userInterface = new UserInterface();
		userInterface.setSelectedType(10);
		assertEquals(userInterface.typeToString(), "Error");
	}
}
