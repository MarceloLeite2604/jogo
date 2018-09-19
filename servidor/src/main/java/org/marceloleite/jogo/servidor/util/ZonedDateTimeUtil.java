package org.marceloleite.jogo.servidor.util;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

@Component
public class ZonedDateTimeUtil {

	public ZonedDateTime now() {
		return ZonedDateTime.now(OffsetDateTime.now()
				.getOffset());
	}
}
