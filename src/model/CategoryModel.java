package model;

import java.util.List;

public class CategoryModel {
	private String name;
	private List<String> types;

	public CategoryModel() {

	}

	public CategoryModel(String name, List<String> types) {
		super();
		this.name = name;
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return name;
	}
}
