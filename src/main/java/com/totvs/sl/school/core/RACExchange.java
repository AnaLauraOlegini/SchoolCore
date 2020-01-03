package com.totvs.sl.school.core;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RACExchange {

	public static final String INPUT = "rac-input";

	@Input(RACExchange.INPUT)
	SubscribableChannel input();
}
