package hu.interconnect.hr.backend.api.dto;

import java.util.Arrays;
import java.util.List;

public class MenuResponseDTO {
	
	public List<MenuItemDTO> menuItems;
	
	public MenuResponseDTO(MenuItemDTO... menuItems) {
		this.menuItems = menuItems.length == 0 ? null : Arrays.asList(menuItems);
	}

	public static class MenuItemDTO {

		public final String label;
		public final List<MenuItemDTO> menuItems;
		
		public MenuItemDTO(String label, MenuItemDTO... menuItems) {
			this.label = label;
			this.menuItems = menuItems.length == 0 ? null : Arrays.asList(menuItems);
		}
	}
}
