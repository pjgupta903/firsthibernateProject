package org.javabrains.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.javabrains.model.Address;
import org.javabrains.model.FourWheeler;
import org.javabrains.model.Phone;
import org.javabrains.model.TwoWheeler;
import org.javabrains.model.UserDetails;
import org.javabrains.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {

		
		/*UserDetails userDetails = new UserDetails();
		userDetails.setJoinDate(new Date());
		userDetails.setUserName("ranju");
		
		Phone phone = new Phone();
		phone.setPhoneName("Samsung");
		userDetails.getPhones().add(phone);
		Phone phone2 = new Phone();
		phone2.setPhoneName("I-Phone");
		userDetails.getPhones().add(phone2);
		
		Address homeaddress = new Address();
		homeaddress.setAddress1("8 Wildwood Drive");
		homeaddress.setCity("OLD Lyme");
		homeaddress.setState("Connecticut");
		
		Address officeAddress = new Address();
		officeAddress.setAddress1("aaa");
		officeAddress.setCity("bbb");
		officeAddress.setState("ccc");
		userDetails.getOfficeAddresses().add(homeaddress);
		userDetails.getOfficeAddresses().add(officeAddress);
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bike");
		userDetails.setVehicle(vehicle);
		
		UserDetails userDetails2 = new UserDetails();
		userDetails2.setJoinDate(new Date());
		userDetails2.setUserName("ranju");
		
		Address homeaddress2 = new Address();
		homeaddress2.setAddress1("8 Wildwood Drive");
		homeaddress2.setCity("OLD Lyme");
		homeaddress2.setState("Connecticut");
		
		Address officeAddress2 = new Address();
		officeAddress2.setAddress1("aaa");
		officeAddress2.setCity("bbb");
		officeAddress2.setState("ccc");
		userDetails2.getOfficeAddresses().add(homeaddress2);
		userDetails2.getOfficeAddresses().add(officeAddress2);
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("Car");
		userDetails2.setVehicle(vehicle2);*/
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// HQL use
		String id ="user_seq_25";
		String hql ="select u.userName,u.userId,p.phoneName" +
					" from UserDetails as u join u.phones as p where u.userId= ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Object[]> list = (List<Object[]>)query.list();
		for (Object[] userDetails : list) {
			String name = (String)userDetails[0];
			String userId = (String)userDetails[1];
			String phone = (String)userDetails[2];
			System.out.println("user id is :" +userId+ "user name is ::" +name+ "phone is ::" +phone);
		}
		session.getTransaction().commit();
		session.close();
		
		Session session2 = sessionFactory.openSession();
		Criteria cr = session2.createCriteria(UserDetails.class);
		cr.setProjection(Projections.property("userName"));
		List<String> names = (List<String>)cr.list();
		names.forEach(name -> System.out.println(name));
		/*Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("CAR");
		
		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setVehicleName("Bike");
		twoWheeler.setSteeringHandle("Bike Steering Handler");
		
		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setVehicleName("Porshe");
		fourWheeler.setSteeringWheel("Porshe Wheering Wheel");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);
		session.getTransaction().commit();
		session.close();*/
	
	
	
	
	
	}

}
