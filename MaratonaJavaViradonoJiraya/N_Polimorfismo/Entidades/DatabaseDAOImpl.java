package N_Polimorfismo.Entidades;

public class DatabaseDAOImpl implements GenericDAO {
    @Override
    public void save() {
        System.out.println("Salvando dados no banco de dados");
    }
}
