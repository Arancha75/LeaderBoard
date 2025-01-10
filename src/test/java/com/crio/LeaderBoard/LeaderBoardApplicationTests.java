package com.crio.LeaderBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crio.LeaderBoard.Entity.User;
import com.crio.LeaderBoard.Repository.UserRepository;
import com.crio.LeaderBoard.ServiceImpl.LeaderboardServiceImpl;

@SpringBootTest
class LeaderBoardApplicationTests {

	@Mock
	private UserRepository userRepository;

	@Autowired
    private LeaderboardServiceImpl userService;

	@Test
	void User_Success() {
		User user = new User();
		user.setUserId("1");
		user.setUsername("John_Doe");

		when(userRepository.existsById("1")).thenReturn(false);
		when(userRepository.save(user)).thenReturn(user);


		User result = userService.addUser("1", "John_Doe");
		assertNotNull(result);
		assertEquals("John_Doe", result.getUsername());
		assertEquals("1", result.getUserId());
	}
	@Test
	void User_Failure() {
		User user = new User();
		user.setUserId("2");
		user.setUsername("Pat_Cummins");

		when(userRepository.existsById("2")).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);
	}


	

}
