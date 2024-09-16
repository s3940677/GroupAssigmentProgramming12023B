import java.util.List;

public class ClientService {
    private List<Client> clients;

    public ClientService() {
        clients = FileHandler.loadClientsFromFile("data/clients.csv");  // Load clients from file
    }

    public Client getClientById(String clientId) {
        for (Client client : clients) {
            if (client.getUserId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }

    public void addClient(Client client) {
        clients.add(client);
        FileHandler.saveClientsToFile("data/clients.csv", clients);  // Save clients to the file
    }

    public void updateClient(Client updatedClient) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUserId().equals(updatedClient.getUserId())) {
                clients.set(i, updatedClient);  // Update the existing client
                break;
            }
        }
        FileHandler.saveClientsToFile("data/clients.csv", clients);  // Save updated client info to file
    }
}


