package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 2, 10);
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasPassadas() {
		LocalDate date = LocalDate.of(2010, 2, 10);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}
	
	@Test
	public void deveRetornarTrueParaDatasAtual() {
		LocalDate date = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

}
