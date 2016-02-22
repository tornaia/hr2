package hu.interconnect.hr.module.scheduledtasks;

import static hu.interconnect.util.ObjectUtils.equalsAndNotNull;

import hu.interconnect.scheduler.AbstractIdozitettFeladat;

public class AbstractIdozitettFeladatAzonositoAlapuPredicate implements java.util.function.Predicate<AbstractIdozitettFeladat> {

	private String azonosito;
	
	public AbstractIdozitettFeladatAzonositoAlapuPredicate(String azonosito) {
		this.azonosito = azonosito;
	}

	@Override
	public boolean test(AbstractIdozitettFeladat abstractIdozitettFeladat) {
		return equalsAndNotNull(azonosito, abstractIdozitettFeladat.getAzonosito());
	}
}
