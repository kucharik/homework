package hu.home.work.treeprobe.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
//no ez nagyon nem frankó, ha nem homework lenne... :D
import hu.home.work.treeprobe.dao.model.Item;
import hu.home.work.treeprobe.dao.model.ItemNode;
import hu.home.work.treeprobe.service.TreeService;

@RestController
public class TreeProbeController {

	@Autowired
	private TreeService srv;
	
	@GetMapping("/treeItems/{treeId}")
	List<Item> getTreeItems(@PathVariable Integer treeId) {

		return srv.getTreeItems(treeId);
	}
	
	@GetMapping("/tree/{treeId}")
	ItemNode getTree(@PathVariable Integer treeId) {

		return srv.getTree(treeId);
	}
}
