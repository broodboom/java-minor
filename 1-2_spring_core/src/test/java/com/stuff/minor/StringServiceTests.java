package com.stuff.minor;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("dev")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StringServiceTests {
    @Autowired
	private StringService stringService;

	@Test
	public void CountWords_Should_Calculate_Wordcount() throws Exception {
        final String input = "hello hi hey";
		final Integer output = 3;

        final var repositoryMock = Mockito.mock(StringRepository.class);
		Mockito.when(repositoryMock.Exists(input)).thenReturn(false);
		
		stringService.stringRepository = repositoryMock;

        final Integer result = stringService.CountWords(input);

		assertThat(result).isEqualTo(output);
		verify(repositoryMock, times(1)).Exists(input);
    }
    
    @Test
	public void CountWords_Should_Return_Stored_Wordcount() throws Exception {
        final String input = "hello hi hey";
		final Integer output = 3;

        final var repositoryMock = Mockito.mock(StringRepository.class);
        Mockito.when(repositoryMock.Exists(input)).thenReturn(true);
        Mockito.when(repositoryMock.GetCount(input)).thenReturn(3);
		
		stringService.stringRepository = repositoryMock;

        final Integer result = stringService.CountWords(input);

		assertThat(result).isEqualTo(output);
		verify(repositoryMock, times(1)).Exists(input);
		verify(repositoryMock, times(1)).GetCount(input);
    }
    
    @Test
	public void FlipString_Should_Flip_A_String() throws Exception {
        final String input = "hello";
		final String output = "olleh";

        final String result = stringService.FlipString(input);

		assertThat(result).isEqualTo(output);
    }
}
