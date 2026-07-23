package segundum.infrastructure.persistence.fakes;

import segundum.domain.outbound.CategoryHierarchyLoader;

public class FakeCategoryHierarchyLoader implements CategoryHierarchyLoader {

	private String lastSource;

	@Override
	public void load(String source) {
		this.lastSource = source;
	}

	public String getLastSource() {
		return lastSource;
	}
}
