package outils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import controleur.MessageDTO;

public class ClientTest {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://172.17.0.5:8080/PPE3v2/"));
		WebTarget ciblefinale = cible.path("dto");
		String dtotext = ciblefinale.queryParam("email", "SOUFIAN").queryParam("password", "outils").request(MediaType.TEXT_PLAIN).get(Response.class).toString();
		System.out.println(dtotext);
		MessageDTO dto = ciblefinale.queryParam("email", "SOUFIAN").queryParam("password", "outils").request(MediaType.APPLICATION_XML).get(MessageDTO.class);
		System.out.println(dto.getBienvenue()+" "+dto.getRole());
		
	}
	
}
