package N_Polimorfismo.Teste;
import N_Polimorfismo.Entidades.*;

public class DAOTest {
    public static void main(String[] args) {
        // GenericDAO -> interface, ArquivoDAOImpl -> implementação
        // polimorfismo -> capacidade de um mesmo metodo oriundo de uma implementação possuir comportamentos distintos
        GenericDAO arquivoDAO = new ArquivoDAOImpl();
        arquivoDAO.save();

        GenericDAO bdDAO = new DatabaseDAOImpl();
        bdDAO.save();
    }
}