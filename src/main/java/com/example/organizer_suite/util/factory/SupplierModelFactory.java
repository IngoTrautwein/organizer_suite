package com.example.organizer_suite.util.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.example.organizer_suite.server.core.model.BaseModel;

public class SupplierModelFactory {

	private static final Map<String, Supplier<? extends BaseModel>> registeredSuppliers = new HashMap<>();
	
	static {
		registeredSuppliers.put("user", new UserSupplier());
	}
	
	public static void registerSupplier(String type, Supplier<? extends BaseModel> supplier) {
		registeredSuppliers.put(type, supplier);
	}
	
	public static BaseModel getBaseModel(String type) {
		Supplier<? extends BaseModel> supplier = registeredSuppliers.get(type);
		return supplier != null ? supplier.get() : null;
	}
}
