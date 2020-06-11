package com.example.organizer_suite.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.example.organizer_suite.server.core.model.BaseModel;

public class SupplierModelFactory {

	private static final Map<String, Supplier<? extends BaseModel>> rgisteredSuppliers = new HashMap<>();
	
	static {
		
	}
}
