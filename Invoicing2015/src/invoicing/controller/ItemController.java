package invoicing.controller;

import invoicing.entity.Item;
import invoicing.utility.ConsoleUtilities;
import invoicing.utility.SerialDB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
	public static final String PRODUCTS_FILE = "products.db";
	private static ItemController iController = new ItemController();
	List<Item> items = new ArrayList<>();

	private ItemController() {
	}

	private static ItemController getInstance() {
		return iController;
	}

	public static void main(String[] args) {
		ItemController pc = ItemController.getInstance();
		pc.loadItems();
		Item it = pc.inputItem();
		pc.saveItems();
	}

	private void saveItems() {
		Path dbPath = Paths.get(PRODUCTS_FILE);
		try {
			SerialDB.writeObjects(dbPath, items);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Item inputItem() {
		Item item = ConsoleUtilities.inputItem();
		items.add(item);
		return item;
	}

	private void loadItems() {
		Path dbPath = Paths.get(PRODUCTS_FILE);
		if (Files.exists(dbPath)) {
			try {
				items = SerialDB.readObjects(dbPath);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
