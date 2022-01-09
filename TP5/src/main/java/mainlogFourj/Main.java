package mainlogFourj;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Model.Products;
import Model.User;


public class Main {
	private static final String LOG_FILE = "log4j.properties";
	
	public static void main(String[] args) {
		BasicConfigurator.configure();

		Logger logger = Logger.getLogger(Main.class);
		Properties properties = new Properties();
		
		PropertyConfigurator.configure(properties);
		try {	
		properties.load(new FileInputStream(LOG_FILE));
		
		Products product = new Products();
		//New User
		int tic = 0;
		User user = new User(1, "Paul", 24, "paul@gmail.com", "paulpassword");
		while(tic==0) {
			Scanner sc = new Scanner(System.in);
			logger.debug("Veuillez entrer votre email :");
			String username= sc.nextLine();
			logger.debug("Veuillez entrer votre mot de passe :");
			String password = sc.nextLine();
			
			if(username.equals(user.getEmail()) & password.equals(user.getPassword())) {
				tic = 1;
				logger.debug("-----Menu------");
				logger.debug("0 : Ajouter un produit");
				logger.debug("1 : Afficher ts les produits");
				logger.debug("2 : Rechercher un produit");
				logger.debug("3 : supprimer un produit");
				
				logger.debug("veuillez enter votre choix :");
				int choix = sc.nextInt();
				switch(choix) {
				case 0:
					product.add(6, "huawei", 600, 9);
					break;
				case  1:
					
					for(Products p : product.initProducts()) {
						logger.info(p.toString());
					}
					break;
				case 2:
					logger.debug("veuillez enter l id  rechercher :");
					int id = sc.nextInt();
					logger.info("Produit trouve :"+product.getProductByID(id));
					break;

				case 3:
					logger.debug("veuillez enter l id du produit a supprimer :");
					int idp = sc.nextInt();
					product.deleteProduct(idp);
						
					break;

				default:
					logger.debug("Mauvais choix");
				}
				
				
			}
			else {
				logger.debug("identifiants incorrects");
			}
		}
			}catch(Exception e) {
			
		}
	}
		

}
