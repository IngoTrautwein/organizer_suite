package com.java.organizer_suite.server.util.factory;

import java.util.function.Supplier;

import com.java.organizer_suite.server.core.model.BaseModel;
import com.java.organizer_suite.server.core.model.User;

public class UserSupplier implements Supplier<BaseModel> {

	@Override
	public BaseModel get() {
		return new User("no firstname", "no surname");
	}

}
