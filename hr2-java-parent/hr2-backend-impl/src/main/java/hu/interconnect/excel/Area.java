package hu.interconnect.excel;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Iterator;
import java.util.List;

public class Area implements Iterable<Coordinate>{

	public Coordinate from;
	public Coordinate to;
	
	private List<Coordinate> coordinates = newArrayList();
	
	public Area(Coordinate from, Coordinate to) {
		this.from = from;
		this.to = to;
		createCoordinates();
	}
	
	private void createCoordinates() {
		for (int y=from.row;y<=to.row;++y) {
			for (int x=from.col;x<=to.col;++x) {
				coordinates.add(new Coordinate(x, y));
			}
		}
	}

	@Override
	public Iterator<Coordinate> iterator() {
		return newArrayList(coordinates).iterator();
	}
}
