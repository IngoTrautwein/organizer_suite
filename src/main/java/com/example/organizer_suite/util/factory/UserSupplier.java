package com.example.organizer_suite.util.factory;

import java.util.function.Supplier;

import com.example.organizer_suite.server.core.model.BaseModel;
import com.example.organizer_suite.server.core.model.User;

public class UserSupplier implements Supplier<BaseModel> {

	@Override
	public BaseModel get() {
		return new User();
	}

}
