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
public class StringControllerTests {
	@Autowired
	private StringController stringController;

	@Test
	public void Flip_Should_Flip_A_String_In_Dev_Config() throws Exception {
        final String input = "any";
		final String output = "yna";

        final var serviceMock = Mockito.mock(StringService.class);
		Mockito.when(serviceMock.FlipString(input)).thenReturn(output);
		
		stringController.stringService = serviceMock;

        final String result = stringController.FlipString(input);

		assertThat(result).isEqualTo(output);
		verify(serviceMock, times(1)).FlipString(input);
	}
	
	@Test
	public void CountWords_Should_Return_WordCount() throws Exception {
        final String input = "bla bla";
        final Integer output = 2;

        final var serviceMock = Mockito.mock(StringService.class);
		Mockito.when(serviceMock.CountWords(input)).thenReturn(output);
		
		stringController.stringService = serviceMock;

        final Integer result = this.stringController.CountWords(input);

		assertThat(result).isEqualTo(output);
		verify(serviceMock, times(1)).CountWords(input);
	}
}