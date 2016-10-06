package ca.ulaval.glo4002.flycheckin.boarding.domain;

import java.text.SimpleDateFormat;

import ca.ulaval.glo4002.flycheckin.boarding.infrastructure.BoardingRepository; // TODO

public class Services {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	private BoardingRepository boardingRepository;

	public Services() {
		this.boardingRepository = new BoardingRepository();
	}
}
