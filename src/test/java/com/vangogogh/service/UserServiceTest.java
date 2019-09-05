package com.vangogogh.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vangogogh.model.User;
import com.vangogogh.repository.RoleRepository;
import com.vangogogh.repository.UserRepository;
import com.vangogogh.service.UserService;

public class UserServiceTest {

	@Mock
	private UserRepository mockUserRepository;
	@Mock
	private RoleRepository mockRoleRepository;
	@Mock
	private BCryptPasswordEncoder mockBCryptPasswordEncoder;

	private UserService userServiceUnderTest;
	private User user;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userServiceUnderTest = new UserService(mockUserRepository, mockRoleRepository, mockBCryptPasswordEncoder);
		user = User.builder().id(1L).name("Gustavo").lastName("Ponce").email("test@test.com").build();

		Mockito.when(mockUserRepository.save(Mockito.any())).thenReturn(user);
		Mockito.when(mockUserRepository.findByEmail(Mockito.anyString())).thenReturn(user);
	}

	@Test
	public void testFindUserByEmail() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		final User result = userServiceUnderTest.findUserByEmail(email);

		// Verify the results
		assertEquals(email, result.getEmail());
	}

	@Test
	public void testSaveUser() {
		// Setup
		final String email = "test@test.com";

		// Run the test
		User user = User.builder().email(email).build();
		User result = userServiceUnderTest.saveUser(user);

		// Verify the results
		assertEquals(email, result.getEmail());
	}
}
