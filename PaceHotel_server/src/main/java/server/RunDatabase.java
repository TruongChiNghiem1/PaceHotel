package server;

import java.rmi.RemoteException;

import untils.EntityManagerFactoryUtil;

public class RunDatabase {
	public static void main(String[] args) throws RemoteException{
		EntityManagerFactoryUtil entityManagerFactoryUtil = new EntityManagerFactoryUtil("server_hotel");
		
	}
}
